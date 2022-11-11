/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;
import static com.arunsp.pokerhand.validator.HandValidator.assertValidDeal;
import static com.arunsp.pokerhand.validator.HandValidator.getCountMap;

import java.util.Collection;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

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
	public int validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Lets get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Lets get see if we find pair occurrence twice.
		int pairsCount = 0;
		Collection<Integer> counts = valueCounts.values();
		for (Integer count : counts) {
			if (count == 2) {
				pairsCount++;
			}
		}

		// Return RANK if we found it, or ZERO otherwise.
		return pairsCount == 2 ? RANK : INVALID_HAND_RANK;
	}
}
