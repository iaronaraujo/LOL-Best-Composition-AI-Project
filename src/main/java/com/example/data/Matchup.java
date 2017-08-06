package com.example.data;



public class Matchup {
	private long numberOfMatchups;
	private String patch;
	
	private MatchupChampion champion1;
	private MatchupChampion champion2;
	
	public Matchup(String patch, long numberOfMatchups, MatchupChampion champion1, MatchupChampion champion2) {
		this.champion1 = champion1;
		this.champion2 = champion2;
		this.patch = patch;
		this.numberOfMatchups = numberOfMatchups;
	}
	
	public Champion getWinner() {
		return (champion1.getWinrate() >= champion2.getWinrate()) ? (champion1.getChampion()) : (champion2.getChampion());
	}
	
	public double getChampion1WinRate() {
		return champion1.getWinrate();
	}
	
	public double getChampion2WinRate() {
		return champion2.getWinrate();
	}

	public long getNumberOfMatchups() {
		return numberOfMatchups;
	}

	public void setNumberOfMatchups(long numberOfMatchups) {
		this.numberOfMatchups = numberOfMatchups;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public MatchupChampion getChampion1() {
		return champion1;
	}

	public void setChampion1(MatchupChampion champion1) {
		this.champion1 = champion1;
	}

	public MatchupChampion getChampion2() {
		return champion2;
	}

	public void setChampion2(MatchupChampion champion2) {
		this.champion2 = champion2;
	}
}
