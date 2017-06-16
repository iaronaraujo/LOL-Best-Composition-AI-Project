package com.example.riot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;

import com.example.beans.GGChampionBean;
import com.example.beans.GGMatchupBean;
import com.example.project.heroes.Elo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GgGiver {
	public static final String GG_API_KEY = "fc54506c86cbb45421e850737b08ec39";
	public static final String GG_ENDPOINT = "api.champion.gg";
	public static final String REQUEST_TYPE = "http";
	public static final String CHAMPDATA = "matchups";
	
	public static final GgGiver INSTANCE = new GgGiver();
	
	public static void main(String[] args) {
		GgGiver g = GgGiver.getInstance();
		g.retrieveMatchups(new Elo[]{Elo.SILVER}, 100);
	}
	
	private GgGiver() {
		
	}
	
	public static GgGiver getInstance() {	
		return INSTANCE;
	}
	
	public Map<GGChampionBean, List<GGMatchupBean>> retrieveMatchups(Elo[] elos, long limit) {
		RestTemplate template = new RestTemplate();
		JsonParser jsonParser = new JsonParser();
		Gson gson = new Gson();
		
		Map<GGChampionBean, List<GGMatchupBean>> matchups = new HashMap<>();
		
		for (Elo elo : elos) {
			String uri = REQUEST_TYPE + "://" + GG_ENDPOINT +  "/v2/champions/43?elo=" + elo.getGgApiParameter() + "&limit=" + limit + "&champData=" + CHAMPDATA + "&api_key=" + GG_API_KEY;
			String rawResponse = template.getForObject(uri, String.class);
			
			JsonArray response = jsonParser.parse(rawResponse).getAsJsonArray();
			
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
