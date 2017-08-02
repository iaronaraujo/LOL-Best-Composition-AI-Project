package com.example.riot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;

import com.example.beans.RiotChampionBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RiotGiver {
	public static final String RIOT_JSON_FILE = "setup_files/RiotJson.json";
	public static final String RIOT_API_KEY = "RGAPI-8054c362-142d-4ed1-b31a-ac5028cbd14b";
	public static final String RIOT_BR_ENDPOINT = "br1.api.riotgames.com";
	public static final String REQUEST_TYPE = "https";
	public static final String RIOT_GET_CHAMPIONS_URI = REQUEST_TYPE + "://" + RIOT_BR_ENDPOINT + "/lol/static-data/v3/champions?api_key=" + RIOT_API_KEY;
	
	public static final RiotGiver INSTANCE = new RiotGiver();
	
	public static void main(String[] args) {
		RiotGiver g = RiotGiver.getInstance();
		g.retrieveChampions();
	}
	
	private RiotGiver() {
		
	}
	
	public static RiotGiver getInstance() {	
		return INSTANCE;
	}
	
	public void saveSetupFile(){
		try{
			Writer writer = new FileWriter(RIOT_JSON_FILE);
			
			RestTemplate template = new RestTemplate();
			
			String rawResponse = template.getForObject(RIOT_GET_CHAMPIONS_URI, String.class);
			writer.write(rawResponse);
			writer.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<Long, RiotChampionBean> retrieveChampions() {
		JsonParser jsonParser = new JsonParser();
		Gson gson = new Gson();
		
		Map<Long, RiotChampionBean> champions = new HashMap<>();
		
		BufferedReader br;
		String rawResponse = "";
		try{
			br = new BufferedReader(new FileReader(RIOT_JSON_FILE));
			String line;
			while((line = br.readLine()) != null){
				rawResponse += line;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		JsonObject response = jsonParser.parse(rawResponse).getAsJsonObject();
		
		for (Entry<String, JsonElement> championEntry : response.get("data").getAsJsonObject().entrySet()) {
			JsonElement element = championEntry.getValue();
			RiotChampionBean champion = gson.fromJson(element, RiotChampionBean.class);
			champions.put(champion.getId(), champion);
		}
		
		return champions;
	}
}
