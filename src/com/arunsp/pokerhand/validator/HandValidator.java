/**
 * 
 */
package com.arunsp.pokerhand.validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * This interface validates if a hand is a valid hand or not. Each implementer
 * of this interface chooses which hand it wants to validate.
 * 
 * @author Arun S P
 *
 */
public interface HandValidator {

	static boolean areSameSuit(String[] deal) throws InvalidCardException {
		for (int i = 0; i < deal.length - 1; i++) {
			String currentCard = deal[i];
			String nextCard = deal[i + 1];
			assertValidCard(currentCard);
			assertValidCard(nextCard);

			char currentCardSuit = currentCard.charAt(1);
			char nextCardSuit = nextCard.charAt(1);
			if (currentCardSuit != nextCardSuit) {
				return false;
			}
		}
		return true;
	}

	static int[] getSortedValues(String[] deal) throws InvalidCardException {
		int[] cardValues = new int[5];
		for (int i = 0; i < 5; i++) {
			cardValues[i] = cardValue(deal[i]);
		}

		// Sorting them
		Arrays.sort(cardValues);
		return cardValues;
	}

	static boolean areAdjacent(String[] deal) throws InvalidCardException {
		// Let's extract all the card values and put them into a sorted array
		int[] cardValues = getSortedValues(deal);

		// Now for each of them to be adjacent, difference between the highest and the
		// lowest cards is always 4
		// Ex: 2, 3, 4, 5, 6 -> 6 - 2 = 4
		// 4, 5, 6, 7, 8 -> 8 - 4 = 4
		// T, J, Q, K, A -> 14 - 10 = 4

		if (cardValues[4] - cardValues[0] == 4) {
			return true;
		}
		return false;
	}

	static Map<Integer, Integer> getCountMap(String[] deal) throws InvalidCardException {
		Map<Integer, Integer> valueCounts = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			int value = cardValue(deal[i]);
			Integer currentCount = valueCounts.get(value);
			if (currentCount == null) {
				valueCounts.put(value, 1);
			} else {
				valueCounts.put(value, currentCount + 1);
			}
		}
		return valueCounts;
	}

	static boolean checkOccurrences(Map<Integer, Integer> valueCounts, int requiredCount) {
		Collection<Integer> counts = valueCounts.values();
		for (Integer count : counts) {
			if (count == requiredCount) {
				return true;
			}
		}
		return false;
	}

	static void assertValidDeal(String[] deal) throws InvalidDealException {
		if (deal.length != 5) {
			throw new InvalidDealException(
					"The Deal '+deal+' is invalid. A player should have 5 cards in a deal. Found " + deal.length);
		}
	}

	static void assertValidCard(String card) throws InvalidCardException {
		if (!Pattern.matches("[2-9TJQKA][SHDC]", card)) {
			throw new InvalidCardException("Card '" + card
					+ "' is invalid. A Card string consists of two characters with first representing the Card number and the second representing the Suit");
		}
	}

	static int cardValue(String card) throws InvalidCardException {
		if (card.length() != 2) {
			throw new InvalidCardException("Card '" + card + "' is invalid. A Card string consists of two characters");
		}
		char cardNumber = card.toUpperCase().charAt(0);
		switch (cardNumber) {
		case 'A':
			return 14;
		case 'K':
			return 13;
		case 'Q':
			return 12;
		case 'J':
			return 11;
		case 'T':
			return 10;
		default:
			if (cardNumber > '1' && cardNumber <= '9') {
				return cardNumber - '0';
			}
			throw new InvalidCardException(
					"Card '' is invalid. The first letter can only be between 2 and 9 (inclusive) or one of { T, J, Q, K, A }");
		}
	}

	/**
	 * Validates if a deal is of a valid hand and returns it's corresponding Rank
	 * value.
	 * 
	 * @param deal An array of Strings each representing a card
	 * @return Rank of the Hand of valid, 0 otherwise.
	 * @throws InvalidCardException If any of the cards is invalid.
	 * @throws InvalidDealException If the deal does not have 5 cards.
	 */
	public int validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException;
}
