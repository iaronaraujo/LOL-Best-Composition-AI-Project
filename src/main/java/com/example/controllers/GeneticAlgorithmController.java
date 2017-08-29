package com.example.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.OutputBean;
import com.example.beans.RunInputBean;
import com.example.beans.TeamBean;
import com.example.data.BeanParser;
import com.example.data.Champion;
import com.example.data.Elo;
import com.example.exceptions.HttpErrorException;
import com.example.genetics.ChampionsByRole;
import com.example.genetics.GeneticAlgorithm;
import com.example.genetics.MatchupRelations;
import com.example.genetics.TeamChromossome;

@CrossOrigin
@RestController
public class GeneticAlgorithmController {
	@RequestMapping(value = "/ga/run", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutputBean> run(@RequestBody RunInputBean iBean) {
		BeanParser parser = new BeanParser();
		Elo[] elos = new Elo[]{Elo.HIGH_ELO};
		
		List<Champion> champions = null;
		
		try {
			champions = parser.parse(elos);
		} catch(HttpErrorException e) {
			e.printStackTrace();
		}
		
		ChampionsByRole champsByRole = new ChampionsByRole();
		
		for(Champion c : champions){
			champsByRole.addChampion(c);
		}
		
		MatchupRelations relations = new MatchupRelations(champsByRole.getChampionsByRole());
		
		GeneticAlgorithm g = new GeneticAlgorithm();
		g.startAlgorithm(champsByRole, relations, iBean.getInit_pop(), iBean.getIterations());
		List<TeamChromossome> teams = g.getPopulation();
		Collections.sort(teams);
		OutputBean bean = new OutputBean();
		
		List<TeamBean> teamBeans = new ArrayList<>();
		
		for(TeamChromossome tc : teams) {
			TeamBean tbean = new TeamBean(tc.getTeam().getChampion(0).getName(), tc.getTeam().getChampion(1).getName(), tc.getTeam().getChampion(2).getName(), tc.getTeam().getChampion(3).getName(), tc.getTeam().getChampion(4).getName(), tc.getFitness());
			teamBeans.add(tbean);
			
			System.out.println(tc.toString());
		}
		
		bean.setTeams(teamBeans);
		
		return new ResponseEntity<>(bean, HttpStatus.OK);
	}
	

}
