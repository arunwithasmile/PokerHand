/**
 * 
 */
package com.arunsp.pokerhand.validator;

import static com.arunsp.pokerhand.validator.HandValidator.getSortedValues;

import org.springframework.stereotype.Service;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.util.Player;

/**
 * This is the tie breaker. It will take both the deals and compare card by card
 * to find who gets it.
 * 
 * @author Arun S P
 *
 */
@Service
public class HighCardValidator {

	public Player breakTie(String[] dealA, String[] dealB) throws InvalidDealException, InvalidCardException {

		// Getting sorted values of each hand
		int[] playerACardValues = getSortedValues(dealA);
		int[] playerBCardValues = getSortedValues(dealB);

		// Now compare one by one
		for (int i = 0; i < 5; i++) {
			if (playerACardValues[i] > playerBCardValues[i]) {
				return Player.PLAYER_A;
			} else if (playerBCardValues[i] > playerACardValues[i]) {
				return Player.PLAYER_B;
			}
		}
		// They are same all the way. So returning no winner
		return null;
	}
}
