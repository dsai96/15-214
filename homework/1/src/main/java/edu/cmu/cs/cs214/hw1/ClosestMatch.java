package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  This class is to check to see what the closest two urls from the bunch that were inputted. 
 * @author dsai96
 *
 */
public class ClosestMatch {

	/**
	 * Prints the two closest matched urls. 
	 * @param args these are an absolute URL provided.
	 * @throws IOException this is to check that the url is valid. 
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<String> mostSimilar = new ArrayList<>();
		double currHighest = 0;
		for (int i = 0; i < args.length; i++) {
			String url1 = args[i];
			Document doc1 = new Document(url1);
			for (int j = 0; j < i; j++) { 
				String url2 = args[j];
				Document doc2 = new Document(url2);
				double cosSim = doc1.cosSim(doc2);
				if (cosSim > currHighest) {
					currHighest = cosSim;
					mostSimilar.clear();
					mostSimilar.add(url1);
					mostSimilar.add(url2);
				}
			}
		}
		for (String url : mostSimilar) {
			System.out.println(url + " ");
		}
	}
}
