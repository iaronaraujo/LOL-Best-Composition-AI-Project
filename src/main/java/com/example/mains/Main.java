package com.example.mains;

import java.util.List;

import com.example.data.BeanParser;
import com.example.data.Champion;
import com.example.data.Elo;
import com.example.data.Matchup;
import com.example.data.Role;
import com.example.data.RoleInfo;
import com.example.exceptions.HttpErrorException;
import com.example.genetics.ChampionsByRole;
import com.example.genetics.GeneticAlgorithm;
import com.example.genetics.MatchupRelations;
import com.example.genetics.TeamChromossome;
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
			champsByRole.addChampion(c);
		}
		
		MatchupRelations relations = new MatchupRelations(champsByRole.getChampionsByRole());
		
		GeneticAlgorithm g = new GeneticAlgorithm();
		g.startAlgorithm(champsByRole, relations, 10, 100000);
		List<TeamChromossome> teams = g.getPopulation();
		for(TeamChromossome tc : teams){
			System.out.println(tc.toString());
		}
		
	}

}
