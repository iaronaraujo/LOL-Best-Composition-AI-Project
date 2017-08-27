package com.example.beans;

public class RunInputBean {
	private int init_pop;
	private int iterations;
	
	public RunInputBean() {
		
	}
	
	public RunInputBean(int init_pop, int iterations) {
		this.init_pop = init_pop;
		this.iterations = iterations;
	}
	
	public int getInit_pop() {
		return init_pop;
	}
	
	public void setInit_pop(int init_pop) {
		this.init_pop = init_pop;
	}
	
	public int getIterations() {
		return iterations;
	}
	
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
}
