package vora.shantanu;

public class Delivery {
	private String matchId;
	private String bowlingTeam;
	private String extraRuns;
	private String bowler;
	private String totalRuns;
	private String batter;

	public String getBatter() {
		return batter;
	}

	public void setBatter(String batter) {
		this.batter = batter;
	}

	private String batterRuns;

	public String getBatterRuns() {
		return batterRuns;
	}

	public void setBatterRuns(String batterRuns) {
		this.batterRuns = batterRuns;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getBowlingTeam() {
		return bowlingTeam;
	}

	public String getBowler() {
		return bowler;
	}

	public void setBowler(String bowler) {
		this.bowler = bowler;
	}

	public void setBowlingTeam(String bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public String getExtraRuns() {
		return extraRuns;
	}

	public void setExtraRuns(String extraRuns) {
		this.extraRuns = extraRuns;
	}

	public String getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(String totalRuns) {
		this.totalRuns = totalRuns;
	}
}
