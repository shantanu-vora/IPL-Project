package vora.shantanu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scenario4 {
	private ArrayList<ArrayList<String>> matchData;
	private ArrayList<ArrayList<String>> deliveryData;

	public Scenario4(ArrayList<ArrayList<String>> matchData, ArrayList<ArrayList<String>> deliveryData) {
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
		getMatches2015() method returns a nested arrayList filtered with only
		rows(matches) played in Season year 2015. It accesses season year by index 1
	 */
	private ArrayList<ArrayList<String>> getMatches2015() {
		ArrayList<ArrayList<String>> matches2015 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2015")) {
				matches2015.add(match);
			}
		}

		return matches2015;
	}

	/*
		getDeliveriesAndRunsMap()  method accepts a nested arrayList representing only
		matches played in 2015. It returns a hashmap with the bowler name as key and
		an array list as value containing balls delivered at index 0 and runs conceded at index 1
		{
			<bowler_name> : [<balls_delivered>, <runs_conceded>]
		}
		matchId is accessed at index 0. Bowler name is accessed at index 8. Total runs conceded are
		accessed at index 17
		It checks if map contains bowler name. If not, then initializes arraylist with 1 at index 0
		and totalRuns at index1. If yes, then increment the delivery count at index 0 by 1 and
		conceded runs count at index 1 by totalRuns.
	 */
	public HashMap<String, ArrayList<Double>> getDeliveriesAndRunsMap(ArrayList<ArrayList<String>> matches2015) {
		HashMap<String, ArrayList<Double>> map = new HashMap<>();
		for(ArrayList<String> match: matches2015) {
			//matchId - index 0
			String matchId = match.get(0);
			for(ArrayList<String> delivery: deliveryData) {
				if(delivery.get(0).equals(matchId)) {
					//bowlerName - index 8
					String key = delivery.get(8);

					if(!map.containsKey(key)) {
						map.put(key, new ArrayList<>());
						//total runs conceded accessed at index 17
						map.get(key).add(0, 1.0);
						map.get(key).add(1, Double.parseDouble(delivery.get(17)));
					} else {
						map.get(key).set(0, map.get(key).get(0) + 1.0);
						map.get(key).set(1, map.get(key).get(1) + Double.parseDouble(delivery.get(17)));
						map.put(key, map.get(key));
					}
				}
			}
		}
		return map;
	}

	/*
		constructMap() method accepts a hashmap as a parameter and returns a hashmap.
		It loops the entry set and counts the deliveries and runs conceded for each key(bowler name)
		It calculates economyRate and initializes bowler with the economyRate and returns a new map.
	 */
	private HashMap<String, Double> constructMap(HashMap<String, ArrayList<Double>> map) {
		HashMap<String, Double> economyMap = new HashMap<>();

		for(Map.Entry<String, ArrayList<Double>> m: map.entrySet()) {
			Double deliveries = map.get(m.getKey()).get(0);
			Double runs = map.get(m.getKey()).get(1);
			Double economyRate = (runs) / (deliveries / 6);

			economyMap.put(m.getKey(), economyRate);
		}
		return economyMap;
	}

	public void printOutput() {
		System.out.println(constructMap(getDeliveriesAndRunsMap(getMatches2015())));
	}

}
