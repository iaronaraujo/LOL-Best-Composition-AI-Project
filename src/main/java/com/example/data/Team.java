package com.example.data;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private List<Champion> champions;

	public Team(Champion mid, Champion top, Champion adc, Champion sup, Champion jungler) {
		champions = new ArrayList<>();
		champions.add(mid);
		champions.add(top);
		champions.add(adc);
		champions.add(sup);
		champions.add(jungler);
	}

	public Champion getChampion(Role role){
		return champions.get(role.getValue());
	}
	
	public Champion getChampion(int pos){
		return champions.get(pos);
	}
	
	public void setChampion(Role role, Champion c){
		champions.set(role.getValue(), c);
	}
	
	public void setChampion(int pos, Champion c){
		champions.set(pos, c);
	}
	
	public boolean isValid(){
		for(int i = 0; i < 4; i++){
			for(int j = i+1; j < 5; j++){
				Champion champ1 = champions.get(i);
				Champion champ2 = champions.get(j);
				if(champ1.getName().equals(champ2.getName())){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	@Override
	public String toString() {
		return  " MID = " + champions.get(0).getName() +
				" TOP = " + champions.get(1).getName() +
				" ADC = " + champions.get(2).getName() +
				" SUP = " + champions.get(3).getName() +
				" JUN = " + champions.get(4).getName();
	}
}
