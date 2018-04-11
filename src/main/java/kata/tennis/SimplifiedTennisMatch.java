package kata.tennis;

public class SimplifiedTennisMatch {

    private final String player1;
    private final String player2;

    public SimplifiedTennisMatch(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void score(String playerName) {

    }

    public String formatScore() {
        String score = new StringBuilder()
                .append(player1 + " - " + player2 + "\n")
                .append("0 - 0")
                .toString();
        return score;
    }
}
