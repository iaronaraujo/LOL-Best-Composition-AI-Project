package com.example.beans;

public class GGChampionBean {
	private double winrate;
	private String role;
	
	public GGChampionBean(String role, double winrate) {
		this.role = role;
		this.winrate = winrate;
	}
	
	public double getWinrate() {
		return winrate;
	}
	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
