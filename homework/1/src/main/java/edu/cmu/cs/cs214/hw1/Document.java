package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author dsai96 This document class creates frequency tables for each input
 *         URL. These frequency tables are hash maps of words mapped to their
 *         frequencies. Then we are able to find cosSim using the provided
 *         formula.
 */
public class Document {
	private String url;
	private Scanner documentScanner;
	private HashMap<String, Integer> frequencyTable;

	/**
	 * This constructor sets the fields up for when a new Document object is created.
	 * @param url is a valid url.
	 * @throws IOException this is to check that the url is valid. 
	 */
	public Document(String url) throws IOException {
		this.url = url;
		this.documentScanner = new Scanner(new URL(url).openStream());
		this.frequencyTable = new HashMap<String, Integer>();
		createFrequencyTable();
	}

	private void createFrequencyTable() {
		while (documentScanner.hasNext()) {
			String word = documentScanner.next();
			if (frequencyTable.containsKey(word))
				frequencyTable.put(word, frequencyTable.get(word) + 1);
			else
				frequencyTable.put(word, 1);
		}
	}

	/**
	 * 
	 * @param other is a document object 
	 * @return the cosSim value between those two documents
	 */
	public double cosSim(Document other) {
		double numerator = 0;

		for (String key : frequencyTable.keySet()) {
			if (other.frequencyTable.keySet().contains(key)) {
				numerator += (frequencyTable.get(key) * (other.frequencyTable.get(key)));
			}
		}
		double den1 = 0;
		double den2 = 0;
		for (String key : frequencyTable.keySet()) {
			den1 += Math.pow((frequencyTable.get(key)), 2);
		}
		for (String key : other.frequencyTable.keySet()) {
			den2 += Math.pow((other.frequencyTable.get(key)), 2);
		}
		return numerator / (Math.sqrt(den1) * Math.sqrt(den2));
	}

	@Override
	public String toString() {
		return url;
	}

}
