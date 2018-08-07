package kata.refactoringtennis;

public class OldStyleFormatter implements SimpleTennisMatchFormatter {

    @Override
    public String formatScore(Score score) {
        if (score.getWinner().isPresent()) {
            return score.getWinner().get() + " wins!";
        }

        return new StringBuilder()
                .append(score.getPlayer1() + " - " + score.getPlayer2() + "\n")
                .append(formatMatchScores(score))
                .toString();
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
}
