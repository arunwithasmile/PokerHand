/**
 * 
 */
package com.arunsp.pokerhand.service;

import static com.arunsp.pokerhand.util.Constants.SPACE;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDataException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.PlayResult;
import com.arunsp.pokerhand.util.Player;
import com.arunsp.pokerhand.validator.HandValidator;
import com.arunsp.pokerhand.validator.HighCardValidator;

/**
 * @author arun
 *
 */
@Service
public class PokerHandGame {

	private final List<HandValidator> validators;

	private final HighCardValidator highCardValidator;

	@Autowired
	public PokerHandGame(List<HandValidator> validators, HighCardValidator highCardValidator) {
		this.validators = validators;
		this.highCardValidator = highCardValidator;
	}

	public PlayResult play(List<String> deals) throws InvalidDataException {
		PlayResult result = new PlayResult();

		// We will be treating each line as one deal. Each deal will be played
		// individually and the result is collated.

		int lineNo = 1;

		try {
			for (String deal : deals) {

				Player winner = playDeal(deal);
				result.updateResult(winner);
				lineNo++;
			}
		} catch (InvalidDealException | InvalidCardException e) {
			throw new InvalidDataException(e, lineNo);
		}
		return result;
	}

	private Player playDeal(String deal) throws InvalidDealException, InvalidCardException {
		// Here we will break the string into two hands for player A and B.
		String[] cards = getCards(deal);
		String[] playerACards = Arrays.copyOfRange(cards, 0, 5);
		String[] playerBCards = Arrays.copyOfRange(cards, 5, 10);

		// Finding Rank of Player A.
		int playerARank = playDeal(playerACards);

		// Finding Rank of player B
		int playerBRank = playDeal(playerBCards);

		// Return the winner.
		Player winner;

		if (playerARank > playerBRank) {
			winner = Player.PLAYER_A;
		} else if (playerBRank > playerARank) {
			winner = Player.PLAYER_B;
		} else { // Draw.
			winner = highCardValidator.breakTie(playerACards, playerBCards);
		}

		return winner;
	}

	private int playDeal(String[] cards) throws InvalidCardException, InvalidDealException {
		// We have autowired all the Hand Validators into 'validators' list. We will
		// check for each hand starting from the highest Rank.
		int rank = 0;
		for (HandValidator validator : validators) { // Looping through each validator.

			rank = validator.validateAndRank(cards);

			// If found a valid hand, we will break the loop and consider the returned rank
			// to check winner
			if (rank > 0) {
				break;
			}
		}
		return rank;
	}

	private String[] getCards(String deal) throws InvalidDealException {
		String[] cards = deal.split(SPACE);
		if (cards.length != 10) {
			throw new InvalidDealException(
					"Deal string '" + deal + "' is invalid. A Deal should contain 10 cards. Found " + cards.length);
		}
		return cards;
	}
}
