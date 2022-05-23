package vora.shantanu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

	public static final int ID = 0;
	public static final int SEASON_YEAR = 1;
	public static final int WINNER = 10;
	public static final int MATCH_ID = 0;
	public static final int BOWLING_TEAM = 3;
	public static final int EXTRA_RUNS = 16;
	public static final int BOWLER = 8;
	public static final int TOTAL_RUNS = 17;
	public static final int BATTER_RUNS = 15;
	public static final int BATTER = 6;

	public static void main(String[] args) throws IOException {
		List<Match> match = getMatchesData();
		List<Delivery> delivery = getDeliveriesData();

		findNumberOfMatchesPlayedPerSeason(match);
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
			match.setId(matchFields[ID]); //0
			match.setSeason(matchFields[SEASON_YEAR]); //1
			match.setWinner(matchFields[WINNER]); //10
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
			delivery.setMatchId(deliveryFields[MATCH_ID]); //0
			delivery.setBowlingTeam(deliveryFields[BOWLING_TEAM]); //3
			delivery.setExtraRuns(deliveryFields[EXTRA_RUNS]); //16
			delivery.setBowler(deliveryFields[BOWLER]); // 8
			delivery.setTotalRuns(deliveryFields[TOTAL_RUNS]); //17
			delivery.setBatterRuns(deliveryFields[BATTER_RUNS]); // 15
			delivery.setBatter(deliveryFields[BATTER]); // 6
			deliveries.add(delivery);
		}

		return deliveries;
	}

	/*
	* findNumberOfMatchesPlayedPerSeason() method checks for every row representing a match in list matches
		whether the Season number(year) is already present in the map as a key. If not it
		initializes the key(Season number) with 1, If yes, it just increments the value by 1.
		It accesses season year by index 1.
	* */
	private static void findNumberOfMatchesPlayedPerSeason(List<Match> matches) {
		Map<String, Integer> matchesPlayedPerYear = new HashMap<>();

		for(Match match: matches) {
			String season = match.getSeason(); //key
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

	private static HashMap<String,double[]> findDeliveriesAndRunsPerBatter(List<Match> matches, List<Delivery> deliveries) {
		HashMap<String, double[]> deliveriesAndRunsPerBatter = new HashMap<>();

		for(Match match: matches) {
			if(match.getSeason().equals("2017")) {
				for(Delivery delivery: deliveries) {
					if(match.getId().equals(delivery.getMatchId())) {
						String batter = delivery.getBatter();

						if(!deliveriesAndRunsPerBatter.containsKey(batter)) {

							deliveriesAndRunsPerBatter.put(batter, new double[2]);
							//total runs conceded accessed at index 17
							deliveriesAndRunsPerBatter.get(batter)[0] = 1.0;
							deliveriesAndRunsPerBatter.get(batter)[1] = Double.parseDouble(delivery.getBatterRuns());
						} else {
							deliveriesAndRunsPerBatter.get(batter)[0]++;
							deliveriesAndRunsPerBatter.get(batter)[1] += Double.parseDouble(delivery.getBatterRuns());
						}
					}
				}
			}
		}
		return deliveriesAndRunsPerBatter;
	}

	private static void findStrikeRateOfBattersIn2017(HashMap<String,double[]> deliveriesAndRunsPerBatter) {
		HashMap<String, Double> strikeRatePerBatter = new HashMap<>();

		for(Map.Entry<String, double[]> entry: deliveriesAndRunsPerBatter.entrySet()) {
			Double deliveries = deliveriesAndRunsPerBatter.get(entry.getKey())[0];
			Double runs = deliveriesAndRunsPerBatter.get(entry.getKey())[1];
			Double strikeRate = (runs / deliveries) * 100.0;

			strikeRatePerBatter.put(entry.getKey(), strikeRate);
		}
		System.out.println(strikeRatePerBatter);
	}
}

