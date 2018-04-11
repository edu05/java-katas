package kata.tennis;

public class SimplifiedTennisMatch {

    private final String player1;
    private final String player2;

    private int player1Points = 0;
    private int player2Points = 0;

    private boolean finishedGame = false;

    public SimplifiedTennisMatch(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void score(String playerName) {
        if (playerName.equals(player1)) {
            player1Points++;
        } else if (playerName.equals(player2)) {
            player2Points++;
        }

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
}
