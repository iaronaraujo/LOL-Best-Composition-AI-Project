package com.example.mains;

import java.util.List;

import com.example.data.BeanParser;
import com.example.data.Champion;

public class Main {

	public static void main(String[] args) {
		BeanParser parser = new BeanParser();
		List<Champion> champions = parser.parse();
		for(Champion c : champions){
			System.out.println(c.toString());
		}

	}

}
