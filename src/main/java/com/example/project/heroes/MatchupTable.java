package com.example.project.heroes;

import com.example.project.crawlers.MatchupCrawler;

public class MatchupTable {
	private double[][] matchupTable;
	private double[][] laneMatchupTable;
	private MatchupCrawler crawler;
	
	public MatchupTable(int heroesQnt){
		matchupTable = new double[heroesQnt][heroesQnt];
		laneMatchupTable = new double[heroesQnt][heroesQnt];
		crawler = new MatchupCrawler();
	}
	
	public double getMatchUpValue(Hero hero1, Hero hero2){
		double value = 0.0;
		value += getTableValue(hero1, hero2, matchupTable, MatchupType.GENERAL);
		if(hero1.getPosition().equals(hero2.getPosition())){
			value += getTableValue(hero1, hero2, laneMatchupTable, MatchupType.LANE);
		}
		return value;
	}
	
	private double getTableValue(Hero hero1, Hero hero2, double[][] table, MatchupType type) {
		double value = 0.0;
		if(table[hero1.getId()][hero2.getId()] > 0){
			value += table[hero1.getId()][hero2.getId()];
		} else if (table[hero1.getId()][hero2.getId()] == 0){
			value += updateTable(hero1, hero2, table, type);
		}
		return value;
	}

	private double updateTable(Hero hero1, Hero hero2, double[][] table, MatchupType type) {
		double value = crawler.getMatchupValue(hero1, hero2, type);
		table[hero1.getId()][hero2.getId()] = value;
		if(value < 0){
			value = 0;
		}
		return value;
	}
	
}
