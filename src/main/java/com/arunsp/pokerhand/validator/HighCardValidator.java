/**
 * 
 */
package com.arunsp.pokerhand.validator;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;

/**
 * This is the tie breaker. It will take both the deals and compare card by card
 * to find out who gets it.
 * 
 * @author Arun S P
 *
 */
@Component
@Order(10)
public class HighCardValidator implements HandValidator {

	@Override
	public HandResult validateAndRank(String[] deal) throws InvalidCardException, InvalidDealException {
		return new HandResult(deal, HandType.HIGH_CARD);
	}
}
