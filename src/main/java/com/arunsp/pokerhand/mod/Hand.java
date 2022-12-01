/**
 * 
 */
package com.arunsp.pokerhand.mod;

/**
 * @author Arun S P
 *
 */
public class Hand {

	private String[] deal;
	private int rank;

	public Hand(String[] deal, int rank) {
		super();
		this.deal = deal;
		this.rank = rank;
	}

	public String[] getDeal() {
		return deal;
	}

	public int getRank() {
		return rank;
	}
}
