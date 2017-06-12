package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.project.heroes.Hero;
import com.example.project.heroes.HeroFactory;
import com.example.project.heroes.HeroList;


public class Maintest {

	public static void main(String[] args) {
		//loadUrl();
		//Position a = Position.ADC;
		//System.out.println(a.getValue());
		HeroList heroList = getAllHeroes();
		System.out.println(heroList.toString());
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
		String html = loadUrl();
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
	
	private static String loadUrl(){
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    StringBuffer html = new StringBuffer();

	    try {
	        url = new URL("http://champion.gg/");
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	            html.append(line);
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    return html.toString();
	}

}
