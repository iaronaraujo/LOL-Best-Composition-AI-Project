package com.example.project.crawlers;


import com.example.project.heroes.Hero;
import com.example.project.heroes.MatchupType;

public class MatchupCrawler {
	
	private static final String MATCHUP_GG = "http://matchup.gg/matchup/";
	
	public MatchupCrawler(){
		
	}
	
	public double getMatchupValue(Hero hero1, Hero hero2, MatchupType type){
		String html;
		if(type.equals(MatchupType.GENERAL)){
			html = URLLoader.loadUrl(MATCHUP_GG + hero1.getName() + "/" + hero2.getName() + "/");
		} else {
			String positionName = ""; //TODO
			html = URLLoader.loadUrl(MATCHUP_GG + hero1.getName() + "/" + hero2.getName() + "/" + positionName + "/");
		}
		System.out.println(html);
		
		return 0.0;
		
	}

}
