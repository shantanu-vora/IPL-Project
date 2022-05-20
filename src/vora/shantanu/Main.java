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
		ArrayList<String> match;
		ArrayList<String> delivery;

		FileReader matchReader = new FileReader("./matches.csv");
		FileReader deliveryReader = new FileReader("./deliveries.csv");

		BufferedReader bufferedReader = new BufferedReader(matchReader);
		String[] matchHeaders = bufferedReader.readLine().split(",");
//		System.out.println(Arrays.asList(matchHeaders));

		String line;
		while((line = bufferedReader.readLine()) != null) {
			match = new ArrayList<>(Arrays.asList((line.split(","))));
			matchData.add(match);
		}

		bufferedReader = new BufferedReader(deliveryReader);
		String[] deliveryHeaders = bufferedReader.readLine().split(",");
//		System.out.println(Arrays.asList(deliveryHeaders));

		while((line = bufferedReader.readLine()) != null) {
			delivery = new ArrayList<>(Arrays.asList((line.split(","))));
			deliveryData.add(delivery);
		}




	}
}