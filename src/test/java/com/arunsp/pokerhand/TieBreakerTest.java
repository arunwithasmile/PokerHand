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

}
