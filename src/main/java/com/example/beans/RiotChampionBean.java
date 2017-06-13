package com.example.beans;

public class RiotChampionBean {
	private long id;
	private String key;
	private String name;
	private String title;
	
	public RiotChampionBean(long id, String key, String name, String title) {
		this.id = id;
		this.key = key;
		this.name = name;
		this.title = title;
	}
	
	public RiotChampionBean() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RiotHeroBean [id=" + id + ", key=" + key + ", name=" + name + ", title=" + title + "]";
	}
	
	
}
