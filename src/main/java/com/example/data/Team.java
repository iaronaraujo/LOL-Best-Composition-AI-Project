package com.example.data;

public class Team {
	private Champion top;
	private Champion mid;
	private Champion jungler;
	private Champion adc;
	private Champion sup;
	
	public Team(Champion top, Champion mid, Champion jungler, Champion adc, Champion sup) {
		this.top = top;
		this.mid = mid;
		this.jungler = jungler;
		this.adc = adc;
		this.sup = sup;
	}

	public Champion getTop() {
		return top;
	}

	public void setTop(Champion top) {
		this.top = top;
	}

	public Champion getMid() {
		return mid;
	}

	public void setMid(Champion mid) {
		this.mid = mid;
	}

	public Champion getJungler() {
		return jungler;
	}

	public void setJungler(Champion jungler) {
		this.jungler = jungler;
	}

	public Champion getAdc() {
		return adc;
	}

	public void setAdc(Champion adc) {
		this.adc = adc;
	}

	public Champion getSup() {
		return sup;
	}

	public void setSup(Champion sup) {
		this.sup = sup;
	}
}
