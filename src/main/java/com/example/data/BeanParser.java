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
	
	public static void main(String[] args) {
		BeanParser parser = new BeanParser();
		parser.parse();
	}
	
	public BeanParser() {
		
	}
	
	public List<Champion> parse() {
		// Initializing Variables.
		GgGiver ggGiver = GgGiver.getInstance();
		RiotGiver riotGiver = RiotGiver.getInstance();
		this.champions = new ArrayList<Champion>();
		
		Map<GGChampionBean, List<GGMatchupBean>> ggMatchups = ggGiver.retrieveMatchups(new Elo[]{Elo.SILVER}, 1000000);
		Map<Long, RiotChampionBean> riotChampions = riotGiver.retrieveChampions();
		
		for (Long championId : riotChampions.keySet()) {
			RiotChampionBean riotChampion = riotChampions.get(championId);
			Champion champion = new Champion(riotChampion.getId(), riotChampion.getName()); 
			champions.add(champion);
		}
		
		for (Long championId : riotChampions.keySet()) {
			for (GGChampionBean ggChampion : ggMatchups.keySet()) {
				if (ggChampion.getChampionId() == championId) {
					List<Matchup> matchups = new ArrayList<>();
					
					for(GGMatchupBean ggMatchup : ggMatchups.get(ggChampion)) {
						MatchupChampion champion1 = new MatchupChampion(findChampion(ggMatchup.getChamp1_id()), ggMatchup.getChamp1().getWinrate());
						MatchupChampion champion2 = new MatchupChampion(findChampion(ggMatchup.getChamp2_id()), ggMatchup.getChamp2().getWinrate());
						
						if (champion1 != null && champion2 != null) {
							if (champion1.getChampion().getId() == ggChampion.getChampionId()) {
								Matchup matchup = new Matchup(ggChampion.getPatch(), ggMatchup.getCount(), champion1, champion2);
								matchups.add(matchup);
							} else if (champion2.getChampion().getId() == ggChampion.getChampionId()) {
								Matchup matchup = new Matchup(ggChampion.getPatch(), ggMatchup.getCount(), champion2, champion1);
								matchups.add(matchup);
							} else {
								// ERRO
							}
						} else {	
							// ERRO
						}
					}
					
					RoleInfo role = new RoleInfo(Role.parseString(ggChampion.getRole()), ggChampion.getElo(), ggChampion.getPlayRate(), ggChampion.getWinRate(), ggChampion.getPercentRolePlayed(), matchups);
					Champion champion = findChampion(ggChampion.getChampionId());
					champion.setBanRate(ggChampion.getBanRate());
					champion.addRole(role);
				}
			}
		}
		
		return champions;
	}
	
	public Champion findChampion(Long id) {
		for (Champion c : champions) {
			if (c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}
}
