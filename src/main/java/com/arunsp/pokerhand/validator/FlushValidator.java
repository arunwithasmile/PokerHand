/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.areSameSuit;
import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * Checks if a deal is a Flush hand.
 * <p>
 * A Flush Hand is simply all cards of same Suit.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(5)
public class FlushValidator implements HandValidator {

	private static final int RANK = 6;

	@Override
	public int validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);
		return areSameSuit(deal) ? RANK : INVALID_HAND_RANK;
	}
}
