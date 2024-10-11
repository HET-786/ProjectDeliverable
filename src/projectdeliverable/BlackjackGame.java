package projectdeliverable;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;


public class BlackjackGame {
    private GroupOfCard deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private Scanner scanner;

    public BlackjackGame() {
        deck = new GroupOfCard();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void startNewRound() {
        deck.shuffle();
        playerHand.clear();
        dealerHand.clear();

        // Deal initial cards
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

    public void playerTurn() {
        while (true) {
            System.out.println("Your hand: " + getPlayerHand());
            System.out.println("Dealer's showing: " + dealerHand.get(0));
            System.out.println("Your current score: " + getPlayerScore());
            System.out.print("Do you want to hit or stay? (h/s): ");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("h")) {
                playerHand.add(deck.drawCard());
                if (getPlayerScore() > 21) {
                    System.out.println("You bust!");
                    break; // End player's turn if they bust
                }
            } else if (action.equals("s")) {
                break; // End player's turn
            } else {
                System.out.println("Invalid input. Please enter 'h' to hit or 's' to stay.");
            }
        }
    }

    public void dealerTurn() {
        while (getDealerScore() < 17) {
            dealerHand.add(deck.drawCard());
        }
    }

    public int getPlayerScore() {
        return calculateScore(playerHand);
    }

    public int getDealerScore() {
        return calculateScore(dealerHand);
    }

    private int calculateScore(List<Card> hand) {
        int total = 0;
        int acesCount = 0;

        for (Card card : hand) {
            total += card.getValue();
            if (card.getRank() == Rank.ACE) {
                acesCount++;
            }
        }

        // Adjust for Aces
        while (total > 21 && acesCount > 0) {
            total -= 10; // Count Ace as 1 instead of 11
            acesCount--;
        }

        return total;
    }

    public String getPlayerHand() {
        return formatHand(playerHand);
    }

    public String getDealerHand() {
        return formatHand(dealerHand);
    }

    public String getGameResult() {
        int playerScore = getPlayerScore();
        int dealerScore = getDealerScore();

        System.out.println("Dealer's hand: " + getDealerHand());
        System.out.println("Your final score: " + playerScore);
        System.out.println("Dealer's final score: " + dealerScore);

        if (playerScore > 21) {
            return "Dealer wins!";
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            return "Player wins!";
        } else if (playerScore < dealerScore) {
            return "Dealer wins!";
        } else {
            return "It's a tie!";
        }
    }

    private String formatHand(List<Card> hand) {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card).append(", ");
        }
        return sb.toString().replaceAll(", $", ""); // Remove trailing comma
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        String playAgain;

        do {
            game.startNewRound();
            game.playerTurn();
            if (game.getPlayerScore() <= 21) {
                game.dealerTurn();
            }
            System.out.println(game.getGameResult());
            System.out.print("Do you want to play again? (y/n): ");
            playAgain = game.scanner.nextLine().toLowerCase();
        } while (playAgain.equals("y"));

        System.out.println("Thanks for playing!");
        game.scanner.close();
    }
}
