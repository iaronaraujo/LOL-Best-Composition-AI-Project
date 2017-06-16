package com.example.data;

import java.util.List;
import java.util.Map;

import com.example.beans.GGChampionBean;
import com.example.beans.GGMatchupBean;
import com.example.beans.RiotChampionBean;
import com.example.riot.GgGiver;
import com.example.riot.RiotGiver;

public class MyParser {
	private List<Champion> champions;
	private Map<Champion, List<Matchup>> matchups;
	
	
	public MyParser() {
		
	}
	
	public void parse() {
		GgGiver ggGiver = GgGiver.getInstance();
		RiotGiver riotGiver = RiotGiver.getInstance();
		
		Map<GGChampionBean, List<GGMatchupBean>> ggMatchups = ggGiver.getMatchups();
		Map<Long, RiotChampionBean> riotChampions = riotGiver.getChampions();
		
		for (Long championId : riotChampions.keySet()) {
			RiotChampionBean riotChampion = riotChampions.get(championId);
					
			for (GGChampionBean ggChampion : ggMatchups.keySet()) {
				if (ggChampion.getChampionId() == championId) {
					List<GGMatchupBean> ggChampionMatchups = ggMatchups.get(championId);
					
				}
			}
			
			
			//Champion champion = new Champion(id, name, roles);
			
		}
	}
}
