package com.example.data;

import java.util.ArrayList;
import java.util.List;

public class Champion {
	private long id;
	private String name;
	private double banRate;
	private List<RoleInfo> roles;
	
	public Champion(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Champion(long id, String name, List<RoleInfo> roles) {
		this.id = id;
		this.name = name;
		this.roles = roles;
	}

	public Champion(long id, String name, double banRate, RoleInfo[] roles) {
		this.id = id;
		this.name = name;
		this.banRate = banRate;
		this.roles = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<RoleInfo> getRoles() {
		return roles;
	}
	
	public void setRoles(List<RoleInfo> roles){
		this.roles = roles;
	}
	
	public double getBanRate() {
		return banRate;
	}
	
	public void setBanRate(double banRate) {
		this.banRate = banRate;
	}

	@Override
	public String toString() {
		return "Champion [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Champion other = (Champion) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
