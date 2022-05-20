package vora.shantanu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scenario1 {
	private ArrayList<ArrayList<String>> matchData;

	public Scenario1(ArrayList<ArrayList<String>> matchData) {
		this.matchData = matchData;
	}

	public ArrayList<ArrayList<String>> getMatchData() {
		return matchData;
	}

	public void constructMap() {
		Map<String, Integer> map = new HashMap<>();


		for(ArrayList<String> match: matchData) {
			String key = match.get(1);
			if(!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.put(key, map.get(key) + 1);
			}
		}

		System.out.println(map);

	}

}
