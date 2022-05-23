package vora.shantanu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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

	public static void readMatches(ArrayList<ArrayList<String>> matchData) throws IOException {
		FileReader matchReader = new FileReader("./matches.csv");
		BufferedReader bufferedReader = new BufferedReader(matchReader);
		String[] matchHeaders = bufferedReader.readLine().split(",");
		String line;
		while((line = bufferedReader.readLine()) != null) {
			ArrayList<String> match = new ArrayList<>(Arrays.asList((line.split(","))));
			matchData.add(match);
		}
	}

	public static void readDeliveries(ArrayList<ArrayList<String>> deliveryData) throws IOException {
		FileReader deliveryReader = new FileReader("./deliveries.csv");
		BufferedReader bufferedReader = new BufferedReader(deliveryReader);
		String[] deliveryHeaders = bufferedReader.readLine().split(",");
		String line;
		while((line = bufferedReader.readLine()) != null) {
			ArrayList<String> delivery = new ArrayList<>(Arrays.asList((line.split(","))));
			deliveryData.add(delivery);
		}
	}
}