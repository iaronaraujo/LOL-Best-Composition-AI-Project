package com.example.beans;

public class GGChampionBean {
	private long championId;
	private double playRate;
	private double winRate;
	private String role;
	private long gamesPlayed;
	private String patch;
	private String elo;
	
	public GGChampionBean(long championId, double playRate, double winRate, String role, long gamesPlayed, String patch, String elo) {
		this.championId = championId;
		this.playRate = playRate;
		this.winRate = winRate;
		this.role = role;
		this.gamesPlayed = gamesPlayed;
		this.patch = patch;
		this.elo = elo;
		this.role = role;
	}
	
	public GGChampionBean() {
		
	}

	public long getChampionId() {
		return championId;
	}

	public void setChampionId(long championId) {
		this.championId = championId;
	}

	public double getPlayRate() {
		return playRate;
	}

	public void setPlayRate(double playRate) {
		this.playRate = playRate;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(long gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
	}

	@Override
	public String toString() {
		return "GGChampionBean [championId=" + championId + ", playRate=" + playRate + ", winRate=" + winRate
				+ ", role=" + role + ", gamesPlayed=" + gamesPlayed + ", patch=" + patch + ", elo=" + elo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (championId ^ (championId >>> 32));
		result = prime * result + ((elo == null) ? 0 : elo.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		GGChampionBean other = (GGChampionBean) obj;
		if (championId != other.championId)
			return false;
		if (elo == null) {
			if (other.elo != null)
				return false;
		} else if (!elo.equals(other.elo))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
