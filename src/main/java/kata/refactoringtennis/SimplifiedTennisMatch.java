package kata.refactoringtennis;

public class SimplifiedTennisMatch {

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

    public String formatScore() {
        if (hasPlayer1Won()) {
            return player1 + " wins!";
        } else if (hasPlayer2Won()) {
            return player2 + " wins!";
        }

        String score = new StringBuilder()
                .append(player1 + " - " + player2 + "\n")
                .append(formatMatchScores())
                .toString();
        return score;
    }

    private boolean hasPlayer1Won() {
        return player1Points >= 4 && player1Points - player2Points > 3;
    }

    private boolean hasPlayer2Won() {
        return player2Points >= 4 && player2Points - player1Points > 3;
    }

    private String formatMatchScores() {
        if (player1Points >= 4) {
            return player1Points > player2Points ? "ADVANTAGE - " : "DEUCE";
        } else if (player2Points >= 4) {
            return player2Points > player1Points ? " - ADVANTAGE" : "DEUCE";
        }

        return formatPoints(player1Points) + " - " + formatPoints(player2Points);
    }

    private String formatPoints(int points) {
        switch (points) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                return Integer.toString(points);
        }
    }

    public String formatScoreModern() {
        /**
         * Part 4 - change null for appropriate Score object
         */
        return formatScoreModern(null);
    }

    private String formatScoreModern(Score score) {
        String player1ScoreString = "  ";
        String player2ScoreString = "  ";
        if (score.getWinner().isPresent() && score.getWinner().get().equals(score.getPlayer1())) {
            player1ScoreString = "WI";
        } else if (score.getWinner().isPresent() && score.getWinner().get().equals(score.getPlayer2())) {
            player2ScoreString = "WI";
        } else if(score.isPlayer1Advantage()) {
            player1ScoreString = "AD";
        } else if (score.isPlayer2Advantage()) {
            player2ScoreString = "AD";
        } else if (score.isDeuce()) {
            player1ScoreString = "DC";
            player2ScoreString = "DC";
        } else {
            player1ScoreString = formatPoints(score.getPlayer1Points());
            player2ScoreString = formatPoints(score.getPlayer2Points());
        }

        return new StringBuilder()
                .append("+-----+------+\n")
                .append("| ")
                .append(score.getPlayer1().substring(0, Math.min(score.getPlayer1().length(), 3)).toUpperCase())
                .append(" |  ")
                .append(player1ScoreString)
                .append("  |\n")
                .append("+-----+------+\n")
                .append("| ")
                .append(score.getPlayer2().substring(0, Math.min(score.getPlayer2().length(), 3)).toUpperCase())
                .append(" |  ")
                .append(player2ScoreString)
                .append("  |\n")
                .append("+-----+------+\n")
                .toString();

    }
}
