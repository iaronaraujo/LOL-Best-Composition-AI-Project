package com.example.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.data.Champion;
import com.example.data.Team;

public class GeneticAlgorithm {
	public static final int TEAM_SIZE = 5;
	public static final double MUTATION_CHANCE = 0.01;
	List<TeamChromossome> population;
	private Random randomGenerator;
	
	public GeneticAlgorithm(){
		randomGenerator = new Random();
	}
	
	
	public void startAlgorithm(ChampionsByRole champsByRole, MatchupRelations matchupRelations, int populationSize, int iterations){
		population = new ArrayList<>();
		population = createFirstPopulation(champsByRole, populationSize);
		
		int runs = 0;
		updateFitness(matchupRelations);
		while(runs < iterations){
			runs++;
			population = getChildrenPopulation(population, populationSize, champsByRole);
			updateFitness(matchupRelations);
		}
		
		
	}


	private List<TeamChromossome> getChildrenPopulation(List<TeamChromossome> population, int populationSize, ChampionsByRole champsByRole) {
		List<TeamChromossome> newPopulation = new ArrayList<>();
		double adjustedMinFitness = getAdjustedMinFitness(population);
		double rouletteSum = getRouletteSum(population, adjustedMinFitness);
		
		while(newPopulation.size() < populationSize){
			List<TeamChromossome> parents = selectParents(population, adjustedMinFitness, rouletteSum);
			List<Champion> child1 = new ArrayList<>();
			List<Champion> child2 = new ArrayList<>();
			int index1, index2;
			for(int i = 0; i < TEAM_SIZE; i++){
				index1 = randomGenerator.nextInt(2);
				index2 = complement(index1);
				child1.add(parents.get(index1).getTeam().getChampion(i));
				child2.add(parents.get(index2).getTeam().getChampion(i));
			}
			mutation(child1, champsByRole);
			mutation(child2, champsByRole);
			
			Team t1 = new Team(child1);
			Team t2 = new Team(child2);
			
			if(t1.isValid()){
				newPopulation.add(new TeamChromossome(t1));
				if(newPopulation.size() == populationSize) break;
			}
			if(t2.isValid()){
				newPopulation.add(new TeamChromossome(t2));
			}
			
			
		}
		
		return newPopulation;
	}


	private void mutation(List<Champion> child, ChampionsByRole champsByRole) {
		for(int i = 0; i < TEAM_SIZE; i++){
			if(randomGenerator.nextDouble() < MUTATION_CHANCE){
				Champion randomChampion = champsByRole.getRandomChampion(i);
				child.set(i, randomChampion);
			}
		}
		
	}


	private int complement(int index1) {
		int result;
		if(index1 == 0){
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}


	private List<TeamChromossome> selectParents(List<TeamChromossome> population2, double adjustedMinFitness, double rouletteSum) {
		double randomDouble;
		double adjustedFitness;
		List<TeamChromossome> parents = new ArrayList<>();
		while(parents.size() < 2){
			randomDouble = randomGenerator.nextDouble() * rouletteSum;
			for(TeamChromossome tc : population2){
				adjustedFitness = tc.getFitness() - adjustedMinFitness;
				randomDouble -= adjustedFitness;
				if(randomDouble <= 0){
					parents.add(tc);
					break;
				}
			}
		}
		return parents;
	}


	private double getRouletteSum(List<TeamChromossome> population2, double minFitness) {
		double sum = 0;
		for(TeamChromossome chromossome : population2){
			sum += chromossome.getFitness() - minFitness;
		}
		return sum;
	}


	private double getAdjustedMinFitness(List<TeamChromossome> population2) {
		double minFitness = Double.MAX_VALUE;
		for(TeamChromossome chromossome : population2){
			if(chromossome.getFitness() < minFitness) minFitness = chromossome.getFitness();
		}
		if(minFitness > 1){
			minFitness -= 1;
		}
		return minFitness;
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
