package com.example.mains;

import java.util.List;

import com.example.data.BeanParser;
import com.example.data.Champion;
import com.example.data.Elo;
import com.example.exceptions.HttpErrorException;
import com.example.riot.GgGiver;
import com.example.riot.RiotGiver;

public class Main {

	public static void main(String[] args) {
		//runSetup();
		runMain();
	}
	
	
	private static void runSetup() {
		RiotGiver rgiver = RiotGiver.getInstance();
		rgiver.saveSetupFile();
		GgGiver ggiver = GgGiver.getInstance();
		ggiver.saveSetupFile(new Elo[]{Elo.SILVER, Elo.BRONZE, Elo.GOLD, Elo.PLATINUM, Elo.HIGH_ELO}, 1000000);
		
	}


	private static void runMain(){
		
		BeanParser parser = new BeanParser();
		
		List<Champion> champions = null;
		
		try {
			champions = parser.parse();
		} catch(HttpErrorException e) {
			e.printStackTrace();
		}
		
		for(Champion c : champions){
			System.out.println(c.toString());
		}
	}

}
