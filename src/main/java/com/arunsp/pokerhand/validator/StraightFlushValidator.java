/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.areAdjacent;
import static com.arunsp.pokerhand.util.CardsUtil.areSameSuit;
import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;

/**
 * Checks if a deal is a Straight Flush hand.
 * <p>
 * A Deal is a Straight Flush hand if all of the cards are in the same Suit and
 * the cards are in consecutive value order.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(2)
public class StraightFlushValidator implements HandValidator {

	@Override
	public HandResult validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException {
		assertValidDeal(deal);

		// Verify if all of them are in the same suit
		if (!areSameSuit(deal)) {
			return null;
		}

		// Verify if all of them are adjacent
		if (!areAdjacent(deal)) {
			return null;
		}

		return new HandResult(deal, HandType.STRAIGHT_FLUSH);
	}
}
