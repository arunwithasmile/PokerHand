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
 * Testing Tie Breaking scenarios.
 * 
 * @author Arun S P
 *
 */
@SpringBootTest
public class TieBreakerTest {

	private final PokerHandGame pokerHandGame;

	@Autowired
	public TieBreakerTest(PokerHandGame pokerHandGame) {
		this.pokerHandGame = pokerHandGame;
	}

	@Test
	public void testTieBreak() {
		String deal = "AS TC 8C 3H 3C AD 5H 8D 5C 2H";
		try {
			PlayResult result = pokerHandGame.play(Arrays.asList(deal));
			Assertions.assertThat(result.getPlayerBCount()).isNotZero();
		} catch (InvalidDataException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testDraw() {
		String deal = "2H AS TD 2D 6C 2C 2S TC 9H 5D";
		try {
			PlayResult result = pokerHandGame.play(Arrays.asList(deal));
			Assertions.assertThat(result.getDrawCount()).isNotZero();
		} catch (InvalidDataException e) {
			Assertions.fail(e.getMessage());
		}
	}

	/**
	 * Both are Straight Flushes but player 2 is the winner in tie break.
	 */
	@Test
	public void testStraightFlushDraw() {
		String deal = "9H TH 8H 7H JH KD QD JD TD AD";
		try {
			PlayResult result = pokerHandGame.play(Arrays.asList(deal));
			Assertions.assertThat(result.getPlayerBCount()).isNotZero();
		} catch (InvalidDataException e) {
			Assertions.fail(e.getMessage());
		}
	}
}
