package com.example.project.heroes;

public class Hero {
	
	private String name;
	private Role position;
	private int id;
	
	public Hero(String name, Role position, int id){
		this.name = name;
		this.position = position;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Role getPosition() {
		return position;
	}
	
	public int getId(){
		return id;
	}
	
	@Override
	public String toString(){
		return position.getValue() + " " + name;
	}
	
	

}
