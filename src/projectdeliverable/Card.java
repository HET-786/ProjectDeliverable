/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package projectdeliverable;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        switch (rank) {
            case ACE:
                return 11; // Ace is 11 initially
            case KING:
            case QUEEN:
            case JACK:
                return 10; // Face cards are worth 10
            default:
                return rank.getValue(); // Numeric cards return their own value
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
