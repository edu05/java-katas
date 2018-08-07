package kata.refactoringtennis;

public class NewStyleFormatter implements SimpleTennisMatchFormatter {

    @Override
    public String formatScore(Score score) {
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
}
