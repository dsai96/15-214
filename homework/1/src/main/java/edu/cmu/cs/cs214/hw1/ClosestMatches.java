package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author dsai96
 *  This class is to check to see which of the urls inputted are closest to each other. 
 */

public class ClosestMatches {

	/**
	 * Prints the closest match to each url that is inputted. 
	 * @param args these are an absolute URL provided.
	 * @throws IOException this is to check that the url is valid. 
	 */
	public static void main(String[] args) throws IOException {
		// take allCombinations of urls and maps the url to an array of
		// highestCosSim values for that url
		Set<HighestCosSim> allCombinations = new HashSet<>();
		for (int i = 0; i < args.length; i++) {
			String url1 = args[i];
			Document doc1 = new Document(url1);
			for (int j = 0; j < i; j++) {
				String url2 = args[j];
				Document doc2 = new Document(url2);
				double cosSim = doc1.cosSim(doc2);
				HighestCosSim currentComparision = new HighestCosSim(url1, url2, cosSim);
				HighestCosSim reversedComparision = new HighestCosSim(url2, url1, cosSim);
				allCombinations.add(currentComparision);
				allCombinations.add(reversedComparision);
			}
		}
		// finds highestCosSim value and prints the urls
		for (String url : args) {
			Double currHighestCosSim = 0.0;
			String highestURL = "";
			for (HighestCosSim x : allCombinations) {
				if (url.equals(x.getUrl1())) {
					if (currHighestCosSim < x.getCosSim()) {
						currHighestCosSim = x.getCosSim();
						highestURL = x.getUrl2();
					}
				}
			}
		System.out.println(url + " " + highestURL);	
		}
	}
}
