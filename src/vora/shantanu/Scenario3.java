package vora.shantanu;

import java.util.ArrayList;
import java.util.HashMap;

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

	private HashMap<String, Integer> constructMap(ArrayList<ArrayList<String>> matches2016) {
		HashMap<String, Integer> map = new HashMap<>();
		for(ArrayList<String> match: matches2016) {
			String matchId = match.get(0);
			for(ArrayList<String> delivery : deliveryData) {
				if(delivery.get(0).equals(matchId)) {
					String key = delivery.get(3);
					int extras = Integer.parseInt(delivery.get(16));
					if(!map.containsKey(key)) {
						map.put(key, extras);
					} else {
						map.put(key, map.get(key) + extras);
					}
				}
			}
		}
		return map;
	}
}
