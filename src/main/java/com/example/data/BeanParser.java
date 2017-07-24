package com.example.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.beans.GGChampionBean;
import com.example.beans.GGMatchupBean;
import com.example.beans.RiotChampionBean;
import com.example.riot.GgGiver;
import com.example.riot.RiotGiver;

public class BeanParser {
	private List<Champion> champions;
	private Map<Champion, List<Matchup>> matchups;
	
	public BeanParser() {
		
	}
	
	public void parse() {
		GgGiver ggGiver = GgGiver.getInstance();
		RiotGiver riotGiver = RiotGiver.getInstance();
		
		champions = new ArrayList<Champion>();
		
		Map<GGChampionBean, List<GGMatchupBean>> ggMatchups = ggGiver.retrieveMatchups(new Elo[]{Elo.SILVER}, 1000000);
		Map<Long, RiotChampionBean> riotChampions = riotGiver.retrieveChampions();
		
		for (Long championId : riotChampions.keySet()) {
			RiotChampionBean riotChampion = riotChampions.get(championId);
			Champion champion = new Champion(riotChampion.getId(), riotChampion.getName()); 
			champions.add(champion);
		}
		
		for (Long championId : riotChampions.keySet()) {
			List<RoleInfo> roles = new ArrayList<>();
			
			for (GGChampionBean ggChampion : ggMatchups.keySet()) {
				
				
				if (ggChampion.getChampionId() == championId) {
					List<GGMatchupBean> ggChampionMatchups = ggMatchups.get(ggChampion);
					
					RoleInfo role = new RoleInfo(ggChampion.getRole(), ggChampion.getElo(), ggChampion.getPlayRate(), ggChampion.getWinRate());
					
					List<Matchup> matchups = new ArrayList<>();
					
					for(GGMatchupBean ggMatchup : ggMatchups.get(ggChampion)) {
						
						Matchup matchup = new Matchup(patch, numberOfMatchups, champion1, champion2);
					}
					
					List<GGMatchupBean> ggChampionMatchups = ggMatchups.get(championId);
					List<String> ggRoles = new ArrayList<>();
					ggRoles.add(ggChampion.getRole());
				}
			}
		}
	}
}
