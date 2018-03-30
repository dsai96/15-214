package edu.cmu.cs.cs214.hw1;

import java.io.IOException;

/**
 * 
 * @author dsai96
 * This class HighestCosSim which records the cosSim value between two urls.
 */

public class HighestCosSim {
	private String url1;
	private String url2;
	private Double cosSim;

	/**
	 * 
	 * @param url1 is the first url
	 * @param url2 is the second url to compare with the first url
	 * @param cosSim the cosSim value between those two urls.
	 * @throws IOException needs to check if both urls inputted are valid
	 */
	public HighestCosSim(String url1, String url2, Double cosSim) throws IOException {
		this.url1 = url1;
		this.url2 = url2;
		this.cosSim = cosSim;
		}
	/**
	 * 
	 * @return the cosSim value
	 */
	public Double getCosSim() {
		return cosSim;
	}

	/**
	 * 	
	 * @return url2
	 */
	public String getUrl2() {
		return url2;
	}

	/**
	 * @return url1
	 */
	public String getUrl1() {
		return url1;
	}
}
