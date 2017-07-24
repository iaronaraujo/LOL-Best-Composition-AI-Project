package com.example.beans;

public class GGMatchupBean {
	private long count;
	private String patch;
	private String role;
	
	private long champ1_id;
	private long champ2_id;
	
	private ChampionMatchupInfo champ1;
	private ChampionMatchupInfo champ2;
	
	private String type;
	
	public GGMatchupBean(long count, String patch, String role, long champ1_id, long champ2_id, ChampionMatchupInfo champ1, ChampionMatchupInfo champ2, String type) {
		this.count = count;
		this.patch = patch;
		this.role = role;
		this.champ1_id = champ1_id;
		this.champ2_id = champ2_id;
		this.champ1 = champ1;
		this.champ2 = champ2;
		this.type = type;
	}
	
	public GGMatchupBean() {
		
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getChamp1_id() {
		return champ1_id;
	}

	public void setChamp1_id(long champ1_id) {
		this.champ1_id = champ1_id;
	}

	public long getChamp2_id() {
		return champ2_id;
	}

	public void setChamp2_id(long champ2_id) {
		this.champ2_id = champ2_id;
	}

	public ChampionMatchupInfo getChamp1() {
		return champ1;
	}

	public void setChamp1(ChampionMatchupInfo champ1) {
		this.champ1 = champ1;
	}

	public ChampionMatchupInfo getChamp2() {
		return champ2;
	}

	public void setChamp2(ChampionMatchupInfo champ2) {
		this.champ2 = champ2;
	}
	
	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GGMatchupBean [count=" + count + ", patch=" + patch + ", role=" + role + ", champ1_id=" + champ1_id
				+ ", champ2_id=" + champ2_id + ", champ1=" + champ1 + ", champ2=" + champ2 + ", type=" + type + "]";
	}

	public class ChampionMatchupInfo {
		private String role;
		private double winrate;
		
		public ChampionMatchupInfo(String role, double winrate) {
			this.role = role;
			this.winrate = winrate;
		}
		
		public ChampionMatchupInfo() {
			
		}
		
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public double getWinrate() {
			return winrate;
		}
		
		public void setWinrate(double winrate) {
			this.winrate = winrate;
		}

		@Override
		public String toString() {
			return "ChampionMatchupInfo [role=" + role + ", winrate=" + winrate + "]";
		}
	}
}
