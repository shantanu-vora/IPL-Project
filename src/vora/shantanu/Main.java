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

	public static void main(String[] args) {
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

	private static List<Match> getMatchesData() {
		List<Match> matches = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader("./matches.csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// to skip the first row of headers
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		return matches;
	}

	private static List<Delivery> getDeliveriesData() {
		List<Delivery> deliveries = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader("./deliveries.csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// to skip the first row of headers
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		return deliveries;
	}

	/*
	* findNumberOfMatchesPlayedPerSeason() method checks for every row representing a match in list matches
		whether the Season number(year) is already present in the map as a key. If not it
		initializes the key(Season number) with 1, If yes, it just increments the value by 1.

	* */
	private static void findNumberOfMatchesPlayedPerSeason(List<Match> matches) {
		Map<String, Integer> matchesPlayedPerYear = new HashMap<>();

		for(Match match: matches) {
			//season acts as the key
			String season = match.getSeason();
			if(!matchesPlayedPerYear.containsKey(season)) {
				matchesPlayedPerYear.put(season, 1);
			} else {
				matchesPlayedPerYear.put(season, matchesPlayedPerYear.get(season) + 1);
			}
		}

		System.out.println(matchesPlayedPerYear);
	}

		/*
		findNumberOfMatchesWonPerTeamOverAllSeasons() method checks for every row representing a match in list matches
		whether the Team name is already present in the map as a key. If not it
		initializes the key(Team name) with 1. If yes, it just increments the value by 1.

	 */
	private static void findNumberOfMatchesWonPerTeamOverAllSeasons(List<Match> matches) {
		Map<String, Integer> matchesWonPerTeam = new HashMap<>();

		for(Match match: matches) {
			//winner acts as the key
			String winner = match.getWinner();
			// result can be no_result. Hence, key can be empty string
			if(!winner.equals("")) {
				if(!matchesWonPerTeam.containsKey(winner)) {
					matchesWonPerTeam.put(winner, 1);
				} else {
					matchesWonPerTeam.put(winner, matchesWonPerTeam.get(winner) + 1);
				}
			}
		}

		System.out.println(matchesWonPerTeam);
	}

		/*
		findExtraRunsConcededPerTeamIn2016() method accepts two lists matches and deliveries representing
		one match and delivery by one row. It returns a hashmap. It loops matches and for every match played in 2016
		it also loops deliveries. It compares id in matches list and matchId in deliveries list.
		If it matches then key is initialized with Bowling team name.	It initializes extras with corresponding extra runs.
		Then checks if the map contains key(bowling team). If not, initialize the key with extras.
		If yes, then just add extras to the original extras
	 */
	private static void findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
		Map<String, Integer> extraRunsConcededPerTeamIn2016 = new HashMap<>();

		for(Match match: matches) {
			if(match.getSeason().equals("2016")) {
				for(Delivery delivery: deliveries) {
					if(match.getId().equals(delivery.getMatchId())) {
						//bowlingTeam acts as the key
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

		/*
		findDeliveriesAndRunsPerBowler()  method accepts two lists matches and deliveries representing
		match and delivery. It returns a hashmap with the bowler name as key and
		an array as value containing balls delivered at index 0 and runs conceded at index 1
		{
			<bowler_name> : [<balls_delivered>, <runs_conceded>]
		}

		It loops matches and for every match played in 2015 it loops deliveries list. It compares id in matches
		and matchId in deliveries. If it matches, then it checks if map contains bowler name.
		If not, then initializes bowler key with empty array with 1 at index 0 and totalRuns at index1.
		If yes, then increment the delivery count at index 0 by 1 and conceded runs count at index 1 by totalRuns.
	 */
	private static HashMap<String, double[]> findDeliveriesAndRunsPerBowler(List<Match> matches, List<Delivery> deliveries) {
		HashMap<String, double[]> deliveriesAndRunsPerBowler = new HashMap<>();

		for(Match match: matches) {
			if(match.getSeason().equals("2015")) {
				for(Delivery delivery: deliveries) {
					if(match.getId().equals(delivery.getMatchId())) {
						String bowler = delivery.getBowler();

						if(!deliveriesAndRunsPerBowler.containsKey(bowler)) {

							deliveriesAndRunsPerBowler.put(bowler, new double[2]);
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

		/*
		findTopEconomicalBowlerIn2015() method accepts a hashmap as a parameter and returns a hashmap.
		It loops the entry set and counts the deliveries and runs conceded for each key(bowler name)
		It calculates economyRate and initializes bowler with the economyRate and prints map economyRatePerBowler.
	 */
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

		/*
		findDeliveriesAndRunsPerBatter()  method accepts two lists matches and deliveries representing
		match and delivery. It returns a hashmap with the batter name as key and
		an array as value containing balls faced at index 0 and runs scored at index 1
		{
			<batter_name> : [<balls_faced>, <runs_scored>]
		}

		It loops matches and for every match played in 2017 it loops deliveries list. It compares id in matches
		and matchId in deliveries. If it matches, then it checks if map contains batter name.
		If not, then initializes batter key with empty array with 1 at index 0 and batterRuns at index1.
		If yes, then increment the delivery count at index 0 by 1 and scored runs count at index 1 by batterRuns.
	 */
	private static HashMap<String,double[]> findDeliveriesAndRunsPerBatter(List<Match> matches, List<Delivery> deliveries) {
		HashMap<String, double[]> deliveriesAndRunsPerBatter = new HashMap<>();

		for(Match match: matches) {
			if(match.getSeason().equals("2017")) {
				for(Delivery delivery: deliveries) {
					if(match.getId().equals(delivery.getMatchId())) {
						String batter = delivery.getBatter();

						if(!deliveriesAndRunsPerBatter.containsKey(batter)) {

							deliveriesAndRunsPerBatter.put(batter, new double[2]);
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

	  /*
		findStrikeRateOfBattersIn2017() method accepts a hashmap as a parameter and returns a hashmap.
		It loops the entry set and counts the deliveries faced and runs scored for each key(batter name)
		It calculates strikeRate and initializes batter with the strikeRate and prints map strikeRatePerBatter
	 */
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

