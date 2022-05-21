package vora.shantanu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<ArrayList<String>> deliveryData = new ArrayList<>();
		ArrayList<ArrayList<String>> matchData = new ArrayList<>();

		readMatches(matchData);
		readDeliveries(deliveryData);

//		System.out.println(matchData);
		Scenario1 scenario1 = new Scenario1(matchData);
		Scenario2 scenario2 = new Scenario2(matchData);
		Scenario3 scenario3 = new Scenario3(matchData, deliveryData);
		Scenario4 scenario4 = new Scenario4(matchData, deliveryData);
		Scenario5 scenario5 = new Scenario5(matchData, deliveryData);
		scenario1.printOutput();
		scenario2.printOutput();
		scenario3.printOutput();
		scenario4.printOutput();
		scenario5.printOutput();


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