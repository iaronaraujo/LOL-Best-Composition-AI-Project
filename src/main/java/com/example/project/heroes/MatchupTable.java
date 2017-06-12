package com.example.project.heroes;

public class MatchupTable {
	private double[][] matchupTable;
	private double[][] laneMatchupTable;
	
	public MatchupTable(int heroesQnt){
		matchupTable = new double[heroesQnt][heroesQnt];
		laneMatchupTable = new double[heroesQnt][heroesQnt];
	}
	
	public double getMatchUpValue(Hero hero1, Hero hero2){
		double value = 0.0;
		if(matchupTable[hero1.getId()][hero2.getId()] > 0){
			value += matchupTable[hero1.getId()][hero2.getId()];
		} else if (matchupTable[hero1.getId()][hero2.getId()] == 0){
			value += updateTable(hero1, hero2, matchupTable);
		}
		
		
		
		return value;
	}

	private double updateTable(Hero hero1, Hero hero2, double[][] matchupTable2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
