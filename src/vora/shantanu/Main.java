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

	private static void findNumberOfMatchesWonPerTeamOverAllSeasons(List<Match> matches) {
		Map<String, Integer> matchesWonPerTeam = new HashMap<>();

		for(Match match: matches) {
			String winner = match.getWinner(); // result can be no_result
			if(!winner.equals("")) {	 // Hence, key can be empty string
				if(!matchesWonPerTeam.containsKey(winner)) {
					matchesWonPerTeam.put(winner, 1);
				} else {
					matchesWonPerTeam.put(winner, matchesWonPerTeam.get(winner) + 1);
				}
			}
		}
		System.out.println(matchesWonPerTeam);
	}

	private static void findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
		Map<String, Integer> extraRunsConcededPerTeamIn2016 = new HashMap<>();

		for(Match match: matches) {

			if(match.getSeason().equals("2016")) {
				for(Delivery delivery: deliveries) {

					if(match.getId().equals(delivery.getMatchId())) {
						String bowlingTeam = delivery.getBowlingTeam();
						int extras = Integer.parseInt(delivery.getExtraRuns());

						if(!extraRunsConcededPerTeamIn2016.containsKey(bowlingTeam)) {
							extraRunsConcededPerTeamIn2016.put(bowlingTeam, extras);
						} else {
							extraRunsConcededPerTeamIn2016.put(bowlingTeam, extraRunsConcededPerTeamIn2016.get(bowlingTeam) + extras);
						}
					}
				}
			}
		}

		System.out.println(extraRunsConcededPerTeamIn2016);
	}

	private static HashMap<String, double[]> findDeliveriesAndRunsPerBowler(List<Match> matches, List<Delivery> deliveries) {
		HashMap<String, double[]> deliveriesAndRunsPerBowler = new HashMap<>();

		for(Match match: matches) {
			if(match.getSeason().equals("2015")) {
				for(Delivery delivery: deliveries) {
					if(match.getId().equals(delivery.getMatchId())) {
						String bowler = delivery.getBowler();

						if(!deliveriesAndRunsPerBowler.containsKey(bowler)) {

							deliveriesAndRunsPerBowler.put(bowler, new double[2]);
							//total runs conceded accessed at index 17
							deliveriesAndRunsPerBowler.get(bowler)[0] = 1.0;
							deliveriesAndRunsPerBowler.get(bowler)[1] = Double.parseDouble(delivery.getTotalRuns());
						} else {
							deliveriesAndRunsPerBowler.get(bowler)[0]++;
							deliveriesAndRunsPerBowler.get(bowler)[1] += Double.parseDouble(delivery.getTotalRuns());
						}
					}
				}
			}
		}
		return deliveriesAndRunsPerBowler;
	}

	private static void findTopEconomicalBowlerIn2015(HashMap<String, double[]> deliveriesAndRunsPerBowler) {
		Map<String, Double> economyRatePerBowler = new HashMap<>();

		for (Map.Entry<String, double[]> entry : deliveriesAndRunsPerBowler.entrySet()) {
			double deliveries = deliveriesAndRunsPerBowler.get(entry.getKey())[0];
			double runs = deliveriesAndRunsPerBowler.get(entry.getKey())[1];
			Double economyRate = (runs) / (deliveries / 6.0);

			economyRatePerBowler.put(entry.getKey(), economyRate);
		}

		System.out.println(economyRatePerBowler);
	}
}

