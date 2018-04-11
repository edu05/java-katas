package kata.tennis;

public class MintSimplifiedTennisMatchFormatter implements SimplifiedTennisMatchFormatter {

    public String format(SimplifiedTennisMatch simplifiedTennisMatch) {
        String player1 = simplifiedTennisMatch.getPlayer1();
        String player2 = simplifiedTennisMatch.getPlayer2();
        String player1ScoreString = "  ";
        String player2ScoreString = "  ";
        if (simplifiedTennisMatch.isFinishedGame()) {
            player1ScoreString = simplifiedTennisMatch.getWinner().get().equals(player1) ? "WI" : "LO";
            player2ScoreString = simplifiedTennisMatch.getWinner().get().equals(player2) ? "WI" : "LO";
        } else {
            int player1Points = simplifiedTennisMatch.getPlayer1Points();
            int player2Points = simplifiedTennisMatch.getPlayer2Points();
            if (player1Points >= simplifiedTennisMatch.getMinimumWinPoints() || player2Points >= simplifiedTennisMatch.getMinimumWinPoints()) {
                if (player1Points > player2Points) {
                    player1ScoreString = "AD";
                } else if (player1Points == player2Points) {
                    player1ScoreString = "DC";
                    player2ScoreString = "DC";
                } else {
                    player2ScoreString = "AD";
                }
            } else {
                player1ScoreString = formatPoints(player1Points);
                player2ScoreString = formatPoints(player2Points);
            }
        }
        return new StringBuilder()
                .append("+-----+------+\n")
                .append("| ")
                .append(player1.substring(0, Math.min(player1.length(), 3)).toUpperCase())
                .append(" |  ")
                .append(player1ScoreString)
                .append("  |\n")
                .append("+-----+------+\n")
                .append("| ")
                .append(player2.substring(0, Math.min(player2.length(), 3)).toUpperCase())
                .append(" |  ")
                .append(player2ScoreString)
                .append("  |\n")
                .append("+-----+------+\n")
                .toString();
    }

    private static String formatPoints(int points) {
        switch (points) {
            case 0:
                return "00";
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
