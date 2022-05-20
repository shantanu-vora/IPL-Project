package vora.shantanu;

import java.util.ArrayList;

public class Scenario3 {
	private ArrayList<ArrayList<String>> matchData;
	private ArrayList<ArrayList<String>> deliveryData;

	public Scenario3(ArrayList<ArrayList<String>> matchData, ArrayList<ArrayList<String>> deliveryData) {
		this.matchData = matchData;
		this.deliveryData = deliveryData;
	}

	public ArrayList<ArrayList<String>> getMatchData() {
		return matchData;
	}

	public ArrayList<ArrayList<String>> getDeliveryData() {
		return deliveryData;
	}

	private ArrayList<ArrayList<String>> getMatches2016() {
		ArrayList<ArrayList<String>> matches2016 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2016")) {
				matches2016.add(match);
			}
		}

		return matches2016;
	}

}
