package com.example.project.heroes;

import java.util.ArrayList;
import java.util.List;

public class HeroList {
	private List<List<Hero>> heroes;
	
	public HeroList(){
		heroes = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			heroes.add(new ArrayList<Hero>());
		}
	}
	
	public void addHero(Hero hero){
		int heroPosition = hero.getPosition().getValue();
		heroes.get(heroPosition).add(hero);
	}
	
	public List<Hero> getHeroesByPosition(Role position){
		return heroes.get(position.getValue());
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(List<Hero> list : heroes){
			for(Hero h : list){
				sb.append(h.toString() + "\n");
			}
		}
		return sb.toString();
	}

}
