package com.arunsp.pokerhand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.Hand;
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

	private RoyalFlushValidator royalFlushValidator;

	private StraightFlushValidator straightFlushValidator;

	private FourOfAKindValidator fourOfAKindValidator;

	private FullHouseValidator fullHouseValidator;

	private FlushValidator flushValidator;

	private StraightValidator straightValidator;

	private ThreeOfAKindValidator threeOfAKindValidator;

	private TwoPairsValidator twoPairsValidator;

	private PairValidator pairValidator;

	@Autowired
	public HandValidatorTest(RoyalFlushValidator royalFlushValidator,
			StraightFlushValidator straightFlushValidator,
			FourOfAKindValidator fourOfAKindValidator,
			FullHouseValidator fullHouseValidator,
			FlushValidator flushValidator,
			StraightValidator straightValidator,
			ThreeOfAKindValidator threeOfAKindValidator,
			TwoPairsValidator twoPairsValidator,
			PairValidator pairValidator) {
	}

	/**
	 * Ten, Jack, Queen, King and Ace in the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testRoyalFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TH", "KH", "AH", "QH", "JH" };
		Hand hand = royalFlushValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotRoyalFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TH", "JH", "AH", "QH", "JH" };
		Hand hand = royalFlushValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * All five cards in consecutive value order, with the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testStraightFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JS", "8S", "7S" };
		Hand hand = straightFlushValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotStraightFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		Hand hand = straightFlushValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * Four cards of the same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFourOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "6S", "6C", "6S", "5S", "6D" };
		Hand hand = fourOfAKindValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotFourOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		Hand hand = fourOfAKindValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * Three of a kind and a Pair.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFullHouse() throws InvalidCardException, InvalidDealException {
		String[] deal = { "5C", "6S", "6D", "5S", "6H" };
		Hand hand = fullHouseValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotFullHouse() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		Hand hand = fullHouseValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * All five cards having the same suit.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JS", "8S", "7S" };
		Hand hand = flushValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		Hand hand = flushValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * All five cards in consecutive value order.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testStraight() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TH", "9D", "JS", "8C", "7S" };
		Hand hand = straightValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotStraight() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		Hand hand = straightValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * Three cards of the same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testThreeOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "9H", "9D", "JS", "8C", "9S" };
		Hand hand = threeOfAKindValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotThreeOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		Hand hand = threeOfAKindValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * Two different pairs.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testTwoPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "9H", "9D", "JS", "8C", "JS" };
		Hand hand = twoPairsValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotTwoPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		Hand hand = twoPairsValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}

	/**
	 * Two cards of same value.
	 * 
	 * @throws InvalidCardException
	 * @throws InvalidDealException
	 */
	@Test
	public void testPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "9H", "9D", "KS", "8C", "JS" };
		Hand hand = pairValidator.validateAndRank(deal);
		assertThat(hand).isNotNull();
	}

	@Test
	public void testNotPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		Hand hand = pairValidator.validateAndRank(deal);
		assertThat(hand).isNull();
	}
}
