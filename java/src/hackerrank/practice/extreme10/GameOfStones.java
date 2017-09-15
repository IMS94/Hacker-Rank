/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.practice.extreme10;

import java.util.Scanner;
import java.util.Stack;

public class GameOfStones {
    private int[] moves;

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int numOfTests = scanner.nextInt();

        for (int i = 0; i < numOfTests; i++) {
            int gamesInParallel = scanner.nextInt();

            int opportunities = 0;

            for (int j = 0; j < gamesInParallel; j++) {
                int pilesInGame = scanner.nextInt();
                Stack<Integer> piles = new Stack<>();

                for (int k = 0; k < pilesInGame; k++) {
                    int pileSize = scanner.nextInt();
                    int index = (pileSize - 1) / 2;
                    if (index < moves.length) {
                        opportunities += moves[index];
                    } else {
                        int maxNum = 2 * (moves.length - 1) + 1;
                        opportunities += (pileSize - maxNum) / 2 + moves[moves.length - 1];
                    }
                }
            }
            System.out.println(opportunities % 2 == 0 ? "Bob" : "Alice");
        }
    }

    public void readAndSolveDynamic() {
        int preCalculated[] = new int[100000];
        preCalculated[0] = 0;
        for (int i = 1; i < preCalculated.length; i++) {
            int number = 2 * i + 1;
            preCalculated[i] = preCalculated[(number - 3) / 2] + 1;
        }
        moves = preCalculated;

        readAndSolve();
    }

    public static void main(String[] args) {
        new GameOfStones().readAndSolveDynamic();
    }
}
