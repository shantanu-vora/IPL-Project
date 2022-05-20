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


}
