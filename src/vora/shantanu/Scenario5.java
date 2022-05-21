package vora.shantanu;

import java.util.ArrayList;

public class Scenario5 {
	private ArrayList<ArrayList<String>> matchData;
	private ArrayList<ArrayList<String>> deliveryData;

	public Scenario5(ArrayList<ArrayList<String>> matchData, ArrayList<ArrayList<String>> deliveryData) {
		this.matchData = matchData;
		this.deliveryData = deliveryData;
	}

	public ArrayList<ArrayList<String>> getMatchData() {
		return matchData;
	}

	public ArrayList<ArrayList<String>> getDeliveryData() {
		return deliveryData;
	}

	private ArrayList<ArrayList<String>> getMatches2017() {
		ArrayList<ArrayList<String>> matches2017 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2017")) {
				matches2017.add(match);
			}
		}
		return matches2017;
	}

}
