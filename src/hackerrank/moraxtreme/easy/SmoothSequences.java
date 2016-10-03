
package hackerrank.moraxtreme.easy;

import java.util.Scanner;

public class SmoothSequences {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < testCases; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int r = Integer.parseInt(parts[0]);
            int n = Integer.parseInt(parts[1]);


            long grid[][] = new long[r + 1][n];
            for (int y = 0; y < r + 1; y++) {
                grid[y][0] = 1;
            }

            for (int column = 1; column < n; column++) {
                for (int row = 0; row < r + 1; row++) {
                    grid[row][column] = grid[row][column - 1];
                    if (row != 0) {
                        grid[row][column] += grid[row - 1][column - 1];
                    }
                    if (row != r) {
                        grid[row][column] += grid[row + 1][column - 1];
                    }
                }
            }

            double combinations = 0;
            for (int y = 0; y < r + 1; y++) {
                combinations += grid[y][n - 1];
            }

            double ncr = Math.pow(r + 1, n);
            //            BigDecimal percentage = combinations.divide(new BigDecimal(ncr), BigDecimal.ROUND_HALF_UP);
            double percentage = combinations * 100 / ncr;
            long answer = Math.round(percentage * 100000);
            String ans = String.format("%.5f", ((double) answer) / 100000);
            //                        percentage.setScale(5, BigDecimal.ROUND_HALF_UP)
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        new SmoothSequences().solve();
    }
}
