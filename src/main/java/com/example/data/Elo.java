package com.example.data;

public enum Elo {
	BRONZE("BRONZE"), SILVER("SILVER"), GOLD("GOLD"), PLATINUM("PLATINUM"), HIGH_ELO("");
	
	private String ggApiParameter;
	
	private Elo(String ggApiParameter) {
		this.ggApiParameter = ggApiParameter;
	}
	
	public String getGgApiParameter() {
		return ggApiParameter;
	}
	
	public Elo parseString(String elo) {
		return Elo.valueOf(elo);
	}
}
