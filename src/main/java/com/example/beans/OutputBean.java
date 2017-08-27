package com.example.beans;

import java.util.List;

public class OutputBean {
	private List<TeamBean> teams;
	
	public OutputBean() {
		
	}
	
	public OutputBean(List<TeamBean> teams) {
		this.teams = teams;
	}
	
	public List<TeamBean> getTeams() {
		return teams;
	}
	
	public void setTeams(List<TeamBean> teams) {
		this.teams = teams;
	}
}
