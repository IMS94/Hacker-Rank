package hackerrank.practice.easy;

import java.util.Scanner;

public class CavityMap {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        // Form grids
        String[] grid = new String[n];
        int[][] numbers = new int[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = scanner.nextLine();

            for (int j = 0; j < n; j++) {
                numbers[i][j] = Character.getNumericValue(grid[i].charAt(j));
            }
        }

        for (int i = 1; i < n - 1; i++) {
            StringBuilder builder = new StringBuilder(grid[i]);
            for (int j = 1; j < n - 1; j++) {
                if (numbers[i][j] > numbers[i][j + 1] && numbers[i][j] > numbers[i][j - 1] &&
                        numbers[i][j] > numbers[i + 1][j] && numbers[i][j] > numbers[i - 1][j]) {
                    builder.setCharAt(j, 'X');
                }
            }
            grid[i] = builder.toString();
        }

        for (String row : grid) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        CavityMap cavityMap = new CavityMap();
        cavityMap.readAndSolve();
    }
}
