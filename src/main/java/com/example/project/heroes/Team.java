package com.example.project.heroes;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private List<Hero> heroes;
	
	public Team(){
		heroes = new ArrayList<>(5);
	}
	
	public void addHero(Hero hero){
		heroes.add(hero.getPosition().getValue(), hero);
	}
	
	public boolean isValid(){
		for(int i = 0; i < 5; i++){
			if (heroes.get(i) == null) return false;
			String heroName = heroes.get(i).getName();
			for(int j = 0; j<i; j++){
				if(heroes.get(j).getName().equals(heroName)){
					return false;
				}
			}
		}
		
		return true;
	}
	

}
