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