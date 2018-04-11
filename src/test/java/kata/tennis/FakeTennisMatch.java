package kata.tennis;

import java.util.Optional;

public class FakeTennisMatch extends SimplifiedTennisMatch {

    private final int player1Points;
    private final int player2Points;
    private final boolean isFinishedGame;
    private final int minimumWinPoints;
    private final String winner;

    public FakeTennisMatch(String player1, String player2, int player1Points, int player2Points, String winner, int minimumWinPoints) {
        super(player1, player2);
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.isFinishedGame = winner != null;
        this.minimumWinPoints = minimumWinPoints;
        this.winner = winner;
    }

    public static FakeTennisMatch unstartedMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 0, 0, null, 4);
    }

    public static FakeTennisMatch unsfinishedMatch(String player1, String player2, int player1Points, int player2Points) {
        return new FakeTennisMatch(player1, player2, player1Points, player2Points, null, player1Points + player2Points);
    }

    public static FakeTennisMatch player1WonFinishedMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 4, 0, player1, 4);
    }

    public static FakeTennisMatch deuceMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 4, 4, null, 4);
    }

    public static FakeTennisMatch firstPlayerAdvantageMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 5, 4, null, 4);
    }

    @Override
    int getPlayer1Points() {
        return player1Points;
    }

    @Override
    int getPlayer2Points() {
        return player2Points;
    }

    @Override
    public boolean isFinishedGame() {
        return isFinishedGame;
    }

    @Override
    int getMinimumWinPoints() {
        return minimumWinPoints;
    }

    @Override
    Optional<String> getWinner() {
        return Optional.of(winner);
    }
}
