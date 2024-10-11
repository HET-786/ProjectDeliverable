package projectdeliverable;
import java.util.Scanner;



public class GameUI {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        Scanner input = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            blackjackGame.startNewRound();

            System.out.println("Your hand:");
            System.out.println(blackjackGame.getPlayerHand());
            System.out.println("Dealer is showing: " + blackjackGame.getDealerHand().split(", ")[0]);

            // Player's turn
            blackjackGame.playerTurn();

            // Check if player has busted
            if (blackjackGame.getPlayerScore() <= 21) {
                blackjackGame.dealerTurn();
                System.out.println("Dealer's hand:");
                System.out.println(blackjackGame.getDealerHand());
            }

            // Show game result
            System.out.println(blackjackGame.getGameResult());

            // Ask to play again
            System.out.print("Do you want to play again? (y/n): ");
            String playAgain = input.nextLine();
            playing = playAgain.equalsIgnoreCase("y");
        }
        input.close();
    }
}
