package com.example.beans;

public class TeamBean {
	private String mid;
	private String top;
	private String adc;
	private String sup;
	private String jun;
	private double fitness;
	
	public TeamBean() {
		
	}
	
	public TeamBean(String mid, String top, String adc, String sup, String jun, double fitness) {
		this.mid = mid;
		this.top = top;
		this.adc = adc;
		this.sup = sup;
		this.jun = jun;
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getAdc() {
		return adc;
	}

	public void setAdc(String adc) {
		this.adc = adc;
	}

	public String getSup() {
		return sup;
	}

	public void setSup(String sup) {
		this.sup = sup;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}
	
	
}
