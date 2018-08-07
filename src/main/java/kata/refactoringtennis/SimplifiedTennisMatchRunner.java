package kata.refactoringtennis;


import java.io.InputStream;
import java.util.Scanner;

public class SimplifiedTennisMatchRunner {

    public static void main(String[] args) {
        InputStream in = System.in;

        try (Scanner scanner = new Scanner(in)) {

            System.out.println("Type player 1's name");
            String player1 = scanner.next();
            System.out.println("Type player 2's name");
            String player2 = scanner.next();
            SimplifiedTennisMatch simplifiedTennisMatch = new SimplifiedTennisMatch(player1, player2);
            NewStyleFormatter newStyleFormatter = new NewStyleFormatter();
            System.out.println(simplifiedTennisMatch.formatScore(newStyleFormatter));
            System.out.println("Who should score next?");
            while (scanner.hasNext()) {
                simplifiedTennisMatch.score(scanner.next().equals("1") ? player1 : player2);
                System.out.println(simplifiedTennisMatch.formatScore(newStyleFormatter));
                System.out.println("Who should score next?");
            }
        }
    }
}
