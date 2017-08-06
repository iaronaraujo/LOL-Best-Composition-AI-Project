package com.example.genetics;

import com.example.data.Team;

public class TeamChromossome implements Comparable<TeamChromossome>{
	private Team team;
	private double fitness;
	
	public TeamChromossome(Team team){
		this.team = team;
		this.fitness = 0;
	}

	public Team getTeam() {
		return team;
	}

	public double getFitness() {
		return fitness;
	}
	
	public void addFitness(double value){
		fitness += value;
	}

	

	@Override
	public int compareTo(TeamChromossome tc) {
		double comparation = fitness - tc.getFitness();
		int result;
		if(comparation > 0){
			result = 1;
		} else if (comparation == 0){
			result = 0;
		} else{
			result = -1;
		}
		return result;
	}

	@Override
	public String toString() {
		return "team= [" + team + "] fitness= " + fitness;
	}
	
	
	
	

}
