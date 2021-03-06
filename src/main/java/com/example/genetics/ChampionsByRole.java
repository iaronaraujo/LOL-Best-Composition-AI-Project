package com.example.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.data.Champion;
import com.example.data.Role;
import com.example.data.RoleInfo;

public class ChampionsByRole {
	public static final int TEAM_SIZE = 5;
	private List<List<Champion>> championsByRole;
	private Random randomGenerator;
	
	public ChampionsByRole(){
		randomGenerator = new Random();
		championsByRole = new ArrayList<List<Champion>>();
		for(int i = 0; i < TEAM_SIZE; i++){
			championsByRole.add(new ArrayList<Champion>());
		}
	}
	
	public void addChampion(Champion champ){
		for(RoleInfo info : champ.getRoles()){
			championsByRole.get(info.getRole().getValue()).add(champ);
		}
	}
	
	public Champion getRandomChampion(int position){
		List<Champion> list = championsByRole.get(position);
		int i = randomGenerator.nextInt(list.size());
		return list.get(i);		
	}
	
	public Champion getRandomChampion(Role role){
		return getRandomChampion(role.getValue());
	}
	
	public List<Champion> getChampions(Role role){
		return championsByRole.get(role.getValue());
	}
	
	public List<List<Champion>> getChampionsByRole(){
		return championsByRole;
	}

}
