package com.example.data;

import java.util.Arrays;

public class Champion {
	private long id;
	private String name;
	private double banRate;
	private RoleInfo[] roles;
	
	public Champion(long id, String name, double banRate, RoleInfo[] roles) {
		this.id = id;
		this.name = name;
		this.banRate = banRate;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RoleInfo[] getRoles() {
		return roles;
	}
	
	public double getBanRate() {
		return banRate;
	}

	@Override
	public String toString() {
		return "Champion [id=" + id + ", name=" + name + ", roles=" + Arrays.toString(roles) + "]";
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
