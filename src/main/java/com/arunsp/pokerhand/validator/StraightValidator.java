/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.areAdjacent;
import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.Constants.INVALID_HAND_RANK;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;

/**
 * Checks if a deal is a Straight hand.
 * <p>
 * A Deal is a Straight hand if all of the cards are in consecutive value order.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(6)
public class StraightValidator implements HandValidator {

	private static final int RANK = 5;

	@Override
	public int validateAndRank(String[] deal) throws InvalidDealException, InvalidCardException {
		assertValidDeal(deal);
		return areAdjacent(deal) ? RANK : INVALID_HAND_RANK;
	}
}
