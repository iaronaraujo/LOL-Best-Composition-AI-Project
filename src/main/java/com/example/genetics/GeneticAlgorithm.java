package com.example.genetics;

import java.util.ArrayList;
import java.util.List;

import com.example.data.Champion;
import com.example.data.Team;

public class GeneticAlgorithm {
	public static final int TEAM_SIZE = 5;
	List<TeamChromossome> population;
	
	public GeneticAlgorithm(){
		
	}
	
	
	public void startAlgorithm(ChampionsByRole champsByRole, MatchupRelations matchupRelations, int populationSize, int iterations){
		population = new ArrayList<>();
		population = createFirstPopulation(champsByRole, populationSize);
		
		int runs = 0;
		while(runs < iterations){
			updateFitness(matchupRelations);
		}
		
		
	}


	private void updateFitness(MatchupRelations matchupRelations) {
		for(int i = 0; i < population.size()-1; i++){
			for(int j = i+1; j < population.size(); j++){
				for(int k = 0; k < TEAM_SIZE; k++){
					long champ1 = population.get(i).getTeam().getChampion(k).getId();
					long champ2 = population.get(j).getTeam().getChampion(k).getId();
					double winrate1 = matchupRelations.getWinRate(k, champ1, champ2);
					double winrate2 = matchupRelations.getWinRate(k, champ2, champ1);
					population.get(i).addFitness(winrate1);
					population.get(j).addFitness(winrate2);
				}
				
			}
		}
		
	}


	private List<TeamChromossome> createFirstPopulation(ChampionsByRole champsByRole, int populationSize) {
		
		List<TeamChromossome> firstPopulation = new ArrayList<>();
		while(firstPopulation.size() < populationSize){
			
			List<Champion> champs = new ArrayList<>();
			for(int i = 0; i < TEAM_SIZE; i++){
				champs.add(champsByRole.getRandomChampion(i));
			}
			
			Team team = new Team(champs.get(0), champs.get(1), champs.get(2), champs.get(3), champs.get(4));
			if(team.isValid()){
				firstPopulation.add(new TeamChromossome(team));
			}
		}
		
		return firstPopulation;
	}
	
	public List<TeamChromossome> getPopulation(){
		return population;
	}


	

}
