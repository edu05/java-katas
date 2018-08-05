package kata.refactoringtennis;

import java.util.Optional;

public class Score {

    private final String player1;
    private final String player2;
    private final int player1Points;
    private final int player2Points;
    private final Optional<String> winner;
    private final boolean isDeuce;
    private final boolean isPlayer1Advantage;
    private final boolean isPlayer2Advantage;

    public Score(String player1, String player2, int player1Points, int player2Points, String winner, boolean isDeuce, boolean isPlayer1Advantage, boolean isPlayer2Advantage) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.winner = Optional.ofNullable(winner);
        this.isDeuce = isDeuce;
        this.isPlayer1Advantage = isPlayer1Advantage;
        this.isPlayer2Advantage = isPlayer2Advantage;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public Optional<String> getWinner() {
        return winner;
    }

    public boolean isDeuce() {
        return isDeuce;
    }

    public boolean isPlayer1Advantage() {
        return isPlayer1Advantage;
    }

    public boolean isPlayer2Advantage() {
        return isPlayer2Advantage;
    }
}
