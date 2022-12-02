package com.arunsp.pokerhand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arunsp.pokerhand.exception.InvalidCardException;
import com.arunsp.pokerhand.exception.InvalidDealException;
import com.arunsp.pokerhand.mod.HandResult;
import com.arunsp.pokerhand.mod.HandType;
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
	public HandValidatorTest(RoyalFlushValidator royalFlushValidator, StraightFlushValidator straightFlushValidator,
			FourOfAKindValidator fourOfAKindValidator, FullHouseValidator fullHouseValidator,
			FlushValidator flushValidator, StraightValidator straightValidator,
			ThreeOfAKindValidator threeOfAKindValidator, TwoPairsValidator twoPairsValidator,
			PairValidator pairValidator) {
		this.royalFlushValidator = royalFlushValidator;
		this.straightFlushValidator = straightFlushValidator;
		this.fourOfAKindValidator = fourOfAKindValidator;
		this.fullHouseValidator = fullHouseValidator;
		this.flushValidator = flushValidator;
		this.straightValidator = straightValidator;
		this.threeOfAKindValidator = threeOfAKindValidator;
		this.twoPairsValidator = twoPairsValidator;
		this.pairValidator = pairValidator;
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
		HandResult res = royalFlushValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.ROYAL_FLUSH);
	}

	@Test
	public void testNotRoyalFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TH", "JH", "AH", "QH", "JH" };
		HandResult res = royalFlushValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = straightFlushValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.STRAIGHT_FLUSH);
	}

	@Test
	public void testNotStraightFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		HandResult res = straightFlushValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = fourOfAKindValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.FOUR_OF_A_KIND);
	}

	@Test
	public void testNotFourOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		HandResult res = fourOfAKindValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = fullHouseValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.FULL_HOUSE);
	}

	@Test
	public void testNotFullHouse() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		HandResult res = fullHouseValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = flushValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.FLUSH);
	}

	@Test
	public void testNotFlush() throws InvalidCardException, InvalidDealException {
		String[] deal = { "TS", "9S", "JH", "8S", "7H" };
		HandResult res = flushValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = straightValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.STRAIGHT);
	}

	@Test
	public void testNotStraight() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		HandResult res = straightValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = threeOfAKindValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.THREE_OF_A_KIND);
	}

	@Test
	public void testNotThreeOfAKind() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		HandResult res = threeOfAKindValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = twoPairsValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.TWO_PAIR);
	}

	@Test
	public void testNotTwoPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		HandResult res = twoPairsValidator.validateAndRank(deal);
		assertThat(res).isNull();
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
		HandResult res = pairValidator.validateAndRank(deal);
		assertThat(res.getHand()).isEqualTo(HandType.PAIR);
	}

	@Test
	public void testNotPair() throws InvalidCardException, InvalidDealException {
		String[] deal = { "2S", "9S", "JH", "8S", "7H" };
		HandResult res = pairValidator.validateAndRank(deal);
		assertThat(res).isNull();
	}
}
