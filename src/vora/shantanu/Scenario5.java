package vora.shantanu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	/*
		getMatches2017() method returns a nested arrayList filtered with only
		rows(matches) played in Season year 2017. It accesses season year by index 1
	 */
	private ArrayList<ArrayList<String>> getMatches2017() {
		ArrayList<ArrayList<String>> matches2017 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2017")) {
				matches2017.add(match);
			}
		}
		return matches2017;
	}

	/*
		getDeliveriesAndRunsMap()  method accepts a nested arrayList representing only
		matches played in 2017. It returns a hashmap with the batter name as key and
		an array list as value containing balls faced at index 0 and runs scored at index 1
		{
			<bowler_name> : [<balls_delivered>, <runs_conceded>]
		}
		matchId is accessed at index 0. Batter name is accessed at index 6. Total runs scored are
		accessed at index 15
		It checks if map contains batter name. If not, then initializes arraylist with 1 at index 0
		and totalRuns scored at index 1. If yes, then increments the deliveries faced count at index 0 by 1 and
		scored runs count at index 1 by totalRuns.
	 */
	private HashMap<String, ArrayList<Double>> getDeliveriesAndRunsMap(ArrayList<ArrayList<String>> matches2017) {
		HashMap<String, ArrayList<Double>> map = new HashMap<>();

		for(ArrayList<String> match: matches2017) {
			String matchId = match.get(0);

			for(ArrayList<String> delivery: deliveryData) {
				if(delivery.get(0).equals(matchId)) {
					String key = delivery.get(6);

					if(!map.containsKey(key)) {
						map.put(key, new ArrayList<>());
						map.get(key).add(0, 1.0);
						map.get(key).add(1, Double.parseDouble(delivery.get(15)));
					} else {
						map.get(key).set(0, map.get(key).get(0) + 1.0);
						map.get(key).set(1, map.get(key).get(1) + Double.parseDouble(delivery.get(15)));
						map.put(key, map.get(key));
					}
				}
			}
		}
		return map;
	}

	/*
		constructMap() method accepts a hashmap as a parameter and returns a hashmap.
		It loops the entry set and counts the deliveries faced and runs scored for each key(batter name)
		It calculates strikeRate and initializes batter with the strikeRate and returns a new map.
	 */
	private HashMap<String, Double> constructMap(HashMap<String, ArrayList<Double>> map) {
		HashMap<String, Double> strikeRateMap = new HashMap<>();

		for(Map.Entry<String, ArrayList<Double>> m: map.entrySet()) {
			Double deliveries = map.get(m.getKey()).get(0);
			Double runs = map.get(m.getKey()).get(1);
			Double strikeRate = (runs / deliveries) * 100.0;

			strikeRateMap.put(m.getKey(), strikeRate);
		}
		return strikeRateMap;
	}

	public void printOutput() {
		System.out.println("For the year 2017, the strike rate of all the batters:");
		System.out.println(constructMap(getDeliveriesAndRunsMap(getMatches2017())));
	}
}
