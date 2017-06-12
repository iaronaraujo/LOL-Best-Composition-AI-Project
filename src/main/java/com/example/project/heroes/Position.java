package com.example.project.heroes;

public enum Position {
	MID(0), TOP(1), ADC(2), SUP(3), JUN(4);
	
	private final int value;
	
	Position(int positionValue){
		value = positionValue;
	}
	
	public int getValue(){
		return value;
	}
	
	

}
