package com.example.riot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;

import com.example.beans.RiotChampionBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RiotGiver {
	public static final String RIOT_API_KEY = "RGAPI-f6e06e93-fed0-446c-98d7-e2c2a166cb9d";
	public static final String RIOT_BR_ENDPOINT = "br1.api.riotgames.com";
	public static final String REQUEST_TYPE = "https";
	public static final String RIOT_GET_CHAMPIONS_URI = REQUEST_TYPE + "://" + RIOT_BR_ENDPOINT + "/lol/static-data/v3/champions?api_key=" + RIOT_API_KEY;
	
	public static final RiotGiver INSTANCE = new RiotGiver();
	
	private RestTemplate template;
	private Map<Long, RiotChampionBean> champions;
	
	public static void main(String[] args) {
		RiotGiver g = RiotGiver.getInstance();
		g.refreshChampionsData();
	}
	
	private RiotGiver() {
		this.template = new RestTemplate();
		this.champions = new HashMap<>();
	}
	
	public static RiotGiver getInstance() {	
		return INSTANCE;
	}
	
	private void refreshChampionsData() {
		JsonObject data = requestChampionsData();
		Iterator<Entry<String, JsonElement>> iterator = data.get("data").getAsJsonObject().entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, JsonElement> entry = iterator.next();
			JsonElement element = entry.getValue();
			
			Gson gson = new Gson();
			RiotChampionBean hero = gson.fromJson(element, RiotChampionBean.class);
			champions.put(hero.getId(), hero);
		}
	}
	
	public Map<Long, RiotChampionBean> getChampions() {
		return champions;
	}
	
	private JsonObject requestChampionsData() {
		String championsData = template.getForObject(RIOT_GET_CHAMPIONS_URI, String.class);
		return new JsonParser().parse(championsData).getAsJsonObject();
	}
}
