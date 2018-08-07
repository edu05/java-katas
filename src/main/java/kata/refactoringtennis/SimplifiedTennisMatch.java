package kata.refactoringtennis;

public class SimplifiedTennisMatch {

    public static final int MINIMUM_WIN_SCORE = 4;
    public static final int WIN_MARGIN_REQUIRED = 3;
    private final String player1;
    private final String player2;

    private int player1Points = 0;
    private int player2Points = 0;

    public SimplifiedTennisMatch(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void score(String playerName) {
        if (hasPlayer1Won() || (hasPlayer2Won())) {
            throw new UnsupportedOperationException("Match has already finished, can't keep scoring");
        }

        if (playerName.equals(player1)) {
            player1Points++;
        } else if (playerName.equals(player2)) {
            player2Points++;
        }
    }

    public String formatScore(SimpleTennisMatchFormatter simpleTennisMatchFormatter) {
        return simpleTennisMatchFormatter.formatScore(getMatchScore());
    }

    private boolean hasPlayer1Won() {
        return player1Points >= MINIMUM_WIN_SCORE && player1Points - player2Points > WIN_MARGIN_REQUIRED;
    }

    private boolean hasPlayer2Won() {
        return player2Points >= MINIMUM_WIN_SCORE && player2Points - player1Points > WIN_MARGIN_REQUIRED;
    }

    private Score getMatchScore() {
        String winner = null;
        boolean isDeuce = false;
        boolean isPlayer1Advantage = false;
        boolean isPlayer2Advantage = false;

        if (hasPlayer1Won()) {
            winner = player1;
        } else if (hasPlayer2Won()) {
            winner = player2;
        } else if ((player1Points >= MINIMUM_WIN_SCORE || player2Points >= MINIMUM_WIN_SCORE) && player1Points == player2Points) {
            isDeuce = true;
        } else if ((player1Points >= MINIMUM_WIN_SCORE || player2Points >= MINIMUM_WIN_SCORE) && player1Points > player2Points) {
            isPlayer1Advantage = true;
        } else if ((player1Points >= MINIMUM_WIN_SCORE || player2Points >= MINIMUM_WIN_SCORE) && player2Points > player1Points) {
            isPlayer2Advantage = true;
        }
        return new Score(player1, player2, player1Points, player2Points, winner, isDeuce, isPlayer1Advantage, isPlayer2Advantage);
    }
}
