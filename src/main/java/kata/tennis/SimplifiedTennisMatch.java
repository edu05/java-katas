package kata.tennis;

import java.util.Optional;

public class SimplifiedTennisMatch {

    private static final int MINIMUM_WIN_POINTS = 7;
    private static final int MINIMUM_WIN_DIFFERENCE = 2;
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

        hasPlayerWon(playerName);
    }

    private boolean hasPlayerWon(String playerName) {
        boolean aPlayerHasWon = (player1Points >= MINIMUM_WIN_POINTS && player1Points - player2Points > MINIMUM_WIN_DIFFERENCE)
                || (player2Points >= MINIMUM_WIN_POINTS && player2Points - player1Points > MINIMUM_WIN_DIFFERENCE);

        if (aPlayerHasWon) {
            winner = playerName;
            finishedGame = aPlayerHasWon;
        }

        return aPlayerHasWon;
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

    public boolean isFinishedGame() {
        return finishedGame;
    }

    Optional<String> getWinner() {
        return Optional.ofNullable(winner);
    }

    int getMinimumWinPoints() {
        return MINIMUM_WIN_POINTS;
    }
}
