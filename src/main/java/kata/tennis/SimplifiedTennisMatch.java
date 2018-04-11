package kata.tennis;

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
        if (playerName.equals(player1)) {
            player1Points++;
        } else if (playerName.equals(player2)) {
            player2Points++;
        }

    }

    public String formatScore() {
        if (player1Points >=4) {
            return player1 + " wins!";
        } else if (player2Points >= 4) {
            return player2 + " wins!";
        }

        String score = new StringBuilder()
                .append(player1 + " - " + player2 + "\n")
                .append(player1Points + " - " + player2Points)
                .toString();
        return score;
    }
}
