package com.arunsp.pokerhand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.validator.FlushValidator;
import com.arunsp.pokerhand.validator.FourOfAKindValidator;
import com.arunsp.pokerhand.validator.FullHouseValidator;
import com.arunsp.pokerhand.validator.PairValidator;
import com.arunsp.pokerhand.validator.RoyalFlushValidator;
import com.arunsp.pokerhand.validator.StraightFlushValidator;
import com.arunsp.pokerhand.validator.StraightValidator;
import com.arunsp.pokerhand.validator.ThreeOfAKindValidator;
import com.arunsp.pokerhand.validator.TwoPairsValidator;

@SpringBootTest
public class HandValidatorTest {

	@Autowired
	private RoyalFlushValidator royalFlushValidator;

	@Autowired
	private StraightFlushValidator straightFlushValidator;

	@Autowired
	private FourOfAKindValidator fourOfAKindValidator;

	@Autowired
	private FullHouseValidator fullHouseValidator;

	@Autowired
	private FlushValidator flushValidator;

	@Autowired
	private StraightValidator straightValidator;

	@Autowired
	private ThreeOfAKindValidator threeOfAKindValidator;

	@Autowired
	private TwoPairsValidator twoPairsValidator;

	@Autowired
	private PairValidator pairValidator;

	/**
	 * Ten, Jack, Queen, King and Ace in the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testRoyalFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TH", "KH", "AH", "QH", "JH" };
		int rank = royalFlushValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotRoyalFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TH", "JH", "AH", "QH", "JH" };
		int rank = royalFlushValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * All five cards in consecutive value order, with the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testStraightFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JS", "8S", "7S" };
		int rank = straightFlushValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotStraightFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JH", "8S", "7H" };
		int rank = straightFlushValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * Four cards of the same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFourOfAKind() throws InvalidCardException, InvalidDealException {
		String[] hand = { "6S", "6C", "6S", "5S", "6D" };
		int rank = fourOfAKindValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotFourOfAKind() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JH", "8S", "7H" };
		int rank = fourOfAKindValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * Three of a kind and a Pair.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFullHouse() throws InvalidCardException, InvalidDealException {
		String[] hand = { "5C", "6S", "6D", "5S", "6H" };
		int rank = fullHouseValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotFullHouse() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JH", "8S", "7H" };
		int rank = fullHouseValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * All five cards having the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JS", "8S", "7S" };
		int rank = flushValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotFlush() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TS", "9S", "JH", "8S", "7H" };
		int rank = flushValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * All five cards in consecutive value order.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testStraight() throws InvalidCardException, InvalidDealException {
		String[] hand = { "TH", "9D", "JS", "8C", "7S" };
		int rank = straightValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotStraight() throws InvalidCardException, InvalidDealException {
		String[] hand = { "2S", "9S", "JH", "8S", "7H" };
		int rank = straightValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * Three cards of the same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testThreeOfAKind() throws InvalidCardException, InvalidDealException {
		String[] hand = { "9H", "9D", "JS", "8C", "9S" };
		int rank = threeOfAKindValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotThreeOfAKind() throws InvalidCardException, InvalidDealException {
		String[] hand = { "2S", "9S", "JH", "8S", "7H" };
		int rank = threeOfAKindValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * Two different pairs.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testTwoPair() throws InvalidCardException, InvalidDealException {
		String[] hand = { "9H", "9D", "JS", "8C", "JS" };
		int rank = twoPairsValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotTwoPair() throws InvalidCardException, InvalidDealException {
		String[] hand = { "2S", "9S", "JH", "8S", "7H" };
		int rank = twoPairsValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}

	/**
	 * Two cards of same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testPair() throws InvalidCardException, InvalidDealException {
		String[] hand = { "9H", "9D", "KS", "8C", "JS" };
		int rank = pairValidator.validateAndRank(hand);
		assertThat(rank).isPositive();
	}

	@Test
	public void testNotPair() throws InvalidCardException, InvalidDealException {
		String[] hand = { "2S", "9S", "JH", "8S", "7H" };
		int rank = pairValidator.validateAndRank(hand);
		assertThat(rank).isZero();
	}
}
