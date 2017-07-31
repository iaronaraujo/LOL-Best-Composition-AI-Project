package com.example.riot;

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
	public static final String RIOT_API_KEY = "RGAPI-dcf78e3c-c00d-4f87-b8ee-457f45d846ef";
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
	
	public Map<Long, RiotChampionBean> retrieveChampions() {
		RestTemplate template = new RestTemplate();
		JsonParser jsonParser = new JsonParser();
		Gson gson = new Gson();
		
		Map<Long, RiotChampionBean> champions = new HashMap<>();
		
		String rawResponse = template.getForObject(RIOT_GET_CHAMPIONS_URI, String.class);
		JsonObject response = jsonParser.parse(rawResponse).getAsJsonObject();
		
		for (Entry<String, JsonElement> championEntry : response.get("data").getAsJsonObject().entrySet()) {
			JsonElement element = championEntry.getValue();
			RiotChampionBean champion = gson.fromJson(element, RiotChampionBean.class);
			champions.put(champion.getId(), champion);
		}
		
		return champions;
	}
}
