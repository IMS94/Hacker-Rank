
package hackerrank.moraxtreme.easy;

import java.util.Scanner;

public class BeachfulIslands {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int k = 0; k < testCases; k++) {

            int rowCount = scanner.nextInt();
            int columnCount = scanner.nextInt();
            scanner.nextLine();

            int[][] island = new int[rowCount][columnCount];
            for (int a = 0; a < rowCount; a++) {
                String line = scanner.nextLine();
                for (int b = 0; b < columnCount; b++) {
                    int cell = Character.getNumericValue(line.charAt(b));
                    if (cell == 1) {
                        island[a][b] = cell;
                        // Color all the adjacent cells.
                        for (int col = -1; col < 2; col++) {
                            for (int row = -1; row < 2; row++) {
                                if (a + row >= 0 && a + row < rowCount) {
                                    if (b + col >= 0 && b + col < columnCount) {
                                        island[a + row][b + col] = island[a + row][b + col] == 1 ? 1 : 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int a = 0; a < rowCount; a++) {
                for (int b = 0; b < columnCount; b++) {
                    System.out.print(island[a][b]);
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) {
        new BeachfulIslands().solve();
    }
}
