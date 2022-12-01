/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.CardsUtil.checkOccurrences;
import static com.arunsp.pokerhand.util.CardsUtil.filterCardsByValue;
import static com.arunsp.pokerhand.util.CardsUtil.getCountMap;

import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.Hand;

/**
 * @author Arun S P
 *
 */
@Component
@Order(9)
public class PairValidator implements HandValidator {
	private static final int RANK = 2;

	@Override
	public Hand validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Let's get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Now check if either of them had 2 occurrences
		Integer match = checkOccurrences(valueCounts, 2);

		if (match != null) {
			return new Hand(filterCardsByValue(deal, match), RANK);
		}

		return null;
	}
}
