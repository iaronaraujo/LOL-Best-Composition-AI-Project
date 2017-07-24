package com.example.data;

public class MatchupChampion {
	private Champion champion;
	private double winrate;
	
	public MatchupChampion(Champion champion, double winrate) {
		this.champion = champion;
		this.winrate = winrate;
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	
	public Champion getChampion() {
		return champion;
	}
	
	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	@Override
	public String toString() {
		return "MatchupChampion [" + super.toString() + " winrate=" + winrate + "]";
	}
}
