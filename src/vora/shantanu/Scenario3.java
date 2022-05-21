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

	/*
		getMatches2016() method returns a nested arrayList filtered with only
		rows(matches) played in Season year 2016. It accesses season year by index 1
	 */
	private ArrayList<ArrayList<String>> getMatches2016() {
		ArrayList<ArrayList<String>> matches2016 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2016")) {
				matches2016.add(match);
			}
		}

		return matches2016;
	}

	/*
		constructMap() method accepts an arrayList filtered with matches played in 2016.
		It returns a hashmap. It accesses match_id for every match and compares it with id from
		deliveriesData list. If it matches then key is initialized with Bowling team name indexed by 3.
		It initializes extras with index 16. Then checks if the map contains key(bowling team).
		If not, initialize the key with extras. If yes, then just add extras to the original extras

	 */
	private HashMap<String, Integer> constructMap(ArrayList<ArrayList<String>> matches2016) {
		HashMap<String, Integer> map = new HashMap<>();
		for(ArrayList<String> match: matches2016) {
			// Access match_id by index 0
			String matchId = match.get(0);
			for(ArrayList<String> delivery : deliveryData) {
				if(delivery.get(0).equals(matchId)) {
					// Access bowling team name by index 3 and extras runs by index 16
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

	public void printOutput() {
		System.out.println("For the year 2016 get the extra runs conceded per team:");
		System.out.println(constructMap(getMatches2016()));
	}
}
