package com.example.riot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.client.RestTemplate;

import com.example.beans.GGMatchupBean;
import com.example.beans.RiotChampionBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GgGiver {
	public static final String GG_API_KEY = "fc54506c86cbb45421e850737b08ec39";
	public static final String GG_ENDPOINT = "api.champion.gg";
	public static final String REQUEST_TYPE = "http";
	public static final String ELO = "SILVER";
	public static final String LIMIT = "200";
	public static final String CHAMPDATA = "matchups";
	public static final String GG_GET_CHAMPION_INFO_URI = REQUEST_TYPE + "://" + GG_ENDPOINT + "/v2/champions?elo=" + ELO + "&limit=" + LIMIT + "&champData=" + CHAMPDATA + "&api_key=" + GG_API_KEY;
	//http://api.champion.gg/v2/champions/43?elo=SILVER&limit=200&champData=positions&api_key=fc54506c86cbb45421e850737b08ec39
	
	public static final GgGiver INSTANCE = new GgGiver();
	
	private RestTemplate template;
	private Map<Long, List<GGMatchupBean>> matchupsMap;
	
	public static void main(String[] args) {
		GgGiver g = GgGiver.getInstance();
		g.refreshChampionsData(43);
	}
	
	private GgGiver() {
		this.template = new RestTemplate();
		this.matchupsMap = new HashMap<>();
	}
	
	public static GgGiver getInstance() {	
		return INSTANCE;
	}
	
	private void refreshChampionsData(long championId) {
		JsonArray data = requestChampionsData();
		Iterator<JsonElement> iterator = data.iterator();
		
		Gson gson = new Gson();
		
		// Iterate over champions
		while (iterator.hasNext()) {
			JsonElement championRoleInfo = iterator.next();
			
			long id = championRoleInfo.getAsJsonObject().get("_id").getAsJsonObject().get("championId").getAsLong();
			
			if (!matchupsMap.containsKey(id)) {
				this.matchupsMap.put(id, new ArrayList<>());
			}
			
			JsonElement matchups = championRoleInfo.getAsJsonObject().get("matchups");
			Iterator<Entry<String, JsonElement>> iterator2 = matchups.getAsJsonObject().entrySet().iterator();
			
			// Iterate over matchup types
			while(iterator2.hasNext()) {
				Entry<String, JsonElement> entry = iterator2.next();
				JsonElement element = entry.getValue();
				
				Iterator<JsonElement> roleMatchups = element.getAsJsonArray().iterator();
				
				// Iterate over matchups
				while(roleMatchups.hasNext()) {
					JsonElement matchupElement = roleMatchups.next();
					GGMatchupBean matchupBean = gson.fromJson(matchupElement.getAsJsonObject(), GGMatchupBean.class);
					matchupsMap.get(id).add(matchupBean);
				}
			}
		}
		
		System.out.println(matchupsMap);
		//Iterator<Entry<String, JsonElement>> iterator = data.get("data").getAsJsonObject().entrySet().iterator();
		
		/*while (iterator.hasNext()) {
			Entry<String, JsonElement> entry = iterator.next();
			JsonElement element = entry.getValue();
			
			Gson gson = new Gson();
			RiotChampionBean hero = gson.fromJson(element, RiotChampionBean.class);
			champions.put(hero.getId(), hero);
		}*/
	}
	
	public Map<Long, List<GGMatchupBean>> getChampions() {
		return matchupsMap;
	}
	
	private JsonArray requestChampionsData() {
		String championsData = template.getForObject(GG_GET_CHAMPION_INFO_URI, String.class);
		return new JsonParser().parse(championsData).getAsJsonArray();
	}
}
