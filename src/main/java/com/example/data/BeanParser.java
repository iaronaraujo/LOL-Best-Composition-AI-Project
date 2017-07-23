package com.example.data;

import java.lang.reflect.Array;
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
		
		Map<GGChampionBean, List<GGMatchupBean>> ggMatchups = ggGiver.getMatchups();
		Map<Long, RiotChampionBean> riotChampions = riotGiver.retrieveChampions();
		
		
		for (Long championId : riotChampions.keySet()) {
			RiotChampionBean riotChampion = riotChampions.get(championId);
			
			RoleInfo[] roles;
			Champion champion = new Champion(riotChampion.getId(), riotChampion.getName());
			
			for (GGChampionBean ggChampion : ggMatchups.keySet()) {
				if (ggChampion.getChampionId() == championId) {
					List<GGMatchupBean> ggChampionMatchups = ggMatchups.get(championId);
					List<String> ggRoles = new ArrayList();
					ggRoles.add(ggChampion.getRole());
				}
				
				//roles = roleInfos.toArray();
				//champion.setRoles(roles);
				//champions.add(champion);	
			}
			
			
			//Champion champion = new Champion(id, name, roles);
			
		}
	}
	

}
