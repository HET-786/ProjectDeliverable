package projectdeliverable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Card> hand;
    private Scanner input;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.input = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }
        }

        // Adjust for aces (if score exceeds 21, treat Aces as 1)
        while (aceCount > 0 && score + 10 <= 21) {
            score += 10;
            aceCount--;
        }

        return score;
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public List<Card> getHand() {
        return hand;
    }

    public String showHand() {
        StringBuilder handDisplay = new StringBuilder();
        for (Card card : hand) {
            handDisplay.append(card.toString()).append(" ");
        }
        return handDisplay.toString();
    }

    public String getInput() {
        return input.nextLine();
    }
}
