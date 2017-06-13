package com.example.project.crawlers;

import org.jsoup.parser.Parser;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Teste {
	public static void main(String[] args) {
		getEmployees();
	}
	
	private static void getEmployees() {
		final String uri = "http://api.champion.gg/v2/champions?elo=SILVER&champData=kda,damage,averageGames,totalHeal,killingSpree,minions,gold,positions,normalized,groupedWins,trinkets,runes,firstitems,summoners,skills,finalitems,masteries,maxMins,matchups&limit=200&api_key=fc54506c86cbb45421e850737b08ec39";
		final String uriRiot = "https://br1.api.riotgames.com/lol/static-data/v3/champions?api_key=RGAPI-f6e06e93-fed0-446c-98d7-e2c2a166cb9d";
		
		RestTemplate restTemplate = new RestTemplate();
		String riotData = restTemplate.getForObject(uriRiot, String.class);
		JsonParser jsonParser = new JsonParser();
		JsonObject object = jsonParser.parse(riotData).getAsJsonObject();
		System.out.println(object);
		
		/*String result = restTemplate.getForObject(uri, String.class);
		
		JsonArray array = jsonParser.parse(result).getAsJsonArray();
		System.out.println(array.get(1));*/
		
	}
}
