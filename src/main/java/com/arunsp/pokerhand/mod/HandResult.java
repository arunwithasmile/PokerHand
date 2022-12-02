/**
 * 
 */
package com.arunsp.pokerhand.mod;

/**
 * @author Arun S P
 *
 */
public class HandResult {

	private String[] deal;
	private HandType hand;

	public HandResult(String[] deal, HandType hand) {
		super();
		this.deal = deal;
		this.hand = hand;
	}

	public String[] getDeal() {
		return deal;
	}

	public HandType getHand() {
		return hand;
	}
}
