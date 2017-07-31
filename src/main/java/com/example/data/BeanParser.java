package com.example.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.HttpClientErrorException;

import com.example.beans.GGChampionBean;
import com.example.beans.GGMatchupBean;
import com.example.beans.RiotChampionBean;
import com.example.exceptions.GGHttpErrorException;
import com.example.exceptions.HttpErrorException;
import com.example.exceptions.RiotHttpErrorException;
import com.example.riot.GgGiver;
import com.example.riot.RiotGiver;

public class BeanParser {
	private List<Champion> champions;
	
	public static void main(String[] args) {
		BeanParser parser = new BeanParser();
		try {
			parser.parse();
		} catch(HttpErrorException e) {
			e.printStackTrace();
		}
	}
	
	public BeanParser() {
		
	}
	
	public List<Champion> parse() throws HttpErrorException {
		GgGiver ggGiver = GgGiver.getInstance();
		RiotGiver riotGiver = RiotGiver.getInstance();
		
		Map<Long, RiotChampionBean> riotChampions = null;
		
		try {
			riotChampions = riotGiver.retrieveChampions();
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			throw new RiotHttpErrorException("Error while trying to request data from riot api. " + e.getMessage());
		}
		
		Map<GGChampionBean, List<GGMatchupBean>> ggMatchups = null;
		
		try {
			ggMatchups = ggGiver.retrieveMatchups(new Elo[]{Elo.SILVER}, 1000000);
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			throw new  GGHttpErrorException("Error while trying to request data from gg api. " + e.getMessage());
		}
		
		this.champions = new ArrayList<Champion>();
		
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
