package kata.tennis;

import java.util.Optional;

public class SimplifiedTennisMatch {

    private final String player1;
    private final String player2;

    private int player1Points = 0;
    private int player2Points = 0;

    private boolean finishedGame = false;
    private String winner = null;

    public SimplifiedTennisMatch(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void score(String playerName) {
        if (finishedGame) {
            throw new RuntimeException("Game has already finished");
        }

        if (playerName.equals(player1)) {
            player1Points++;
        } else if (playerName.equals(player2)) {
            player2Points++;
        }

        finishedGame = hasPlayerWon(playerName);
    }

    private boolean hasPlayerWon(String playerName) {
        if (playerName.equals(player1)) {
            return player1Points >= 4 && player1Points - player2Points > 1;
        } else if (playerName.equals(player2)) {
            return player2Points >=4 && player2Points - player1Points > 1;
        }
        return false;
    }

    int getPlayer1Points() {
        return player1Points;
    }

    String getPlayer1() {
        return player1;
    }

    int getPlayer2Points() {
        return player2Points;
    }

    String getPlayer2() {
        return player2;
    }

    boolean isFinishedGame() {
        return finishedGame;
    }

    public Optional<String> getWinner() {
        return Optional.ofNullable(winner);
    }
}
