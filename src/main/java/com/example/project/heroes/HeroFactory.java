package com.example.project.heroes;

public class HeroFactory {
	
	public HeroFactory(){
		
	}
	
	public Hero createHero(String name, String position, int id){
		return new Hero(name, nameToPosition(position), id);
	}
	
	public Role nameToPosition(String position){
		Role pos = null;
		if(position.equals("Middle")){
			pos = Role.MID;
		} else if (position.equals("Top")){
			pos = Role.TOP;
		} else if (position.equals("ADC")){
			pos = Role.ADC;
		} else if (position.equals("Support")){
			pos = Role.SUP;
		} else if (position.equals("Jungle")){
			pos = Role.JUN;
		}
		return pos;
	}

}
