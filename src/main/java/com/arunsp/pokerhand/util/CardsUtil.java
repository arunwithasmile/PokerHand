/**
 * 
 */
package com.arunsp.pokerhand.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * @author Arun S P
 *
 */
public class CardsUtil {

	public static boolean areSameSuit(String[] deal) throws InvalidCardException {
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

	public static int[] getSortedValues(String[] deal) throws InvalidCardException {
		int[] cardValues = new int[5];
		for (int i = 0; i < 5; i++) {
			cardValues[i] = cardValue(deal[i]);
		}

		// Sorting them
		Arrays.sort(cardValues);
		return cardValues;
	}

	public static boolean areAdjacent(String[] deal) throws InvalidCardException {
		// Let's extract all the card values and put them into a sorted array
		int[] cardValues = getSortedValues(deal);

		// Now for each of them to be adjacent, difference between the highest and the
		// lowest cards is always 4
		// Ex: 2, 3, 4, 5, 6 -> 6 - 2 = 4
		// 4, 5, 6, 7, 8 -> 8 - 4 = 4
		// T, J, Q, K, A -> 14 - 10 = 4

		return cardValues[4] - cardValues[0] == 4;
	}

	/**
	 * Counts the no of occurrences of each card value and presents them as a Map.
	 * 
	 * @param deal List of strings representing valid cards to check values of.
	 * @return The map containing the counts of each card number.
	 * @throws InvalidCardException If a card is not recognizable
	 */
	public static Map<Integer, Integer> getCountMap(String[] deal) throws InvalidCardException {
		Map<Integer, Integer> valueCounts = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			valueCounts.merge(cardValue(deal[i]), 1, (currentCount, ONE) -> currentCount + 1);
		}
		return valueCounts;
	}

	/**
	 * Checks for the no of occurrences in the map containing counts of each card
	 * value.
	 * 
	 * @param valueCounts
	 * @param requiredCount
	 * @return The card value if the count matches. Null otherwise.
	 */
	public static Integer checkOccurrences(Map<Integer, Integer> valueCounts, int requiredCount) {
		for (Entry<Integer, Integer> entry : valueCounts.entrySet()) {
			if (entry.getValue() == requiredCount) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static void assertValidDeal(String[] deal) throws InvalidDealException {
		if (deal.length != 5) {
			throw new InvalidDealException(
					"The Deal '+deal+' is invalid. A player should have 5 cards in a deal. Found " + deal.length);
		}
	}

	public static void assertValidCard(String card) throws InvalidCardException {
		if (!Pattern.matches("[2-9TJQKA][SHDC]", card)) {
			throw new InvalidCardException("Card '" + card
					+ "' is invalid. A Card string consists of two characters with first representing the Card number and the second representing the Suit");
		}
	}

	public static int cardValue(String card) throws InvalidCardException {
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
			throw new InvalidCardException("Card '" + card
					+ "' is invalid. The first letter can only be between 2 and 9 (inclusive) or one of { T, J, Q, K, A }");
		}
	}

	/**
	 * This will return all the cards that are of a given card value. i.e. it will
	 * return all the cards that start with a given card number.
	 * 
	 * @param deal  Array of cards.
	 * @param match Card value to match.
	 * @return An array of cards that matched the card value.
	 */
	public static String[] filterCardsByValue(String[] deal, Integer match) {
		return Arrays.asList(deal).stream().filter(card -> {
			try {
				return cardValue(card) == match;
			} catch (InvalidCardException e) {
				return false;
			}
		}).toArray(String[]::new);
	}
}
