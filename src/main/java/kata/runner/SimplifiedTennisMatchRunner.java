package kata.runner;

import kata.tennis.MintSimplifiedTennisMatchFormatter;
import kata.tennis.SimplifiedTennisMatch;
import kata.tennis.SimplifiedTennisMatchFormatter;

import java.io.InputStream;
import java.util.Scanner;

public class SimplifiedTennisMatchRunner {

    public static void main(String[] args) {
        InputStream in = System.in;

        SimplifiedTennisMatchFormatter formatter = new MintSimplifiedTennisMatchFormatter();
        try (Scanner scanner = new Scanner(in)) {

            System.out.println("Type player 1's name");
            String player1 = scanner.next();
            System.out.println("Type player 2's name");
            String player2 = scanner.next();
            SimplifiedTennisMatch simplifiedTennisMatch = new SimplifiedTennisMatch(player1, player2);
            System.out.println("Who should score next?");
            while (scanner.hasNext()) {
                simplifiedTennisMatch.score(scanner.next().equals("1") ? player1 : player2);
                System.out.println(simplifiedTennisMatch.format(formatter));
                if (simplifiedTennisMatch.isFinishedGame()) {
                    break;
                } else {
                    System.out.println("Who should score next?");
                }
            }
        }
    }
}
