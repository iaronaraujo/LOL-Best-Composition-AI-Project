package com.example.genetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.data.Champion;
import com.example.data.Matchup;
import com.example.data.Role;
import com.example.data.RoleInfo;

public class MatchupRelations {
	
	public static final double NO_MATCHUP_FLAG = -10.0;
	private List<Map<Long, Map<Long, Double>>> matchupRelations;
	
	public MatchupRelations(List<List<Champion>> championsByRole){
		
		matchupRelations = new ArrayList<Map<Long, Map<Long, Double>>>();
		for(int i = 0; i < 5; i++){
			Map<Long, Map<Long, Double>> champions1map = new HashMap<Long, Map<Long, Double>>();
			List<Champion> championsInARole = championsByRole.get(i);
			for(Champion champion1 : championsInARole){
				List<Matchup> champ1matchups = null;
				for(RoleInfo info : champion1.getRoles()){
					if(info.getRole().getValue() == i){
						champ1matchups = info.getMatchups();
					}
				}
				
				champions1map.put(champion1.getId(), new HashMap<Long, Double>());
				for(Matchup matchup : champ1matchups){
					champions1map.get(champion1.getId()).put(matchup.getChampion2().getChampion().getId(), matchup.getChampion1WinRate());
				}
				
			}
			matchupRelations.add(champions1map);
		}
		
	}
	
	
	public double getWinRate(int pos, long champ1, long champ2){
		double winrate = NO_MATCHUP_FLAG;
		Map<Long, Double> aux = matchupRelations.get(pos).get(champ1);
		if(aux != null){
			if(aux.get(champ2) != null){
				winrate = aux.get(champ2);
			}
		}
		return winrate;
	}
	
	public double getWinRate(Role role, long champ1, long champ2){
		return getWinRate(role.getValue(), champ1, champ2);
	}

}
