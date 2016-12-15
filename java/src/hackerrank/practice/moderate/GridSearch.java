package hackerrank.practice.moderate;


import java.util.Scanner;

public class GridSearch {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            // rows in main grid
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            scanner.nextLine();

            // main grid
            String[] mainGrid = new String[rows];
            for (int j = 0; j < rows; j++) {
                mainGrid[j] = scanner.nextLine();
            }

            int r = scanner.nextInt();
            int c = scanner.nextInt();
            scanner.nextLine();

            // read sub grid
            String[] subGrid = new String[r];
            for (int j = 0; j < r; j++) {
                subGrid[j] = scanner.nextLine();
            }

            boolean found = false;
            // solve
            for (int x = 0; x < rows; x++) {
                for (int a = 0; a < columns; a++) {
                    int index = mainGrid[x].indexOf(subGrid[0], a);
                    if (index != -1 && x + r <= rows) {
                        found = true;
                        for (int y = 1; y < r; y++) {
                            if (x + y < rows && mainGrid[x + y].indexOf(subGrid[y], a) == index) {
                            } else {
                                found = false;
                                break;
                            }
                        }
                    }

                    if (found) break;
                }
                if (found) break;
            }

            if (found) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void main(String[] args) {
        GridSearch gridSearch = new GridSearch();
        gridSearch.readAndSolve();
    }
}
