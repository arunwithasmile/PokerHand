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
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;

/**
 * Checks if a deal is a Three of a Kind hand.
 * <p>
 * A Deal is a Three of a Kind hand if at least 3 cards are of same value.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(7)
public class ThreeOfAKindValidator implements HandValidator {

	@Override
	public HandResult validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);

		// Let's get the count of each card value.
		Map<Integer, Integer> valueCounts = getCountMap(deal);

		// Now check if either of them had 4 occurrences
		Integer match = checkOccurrences(valueCounts, 3);

		if (match != null) {
			return new HandResult(filterCardsByValue(deal, match), HandType.THREE_OF_A_KIND);
		}

		return null;
	}
}
