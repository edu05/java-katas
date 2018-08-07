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

    public String formatScore() {
        Score score = getMatchScore();
        if (score.getWinner().isPresent()) {
            return score.getWinner().get() + " wins!";
        }
        return new StringBuilder()
                .append(score.getPlayer1() + " - " + score.getPlayer2() + "\n")
                .append(formatMatchScores(score))
                .toString();
    }

    private boolean hasPlayer1Won() {
        return player1Points >= MINIMUM_WIN_SCORE && player1Points - player2Points > WIN_MARGIN_REQUIRED;
    }

    private boolean hasPlayer2Won() {
        return player2Points >= MINIMUM_WIN_SCORE && player2Points - player1Points > WIN_MARGIN_REQUIRED;
    }

    private String formatMatchScores(Score score) {
        if (score.isPlayer1Advantage()) {
            return "ADVANTAGE - ";
        } else if (score.isPlayer2Advantage()) {
            return " - ADVANTAGE";
        } else if (score.isDeuce()) {
            return "DEUCE";
        } else {
            return formatPoints(score.getPlayer1Points()) + " - " + formatPoints(score.getPlayer2Points());
        }
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
        Score score = getMatchScore();
        return formatScoreModern(score);
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
