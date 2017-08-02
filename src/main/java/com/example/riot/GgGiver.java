package com.example.riot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;

import com.example.beans.GGChampionBean;
import com.example.beans.GGMatchupBean;
import com.example.data.Elo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GgGiver {
	public static final String GG_FOLDER = "setup_files/GG_files/";
	public static final String GG_API_KEY = "fc54506c86cbb45421e850737b08ec39";
	public static final String GG_ENDPOINT = "api.champion.gg";
	public static final String REQUEST_TYPE = "http";
	public static final String CHAMPDATA = "matchups";
	
	public static final GgGiver INSTANCE = new GgGiver();
	
	
	private GgGiver() {
		
	}
	
	public static GgGiver getInstance() {	
		return INSTANCE;
	}
	
	public void saveSetupFile(Elo[] elos, long limit){
		try{
			for(Elo elo : elos){
				Writer writer = new FileWriter(GG_FOLDER + elo.getName() + ".json");
				RestTemplate template = new RestTemplate();
				String uri = REQUEST_TYPE + "://" + GG_ENDPOINT +  "/v2/champions?elo=" + elo.getGgApiParameter() + "&limit=" + limit + "&champData=" + CHAMPDATA + "&api_key=" + GG_API_KEY;
				String rawResponse = template.getForObject(uri, String.class);
				writer.write(rawResponse);
				writer.close();
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<GGChampionBean, List<GGMatchupBean>> retrieveMatchups(Elo[] elos) {
		JsonParser jsonParser = new JsonParser();
		Gson gson = new Gson();
		
		Map<GGChampionBean, List<GGMatchupBean>> matchups = new HashMap<>();
		
		for (Elo elo : elos) {
			BufferedReader br;
			StringBuffer rawResponse= new StringBuffer();
			try{
				br = new BufferedReader(new FileReader(GG_FOLDER + elo.getName() + ".json"));
				String line;
				while((line = br.readLine()) != null){
					rawResponse.append(line);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			
			JsonArray response = jsonParser.parse(rawResponse.toString()).getAsJsonArray();
			
			for (JsonElement responseElement : response) {			
				GGChampionBean champion = gson.fromJson(responseElement, GGChampionBean.class);
				
				if (!matchups.containsKey(champion)) {
					matchups.put(champion, new ArrayList<>());
				}
				
				JsonElement rowOfMatchups = responseElement.getAsJsonObject().get("matchups");
				
				for (Entry<String, JsonElement> kindOfMatchupEntry : rowOfMatchups.getAsJsonObject().entrySet()) {
					JsonArray matchupsOfKind = kindOfMatchupEntry.getValue().getAsJsonArray();
					
					for (JsonElement matchupElement : matchupsOfKind) {
						GGMatchupBean matchupBean = gson.fromJson(matchupElement.getAsJsonObject(), GGMatchupBean.class);
						matchupBean.setType(kindOfMatchupEntry.getKey());
						matchups.get(champion).add(matchupBean);
					}
				}
			}
		}
		
		return matchups;
	}
}
