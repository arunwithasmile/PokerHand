/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.util.CardsUtil.areSameSuit;
import static com.arunsp.pokerhand.util.CardsUtil.assertValidDeal;
import static com.arunsp.pokerhand.util.CardsUtil.cardValue;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;

/**
 * Checks if a deal is a Royal Flush hand.
 * <p>
 * A Deal is a Royal Flush hand if all of the cards are in the same Suit and the
 * cards are Ten, Jack, Queen, King and Ace.
 * </p>
 * 
 * @author Arun S P
 *
 */
@Component
@Order(1)
public class RoyalFlushValidator implements HandValidator {

	@Override
	public HandResult validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException {
		assertValidDeal(deal);

		// Verify if all of them are in the same suit
		if (!areSameSuit(deal)) {
			return null;
		}

		// So for all the cards to be Ten, Jack, Queen, King and Ace, the total sum of
		// all the card numbers will be 60 (10+11+12+13+14)
		// We will just do that.

		int totalCardValue = 0;
		for (String card : deal) {
			totalCardValue += cardValue(card);
		}

		if (totalCardValue == 60) {// Match!
			return new HandResult(deal, HandType.ROYAL_FLUSH);
		}

		return null;
	}
}
