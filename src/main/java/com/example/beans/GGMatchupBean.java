package com.example.beans;

public class GGMatchupBean {
	private ChampionMatchupInfo champ1;
	private ChampionMatchupInfo champ2;
	private long champ1_id;
	private long champ2_id;
	private long count;
	private String patch;
	private String role;
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
		this(-1L, "-1.0", "NONE", -1L, -1L, null, null, "NONE");
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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
		return "GGMatchupBean [champ1=" + champ1 + ", champ2=" + champ2 + ", champ1_id=" + champ1_id + ", champ2_id="
				+ champ2_id + ", count=" + count + ", patch=" + patch + ", role=" + role + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (champ1_id ^ (champ1_id >>> 32));
		result = prime * result + (int) (champ2_id ^ (champ2_id >>> 32));
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + ((patch == null) ? 0 : patch.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		GGMatchupBean other = (GGMatchupBean) obj;
		if (champ1_id != other.champ1_id)
			return false;
		if (champ2_id != other.champ2_id)
			return false;
		if (count != other.count)
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			long temp;
			temp = Double.doubleToLongBits(winrate);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			ChampionMatchupInfo other = (ChampionMatchupInfo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (Double.doubleToLongBits(winrate) != Double.doubleToLongBits(other.winrate))
				return false;
			return true;
		}

		private GGMatchupBean getOuterType() {
			return GGMatchupBean.this;
		}
	}
}
