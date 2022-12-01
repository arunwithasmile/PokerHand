/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.CardsUtil.checkOccurrences;
import static com.arunsp.pokerhand.util.CardsUtil.getCountMap;
import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * @author Arun S P
 *
 */
@Component
@Order(9)
public class PairValidator implements HandValidator {
	private static final int RANK = 2;

	@Override
	public int validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Let's get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Now check if either of them had 4 occurrences
		boolean match = checkOccurrences(valueCounts, 2);

		if (match) {
			return RANK;
		}

		return INVALID_HAND_RANK;
	}
}
