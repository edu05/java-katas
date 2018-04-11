package kata.tennis;

public class FakeTennisMatch extends SimplifiedTennisMatch {

    private final int player1Points;
    private final int player2Points;
    private final boolean isFinishedGame;
    private int minimumWinPoints;

    public FakeTennisMatch(String player1, String player2, int player1Points, int player2Points, boolean isFinishedGame, int minimumWinPoints) {
        super(player1, player2);
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.isFinishedGame = isFinishedGame;
        this.minimumWinPoints = minimumWinPoints;
    }

    public static FakeTennisMatch unstartedMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 0, 0, false, 4);
    }

    public static FakeTennisMatch unsfinishedMatch(String player1, String player2, int player1Points, int player2Points) {
        return new FakeTennisMatch(player1, player2, player1Points, player2Points, false, player1Points + player2Points);
    }

    public static FakeTennisMatch player1WonFinishedMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 4, 0, true, 4);
    }

    public static FakeTennisMatch deuceMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 4, 4, true, 4);
    }

    public static FakeTennisMatch firstPlayerAdvantageMatch(String player1, String player2) {
        return new FakeTennisMatch(player1, player2, 5, 4, true, 4);
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
}
