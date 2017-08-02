package com.example.mains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.data.BeanParser;
import com.example.data.Champion;
import com.example.data.Elo;
import com.example.data.Matchup;
import com.example.data.Role;
import com.example.data.RoleInfo;
import com.example.exceptions.HttpErrorException;
import com.example.genetics.ChampionsByRole;
import com.example.genetics.MatchupRelations;
import com.example.riot.GgGiver;
import com.example.riot.RiotGiver;

public class Main {

	public static void main(String[] args) {
		//runSetup();
		runMain();
	}
	
	
	private static void runSetup() {
		RiotGiver rgiver = RiotGiver.getInstance();
		rgiver.saveSetupFile();
		GgGiver ggiver = GgGiver.getInstance();
		ggiver.saveSetupFile(new Elo[]{Elo.SILVER, Elo.BRONZE, Elo.GOLD, Elo.PLATINUM, Elo.HIGH_ELO}, 1000000);
		
	}


	private static void runMain(){
		
		BeanParser parser = new BeanParser();
		
		List<Champion> champions = null;
		
		try {
			champions = parser.parse();
		} catch(HttpErrorException e) {
			e.printStackTrace();
		}
		
		ChampionsByRole champsByRole = new ChampionsByRole();
		
		for(Champion c : champions){
			System.out.println(c.toString());
			champsByRole.addChampion(c);
			for(RoleInfo info : c.getRoles()){
				System.out.println(info.getRole().name());
				for(Matchup mu : info.getMatchups()){
					System.out.println(mu.getChampion1().getChampion().getId() + " VS " + mu.getChampion2().getChampion().getId() + " = " + mu.getChampion1WinRate());
				}
			}
		}
		
		MatchupRelations relations = new MatchupRelations(champsByRole.getChampionsByRole());
		
		System.out.println("=========");
		System.out.println(relations.getWinRate(Role.JUN, 254, 54));
		System.out.println(relations.getWinRate(0, 245, 136));
		System.out.println(relations.getWinRate(1, 245, 126));
		System.out.println(relations.getWinRate(2, 245, 34));
		System.out.println(relations.getWinRate(3, 245, 34));
		System.out.println(relations.getWinRate(Role.TOP, 114, 59));
		
		
	}

}
