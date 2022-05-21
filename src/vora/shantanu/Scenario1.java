package vora.shantanu;

import java.util.ArrayList;
import java.util.HashMap;

public class Scenario1 {
	private ArrayList<ArrayList<String>> matchData;

	public Scenario1(ArrayList<ArrayList<String>> matchData) {
		this.matchData = matchData;
	}

	public ArrayList<ArrayList<String>> getMatchData() {
		return matchData;
	}

	/*
		constructMap() method checks for every row representing a match in list matchData
		whether the Season number(year) is already present in the map as a key. If not it
		initializes the key(Season number) with 1, If yes, it just increments the value by 1.
		It accesses season year by index 1.
	 */
	private HashMap<String, Integer> constructMap() {
		HashMap<String, Integer> map = new HashMap<>();

		for(ArrayList<String> match: matchData) {
			String key = match.get(1);
			if(!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.put(key, map.get(key) + 1);
			}
		}

		return map;
	}

	public void printOutput() {
		System.out.println(constructMap());
	}
}
