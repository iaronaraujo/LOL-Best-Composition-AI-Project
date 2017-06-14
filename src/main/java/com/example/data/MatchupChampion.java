package com.example.data;

import com.example.project.heroes.Role;

public class MatchupChampion extends Champion {
	private double winrate;
	
	public MatchupChampion(long id, String name, Role[] roles, double winrate) {
		super(id, name, roles);
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}

	@Override
	public String toString() {
		return "MatchupChampion [" + super.toString() + " winrate=" + winrate + "]";
	}
}
