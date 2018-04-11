package kata.tennis;

public class SimplifiedTennisMatchFormatter {

    public static String formatScore(SimplifiedTennisMatch simplifiedTennisMatch) {
        int player1Points = simplifiedTennisMatch.getPlayer1Points();
        String player1 = simplifiedTennisMatch.getPlayer1();
        int player2Points = simplifiedTennisMatch.getPlayer2Points();
        String player2 = simplifiedTennisMatch.getPlayer2();

        if (player1Points >= 4 && player1Points - player2Points > 1) {
            return player1 + " wins!";
        } else if (player2Points >= 4 && player2Points - player1Points > 1) {
            return player2 + " wins!";
        }

        String score = new StringBuilder()
                .append(player1 + " - " + player2 + "\n")
                .append(formatMatchScores(player1Points, player2Points))
                .toString();
        return score;
    }

    private static String formatMatchScores(int player1Points, int player2Points) {
        if (player1Points >=4) {
            return player1Points > player2Points ? "ADVANTAGE - " : "DEUCE - DEUCE";
        } else if (player2Points >= 4) {
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
