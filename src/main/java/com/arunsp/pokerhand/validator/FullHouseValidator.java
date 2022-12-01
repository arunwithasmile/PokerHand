/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.CardsUtil.getCountMap;
import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;

import java.util.Collection;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * Checks if a deal is a Full House hand.
 * <p>
 * A Deal is a Full House hand if there are 3 cards and a pair of same value.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(4)
public class FullHouseValidator implements HandValidator {

	private static final int RANK = 7;

	@Override
	public int validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Let's get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Now check if we have 3 & 2 occurrences
		boolean tripletMatch = false;
		boolean pairMatch = false;
		Collection<Integer> counts = valueCounts.values();
		for (Integer count : counts) {
			if (count == 3) {
				tripletMatch = true;
			} else if (count == 2) {
				pairMatch = true;
			}
		}

		return tripletMatch && pairMatch ? RANK : INVALID_HAND_RANK;
	}
}
