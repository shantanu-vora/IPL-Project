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

	private ArrayList<ArrayList<String>> getMatches2017() {
		ArrayList<ArrayList<String>> matches2017 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2017")) {
				matches2017.add(match);
			}
		}
		return matches2017;
	}

	private HashMap<String, ArrayList<Double>> deliveriesAndRunsMap(ArrayList<ArrayList<String>> matches2017) {
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
		System.out.println(constructMap(deliveriesAndRunsMap(getMatches2017())));
	}

}
