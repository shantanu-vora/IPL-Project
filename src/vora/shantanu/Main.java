package vora.shantanu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		List<Match> match = getMatchesData();
		List<Delivery> delivery = getDeliveriesData();

		findNumberOfMatchesPlayedPerTeam(match);
		findNumberOfMatchesWonPerTeamOverAllSeasons(match);
		findExtraRunsConcededPerTeamIn2016(match, delivery);
		HashMap<String, double[]> deliveriesAndRunsPerBowler = findDeliveriesAndRunsPerBowler(match, delivery);
		findTopEconomicalBowlerIn2015(deliveriesAndRunsPerBowler);
		HashMap<String, double[]> deliveriesAndRunsPerBatter = findDeliveriesAndRunsPerBatter(match, delivery);
		findStrikeRateOfBattersIn2017(deliveriesAndRunsPerBatter);


	}

	private static List<Match> getMatchesData() throws IOException {
		List<Match> matches = new ArrayList<>();

		FileReader fileReader = new FileReader("./matches.csv");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		bufferedReader.readLine();
		String line;

		while((line = bufferedReader.readLine()) != null) {
			String[] matchFields = line.split(",");

			Match match = new Match();
			match.setId(matchFields[0]);
			match.setSeason(matchFields[1]);
			match.setWinner(matchFields[10]);
			matches.add(match);
		}

		return matches;
	}

	private static List<Delivery> getDeliveriesData() throws IOException {
		List<Delivery> deliveries = new ArrayList<>();

		FileReader fileReader = new FileReader("./deliveries.csv");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		bufferedReader.readLine();
		String line;

		while((line = bufferedReader.readLine()) != null) {
			String[] deliveryFields = line.split(",");

			Delivery delivery = new Delivery();
			delivery.setMatchId(deliveryFields[0]);
			delivery.setBowlingTeam(deliveryFields[3]);
			delivery.setExtraRuns(deliveryFields[16]);
			delivery.setBowler(deliveryFields[8]);
			delivery.setTotalRuns(deliveryFields[17]);
			delivery.setBatterRuns(deliveryFields[15]);
			delivery.setBatter(deliveryFields[6]);
			deliveries.add(delivery);
		}

		return deliveries;
	}

	private static void findNumberOfMatchesPlayedPerTeam(List<Match> matches) {
		Map<String, Integer> matchesPlayedPerYear = new HashMap<>();

		for(Match match: matches) {
			String season = match.getSeason();
			if(!matchesPlayedPerYear.containsKey(season)) {
				matchesPlayedPerYear.put(season, 1);
			} else {
				matchesPlayedPerYear.put(season, matchesPlayedPerYear.get(season) + 1);
			}
		}
		System.out.println(matchesPlayedPerYear);
	}
}

