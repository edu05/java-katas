package kata.tennis;

public class SimpleSimplifiedTennisMatchFormatter implements SimplifiedTennisMatchFormatter{

    public String format(SimplifiedTennisMatch simplifiedTennisMatch) {
        if (simplifiedTennisMatch.isFinishedGame()) {
            return simplifiedTennisMatch.getWinner().get() + " wins!";
        }

        int player1Points = simplifiedTennisMatch.getPlayer1Points();
        String player1 = simplifiedTennisMatch.getPlayer1();
        int player2Points = simplifiedTennisMatch.getPlayer2Points();
        String player2 = simplifiedTennisMatch.getPlayer2();

        String score = new StringBuilder()
                .append(player1 + " - " + player2 + "\n")
                .append(formatMatchScores(player1Points, player2Points, simplifiedTennisMatch.getMinimumWinPoints()))
                .toString();
        return score;
    }

    private static String formatMatchScores(int player1Points, int player2Points, int minimumWinPoints) {
        if (player1Points >= minimumWinPoints) {
            return player1Points > player2Points ? "ADVANTAGE - " : "DEUCE - DEUCE";
        } else if (player2Points >= minimumWinPoints) {
            return player2Points > player1Points ? " - ADVANTAGE" : "DEUCE - DEUCE";
        }

        return formatPoints(player1Points) + " - " + formatPoints(player2Points);
    }

    private static String formatPoints(int points) {
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
