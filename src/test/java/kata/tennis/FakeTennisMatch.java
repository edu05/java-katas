package kata.tennis;

public class FakeTennisMatch extends SimplifiedTennisMatch {

    private final int player1Points;
    private final int player2Points;

    public FakeTennisMatch(String player1, String player2, int player1Points, int player2Points) {
        super(player1, player2);
        this.player1Points = player1Points;
        this.player2Points = player2Points;
    }

    @Override
    int getPlayer1Points() {
        return player1Points;
    }

    @Override
    int getPlayer2Points() {
        return player2Points;
    }
}
