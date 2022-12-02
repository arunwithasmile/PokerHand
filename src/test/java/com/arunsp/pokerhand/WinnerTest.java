/**
 * 
 */
package com.arunsp.pokerhand;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arunsp.pokerhand.exception.InvalidDataException;
import com.arunsp.pokerhand.mod.PlayResult;
import com.arunsp.pokerhand.service.PokerHandGame;

/**
 * Testing different scenarios of winners.
 * 
 * @author Arun S P
 *
 */
@SpringBootTest
public class WinnerTest {

	private final PokerHandGame pokerHandGame;

	@Autowired
	public WinnerTest(PokerHandGame pokerHandGame) {
		this.pokerHandGame = pokerHandGame;
	}

	@Test
	public void testPairWinner() {
		String deal = "8S TH 3C 2H 3S 7H KS QH 3H 6C";
		try {
			PlayResult result = pokerHandGame.play(Arrays.asList(deal));
			Assertions.assertThat(result.getPlayerACount()).isNotZero();
		} catch (InvalidDataException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testRoyalWinner() {
		String deal = "8S TH 3C 2H 3S TH KH QH AH JH";
		try {
			PlayResult result = pokerHandGame.play(Arrays.asList(deal));
			Assertions.assertThat(result.getPlayerBCount()).isNotZero();
		} catch (InvalidDataException e) {
			Assertions.fail(e.getMessage());
		}
	}
}
