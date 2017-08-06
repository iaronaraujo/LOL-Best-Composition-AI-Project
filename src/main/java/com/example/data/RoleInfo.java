package com.example.data;

import java.util.List;

public class RoleInfo {
	private Role role;
	private String elo;
	private double playRate;
	private double winRate;
	private double percentRolePlayed;
	private List<Matchup> matchups;
	
	public RoleInfo(Role role, String elo, double playRate, double winRate, double percentRolePlayed, List<Matchup> matchups) {
		this.role = role;
		this.elo = elo;
		this.playRate = playRate;
		this.winRate = winRate;
		this.percentRolePlayed = percentRolePlayed;
		this.matchups = matchups;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
	}

	public double getPlayRate() {
		return playRate;
	}

	public void setPlayRate(double playRate) {
		this.playRate = playRate;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getPercentRolePlayed() {
		return percentRolePlayed;
	}

	public void setPercentRolePlayed(double percentRolePlayed) {
		this.percentRolePlayed = percentRolePlayed;
	}

	public List<Matchup> getMatchups() {
		return matchups;
	}

	public void setMatchups(List<Matchup> matchups) {
		this.matchups = matchups;
	}

	@Override
	public String toString() {
		return "RoleInfo [role=" + role + ", elo=" + elo + ", playRate=" + playRate + ", winRate=" + winRate
				+ ", percentRolePlayed=" + percentRolePlayed + ", matchups=" + matchups + "]";
	}
	
	
}
