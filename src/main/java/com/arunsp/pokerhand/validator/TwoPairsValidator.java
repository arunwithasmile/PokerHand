/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.CardsUtil.filterCardsByValue;
import static com.arunsp.pokerhand.util.CardsUtil.getCountMap;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.Hand;

/**
 * Checks if a deal is a Two Pair hand.
 * <p>
 * A Deal is a Two Pair hand if there are two pairs of cards of same value.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(8)
public class TwoPairsValidator implements HandValidator {
	private static final int RANK = 3;

	@Override
	public Hand validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Let's get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Let's get see if we find pair occurrence twice.
		int pairsCount = 0;
		String[] matchedCards = new String[0];
		for (Entry<Integer, Integer> entry : valueCounts.entrySet()) {
			if (entry.getValue() == 2) {
				String[] pair = filterCardsByValue(deal, entry.getKey());
				matchedCards = fillMatchedCards(matchedCards, pair);
				pairsCount++;
			}
		}

		// Return RANK if we found it, or ZERO otherwise.
		return pairsCount == 2 ? new Hand(matchedCards, RANK) : null;
	}

	private String[] fillMatchedCards(String[] matchedCards, String[] pair) {
		int size = matchedCards.length;
		String[] mergedCards = grow(matchedCards);
		for (int i = 0; i < 2; i++) {
			mergedCards[size + i] = pair[i];
		}
		return mergedCards;
	}

	private String[] grow(String[] array) {
		String[] newArray = new String[array.length + 2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}
}
