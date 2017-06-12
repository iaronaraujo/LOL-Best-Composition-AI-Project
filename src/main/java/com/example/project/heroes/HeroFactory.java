package com.example.project.heroes;

public class HeroFactory {
	
	public HeroFactory(){
		
	}
	
	public Hero createHero(String name, String position, int id){
		return new Hero(name, nameToPosition(position), id);
	}
	
	public Position nameToPosition(String position){
		Position pos = null;
		if(position.equals("Middle")){
			pos = Position.MID;
		} else if (position.equals("Top")){
			pos = Position.TOP;
		} else if (position.equals("ADC")){
			pos = Position.ADC;
		} else if (position.equals("Support")){
			pos = Position.SUP;
		} else if (position.equals("Jungle")){
			pos = Position.JUN;
		}
		return pos;
	}

}
