package projectdeliverable;

    import java.util.ArrayList;
    import java.util.List;

    public class BlackjackGame {
        private GroupOfCard deck;
        private List<Player> players;
        private Player dealer;

        public BlackjackGame() {
            deck = new GroupOfCard();
            players = new ArrayList<>();
            dealer = new Player("Dealer");
        }

        // Register a new player
        public void registerPlayer(String playerName) {
            players.add(new Player(playerName));
        }

        // Method to get the list of players
        public List<Player> getPlayers() {
            return players;
        }

        public void startNewRound() {
            deck.shuffle();
            dealer.clearHand();
            for (Player player : players) {
                player.clearHand();
            }

            // Deal initial cards to players and dealer
            for (Player player : players) {
                player.addCardToHand(deck.drawCard());
                player.addCardToHand(deck.drawCard());
            }
            dealer.addCardToHand(deck.drawCard());
            dealer.addCardToHand(deck.drawCard());
        }

        public void playerTurn(Player player) {
            while (true) {
                System.out.println(player.getName() + "'s hand: " + player.showHand());
                System.out.println(player.getName() + "'s score: " + player.getScore());
                System.out.print(player.getName() + ", do you want to hit or stay? (h/s): ");
                String action = player.getInput().toLowerCase();

                if (action.equals("h")) {
                    player.addCardToHand(deck.drawCard());
                    if (player.isBust()) {
                        System.out.println(player.getName() + " busts!");
                        break;
                    }
                } else if (action.equals("s")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'h' to hit or 's' to stay.");
                }
            }
        }

        public void dealerTurn() {
            while (dealer.getScore() < 17) {
                dealer.addCardToHand(deck.drawCard());
            }
            System.out.println("Dealer's hand: " + dealer.showHand());
            System.out.println("Dealer's score: " + dealer.getScore());
        }

        public void determineWinners() {
            int dealerScore = dealer.getScore();
            for (Player player : players) {
                int playerScore = player.getScore();

                System.out.println(player.getName() + "'s final score: " + playerScore);
                if (playerScore > 21) {
                    System.out.println(player.getName() + " busts. Dealer wins!");
                } else if (dealerScore > 21 || playerScore > dealerScore) {
                    System.out.println(player.getName() + " wins!");
                } else if (playerScore < dealerScore) {
                    System.out.println(player.getName() + " loses. Dealer wins!");
                } else {
                    System.out.println(player.getName() + " ties with the dealer.");
                }
            }
        }
    }
