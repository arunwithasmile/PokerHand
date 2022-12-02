/**
 * 
 */
package com.arunsp.pokerhand.service;

import static com.arunsp.pokerhand.util.CardsUtil.cardValue;
import static com.arunsp.pokerhand.util.Constants.SPACE;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDataException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
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

	@Autowired
	public PokerHandGame(List<HandValidator> validators, HighCardValidator highCardValidator) {
		this.validators = validators;
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
		HandResult playerAHand = playDeal(playerACards);

		// Finding Rank of player B
		HandResult playerBHand = playDeal(playerBCards);

		// Return the winner.
		Player winner;

		if (playerAHand.getHand().getRank() > playerBHand.getHand().getRank()) {
			winner = Player.PLAYER_A;
		} else if (playerAHand.getHand().getRank() < playerBHand.getHand().getRank()) {
			winner = Player.PLAYER_B;
		} else { // Draw.
			winner = breakTie(playerAHand.getDeal(), playerBHand.getDeal());
		}

		return winner;
	}

	private HandResult playDeal(String[] cards) throws InvalidCardException, InvalidDealException {
		// We have autowired all the Hand Validators into 'validators' list. We will
		// check for each hand starting from the highest Rank.
		HandResult identifiedHand = null;
		for (HandValidator validator : validators) { // Looping through each validator.

			identifiedHand = validator.validateAndRank(cards);

			// If found a valid hand, we will break the loop and consider the returned rank
			// to check winner
			if (identifiedHand != null) {
				break;
			}
		}
		return identifiedHand;
	}

	private String[] getCards(String deal) throws InvalidDealException {
		String[] cards = deal.split(SPACE);
		if (cards.length != 10) {
			throw new InvalidDealException(
					"Deal string '" + deal + "' is invalid. A Deal should contain 10 cards. Found " + cards.length);
		}
		return cards;
	}

	/**
	 * This will break tie between
	 * 
	 * @param dealA
	 * @param dealB
	 * @return
	 * @throws InvalidCardException
	 */
	private Player breakTie(String[] dealA, String[] dealB) throws InvalidCardException {
		if (dealA.length != dealB.length) {
			throw new RuntimeException("Ideally both deals should have the same length for the tie breaker");
		}
		Player leading = null;
		int highestCard = 0;
		for (int i = 0; i < dealA.length; i++) {
			int cardAValue = cardValue(dealA[i]);
			int carBValue = cardValue(dealB[i]);
			if (cardAValue == carBValue) {
				continue;
			}

			if (cardAValue > highestCard) {
				leading = Player.PLAYER_A;
				highestCard = cardAValue;
			} else if (carBValue > highestCard) {
				leading = Player.PLAYER_B;
				highestCard = carBValue;
			}
		}
		return leading;
	}
}
