/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;
import static com.arunsp.pokerhand.validator.HandValidator.areAdjacent;
import static com.arunsp.pokerhand.validator.HandValidator.areSameSuit;
import static com.arunsp.pokerhand.validator.HandValidator.assertValidDeal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

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

	private static final int RANK = 9;

	@Override
	public int validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException {
		assertValidDeal(deal);

		// Verify if all of them are in the same suit
		if (!areSameSuit(deal)) {
			return INVALID_HAND_RANK;
		}

		// Verify if all of them are adjacent
		if (!areAdjacent(deal)) {
			return INVALID_HAND_RANK;
		}

		return RANK;
	}
}
