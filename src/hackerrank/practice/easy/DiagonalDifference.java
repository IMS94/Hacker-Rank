package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class DiagonalDifference {

    public long readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long primaryDiagonal = 0, secondaryDiagonal = 0;
        for (int row = 0; row < N; row++) {
            scanner.nextLine();
            for (int col = 0; col < N; col++) {
                int val = scanner.nextInt();
                if (row == col) {
                    primaryDiagonal += val;
                }
                if (row == N - col - 1) {
                    secondaryDiagonal += val;
                }
            }
        }
        return primaryDiagonal - secondaryDiagonal > 0 ?
                primaryDiagonal - secondaryDiagonal : secondaryDiagonal - primaryDiagonal;
    }

    public static void main(String[] args) {
        DiagonalDifference diagonalDifference = new DiagonalDifference();
        System.out.println(diagonalDifference.readAndSolve());
    }
}
