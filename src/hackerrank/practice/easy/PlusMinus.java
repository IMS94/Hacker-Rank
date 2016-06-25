package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class PlusMinus {
    private int positive = 0, zero = 0, negative = 0, N;

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            int val = scanner.nextInt();
            if (val > 0) {
                positive++;
            } else if (val < 0) {
                negative++;
            } else {
                zero++;
            }
        }
    }

    public void printFractions() {
        System.out.println(String.format("%.7g", ((double) positive) / N));
        System.out.println(String.format("%.7g", ((double) negative) / N));
        System.out.println(String.format("%.7g", ((double) zero) / N));
    }

    public static void main(String[] args) {
        PlusMinus plusMinus = new PlusMinus();
        plusMinus.readAndSolve();
        plusMinus.printFractions();
    }
}
