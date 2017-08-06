package com.example.data;

public enum Role {
	MID(0), TOP(1), ADC(2), SUP(3), JUN(4);
	
	private final int value;
	
	Role(int positionValue){
		value = positionValue;
	}
	
	public int getValue(){
		return value;
	}
	
	public static Role parseString(String role) {
		if (role.equals("DUO_SUPPORT")) {
			return Role.SUP;
		} else if (role.equals("MIDDLE")) {
			return Role.MID;
		} else if (role.equals("JUNGLE")) {
			return Role.JUN;
		} else if (role.equals("TOP")) {
			return Role.TOP;
		} else if (role.equals("DUO_CARRY")) {
			return Role.ADC;
		} else {
			// ERRO
			throw new RuntimeException("The role was: " + role + ".");
		}
 	}
}
