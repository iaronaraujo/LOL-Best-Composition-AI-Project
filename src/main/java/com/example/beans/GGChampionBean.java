package com.example.beans;

public class GGChampionBean {
	private double banRate;
	private long championId;
	private String elo;
	private long gamesPlayed;
	private String patch;
	private double percentRolePlayed;
	private double playRate;
	private String role;
	private double winRate;
	
	public GGChampionBean(double banRate, long championId, String elo, long gamesPlayed, String patch, double playRate, double percentRolePlayed, String role, double winRate) {
		this.banRate = banRate;
		this.championId = championId;
		this.elo = elo;
		this.gamesPlayed = gamesPlayed;
		this.patch = patch;
		this.percentRolePlayed = percentRolePlayed;
		this.playRate = playRate;
		this.role = role;
		this.winRate = winRate;
	}
	
	public GGChampionBean() {
		this(-1.0, -1L, "NONE", -1L, "-1.0", -1.0, -1.0, "NONE", -1.0);
	}

	public double getBanRate() {
		return banRate;
	}

	public void setBanRate(double banRate) {
		this.banRate = banRate;
	}

	public long getChampionId() {
		return championId;
	}

	public void setChampionId(long championId) {
		this.championId = championId;
	}

	public String getElo() {
		return elo;
	}

	public void setElo(String elo) {
		this.elo = elo;
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

	public double getPercentRolePlayed() {
		return percentRolePlayed;
	}

	public void setPercentRolePlayed(double percentRolePlayed) {
		this.percentRolePlayed = percentRolePlayed;
	}

	public double getPlayRate() {
		return playRate;
	}

	public void setPlayRate(double playRate) {
		this.playRate = playRate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	@Override
	public String toString() {
		return "GGChampionBean [banRate=" + banRate + ", championId=" + championId + ", elo=" + elo + ", gamesPlayed="
				+ gamesPlayed + ", patch=" + patch + ", percentRolePlayed=" + percentRolePlayed + ", playRate="
				+ playRate + ", role=" + role + ", winRate=" + winRate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (championId ^ (championId >>> 32));
		result = prime * result + ((elo == null) ? 0 : elo.hashCode());
		result = prime * result + ((patch == null) ? 0 : patch.hashCode());
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
		if (patch == null) {
			if (other.patch != null)
				return false;
		} else if (!patch.equals(other.patch))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
