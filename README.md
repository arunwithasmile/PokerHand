# Poker Hand

## Assumptions
1. There will be at least one line (deal) in the file.
2. Each deal will have 10 cards, 5 each for each player.
3. All the letters in the file will be in capital letters.
5. Each card is represented with a two-character string. First character is the card number and the second is the suit.
5. First character is in the range of 2 to 9 or one among { T, J, Q, K, A }. Representing Ten, Jack, Queen, King, and Ace respectively
6. Always the higest possible hand is considered for the play. That is, if a set of cards results in Three of a Kind & Flush, Flush will be presented for comparison against the opoonent. Similarly, Between Flush and Four of a Kind, Four of a Kind is the candidate.
7. One line will result in one win only. That means no of player 1 hands, player 2 hands, and draws always add up to the no of lines provided in the file.

## Definition of Each Hand
### Royal Flush
All the Cards are under same Suit and the Cards are the higest. That is, from 10 to Ace. Ex: TC JC QC KC AC

### Straight Flush
All the Cards are under same Suit and the Cards are in consecutive value order. Ex: 5C 6C 7C 8C 9C

### Four of a Kind
At least 4 cards are of same value.  Ex: TC TS TH KD AC

### Full House
There is a triplet of cards of same value along with a pair of cards of same value. Ex: 3C 3D QC 3C QD

### Flush
All the cards are under same Suit. Ex: 3C 5C 1C KC 8C

### Straight
All the cards are in consecutive value order.  Ex: 2D 3S 4C 5S 6H

### Three of a Kind
At least 3 cards are of same value.  Ex: 2D 4S 4C 5S 4H

### Two pairs
There are two sets of cards with a matching pair.  Ex: 2D 2S 4C 5S 5H

### Pair
There is only one pair of cards with same value.  Ex: 4D 3S 4C 5S 6H

## Running the Application
Have the jar `poker-hand-1.0.0.jar` and `poker-had.txt` at the same location and run the following command

```java -jar poker-hand-1.0.0.jar poker-had.txt```

Or import the project into any IDE and run the Java file `PokerHandApplication.java`