/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.areSameSuit;
import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;

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

	@Override
	public HandResult validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);
		if (areSameSuit(deal)) {
			return new HandResult(deal, HandType.FLUSH);
		}
		return null;
	}
}
