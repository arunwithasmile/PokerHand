/**
 * 
 */
package com.arunsp.pokerhand.validator;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.Hand;

/**
 * This interface validates if a hand is a valid hand or not. Each implementer
 * of this interface chooses which hand it wants to validate.
 * 
 * @author Arun S P
 *
 */
public interface HandValidator {

	/**
	 * Validates if a deal is of a valid hand and returns its corresponding Rank
	 * value.
	 * 
	 * @param deal An array of Strings each representing a card
	 * @return Rank of the Hand of valid, 0 otherwise.
	 * @throws InvalidCardException If any of the cards is invalid.
	 * @throws InvalidDealException If the deal does not have 5 cards.
	 */
	public Hand validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException;
}
