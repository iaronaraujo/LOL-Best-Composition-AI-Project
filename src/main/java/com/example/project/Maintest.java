package com.example.project;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.project.crawlers.MatchupCrawler;
import com.example.project.crawlers.URLLoader;
import com.example.project.heroes.Hero;
import com.example.project.heroes.HeroFactory;
import com.example.project.heroes.HeroList;
import com.example.project.heroes.MatchupType;
import com.example.project.heroes.Position;


public class Maintest {

	public static void main(String[] args) {
		
		//System.out.println(URLLoader.loadUrl("http://matchup.gg/matchup/Zed/Ahri/MID"));		
		HeroList heroList = getAllHeroes();
		List<Hero> midlist = heroList.getHeroesByPosition(Position.MID);
		List<Hero> toplist = heroList.getHeroesByPosition(Position.TOP);
		Hero hero1 = midlist.get(0);
		Hero hero2 = midlist.get(1);
		Hero hero3 = toplist.get(0);
		MatchupCrawler crawler = new MatchupCrawler();
		crawler.getMatchupValue(hero1, hero2, MatchupType.GENERAL);
		//System.out.println(heroList.toString());
	}
	
	private static HeroList getAllHeroes(){
		
		Elements heroTable = getHeroTable();
		Element current;
		HeroList heroList = new HeroList();
		HeroFactory factory = new HeroFactory();
		int i = 0;
		int id = 0;
		while(i < heroTable.size()){
			current = heroTable.eq(i).first();
			Element href = current.child(0).child(0);
			String heroName = getLastString(href);
			i++;
			
			Element parentHref = current.child(0);
			int j = 1;
			Element currentref;	
			while(j < parentHref.children().size()){
				currentref = parentHref.child(j);
				String heroPosition = getLastString(currentref);
				Hero hero = factory.createHero(heroName, heroPosition, id);
				heroList.addHero(hero);
				id++;
				j++;
			}
		}	
		return heroList;
	}
	
	private static Elements getHeroTable(){
		String html = URLLoader.loadUrl("http://champion.gg/");
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		Elements primaryhue = body.getElementsByClass("primary-hue");
		Elements maincontainer = primaryhue.first().getElementsByClass("main-container");
		Elements pagecontent = maincontainer.first().getElementsByClass("page-content");
		Elements containerfluid = pagecontent.first().getElementsByClass("container-fluid");
		Elements row = containerfluid.first().getElementsByClass("row");
		Elements colmd = row.last().getElementsByClass("col-md-9 clearfix");
		Elements heroTable = colmd.first().getElementsByClass("champ-height");
		return heroTable;
	}
	
	private static String getLastString(Element element){
		String link = element.attr("href");
		String[] split = link.split("/");
		String nameOrPos = split[split.length-1];
		return nameOrPos;
	}

}
