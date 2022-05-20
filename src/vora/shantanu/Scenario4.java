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

	private ArrayList<ArrayList<String>> getMatches2015() {
		ArrayList<ArrayList<String>> matches2015 = new ArrayList<>();
		for(ArrayList<String> match: matchData) {
			if(match.get(1).equals("2015")) {
				matches2015.add(match);
			}
		}

		return matches2015;
	}

	public HashMap<String, ArrayList<Double>> deliveriesAndRunsMap(ArrayList<ArrayList<String>> matches2015) {
		HashMap<String, ArrayList<Double>> map = new HashMap<>();
		for(ArrayList<String> match: matches2015) {
			String matchId = match.get(0);
			for(ArrayList<String> delivery: deliveryData) {
				if(delivery.get(0).equals(matchId)) {
					String key = delivery.get(8);
					if(!map.containsKey(key)) {
						map.put(key, new ArrayList<>());
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
		constructMap(deliveriesAndRunsMap(getMatches2015()));
		System.out.println(constructMap(deliveriesAndRunsMap(getMatches2015())));
	}

}
