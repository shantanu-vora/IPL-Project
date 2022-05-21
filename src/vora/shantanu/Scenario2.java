package vora.shantanu;

import java.util.ArrayList;
import java.util.HashMap;

public class Scenario2 {
	private ArrayList<ArrayList<String>> matchData;

	public Scenario2(ArrayList<ArrayList<String>> matchData) {
		this.matchData = matchData;
	}

	public ArrayList<ArrayList<String>> getMatchData() {
		return matchData;
	}

	/*
		constructMap() method checks for every row representing a match in list matchData
		whether the Team name is already present in the map as a key. If not it
		initializes the key(Team name) with 1. If yes, it just increments the value by 1.
		It accesses the team name by index 10.
	 */
	private HashMap<String, Integer> constructMap() {
		HashMap<String, Integer> map = new HashMap<>();

		for(ArrayList<String> match: matchData) {
			String key = match.get(10); // result can be no_result
			if(!key.equals("")) {	 // Hence, key can be empty string
				if(!map.containsKey(key)) {
					map.put(key, 1);
				} else {
					map.put(key, map.get(key) + 1);
				}
			}
		}

		return map;
	}

	public void printOutput() {
		System.out.println("Number of matches won of all teams over all the years of IPL:");
		System.out.println(constructMap());
	}
}
