package projectdeliverable;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


public class GroupOfCard {
    private List<Card> deck;

    public GroupOfCard() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            System.out.println("Deck is empty! Reshuffling...");
            // Reset and reshuffle the deck if it’s empty
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
            shuffle();
        }
        return deck.remove(deck.size() - 1);
    }
}
