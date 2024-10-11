package projectdeliverable;

import java.util.Scanner;

public class GameUI {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");

        // Player registration
        System.out.print("Enter number of players: ");
        int numberOfPlayers = input.nextInt();
        input.nextLine(); // Consume newline
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = input.nextLine();
            game.registerPlayer(playerName);
        }

        boolean playing = true;
        while (playing) {
            game.startNewRound();

            // Each player's turn
            for (Player player : game.getPlayers()) {
                game.playerTurn(player);
            }

            // Dealer's turn
            game.dealerTurn();

            // Determine winners and announce results
            game.determineWinners();

            // Play again prompt
            System.out.print("Do you want to play again? (y/n): ");
            playing = input.nextLine().equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");
        input.close();
    }
}
