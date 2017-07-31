package com.example.mains;

import java.util.List;

import com.example.data.BeanParser;
import com.example.data.Champion;
import com.example.exceptions.HttpErrorException;

public class Main {

	public static void main(String[] args) {
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
