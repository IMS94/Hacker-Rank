
package hackerrank.ieeextreme10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MysteriousMaze {

    private short[][] grid;
    private int N;

    private void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        grid = new short[N][N];

        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(Character.toChars(reader.read()));
        }

        //process sequence
        String sequence[] = builder.toString().split("\n");
        int counter = 0;
        int answer = -1;
        for (String elem : sequence) {
            if (elem.equals("-1")) {
                break;
            }

            String index[] = elem.split(" ");
            int x = Integer.parseInt(index[0]) - 1;
            int y = Integer.parseInt(index[1]) - 1;

            // Last row?
            if (x == N - 1) {
                grid[x][y] = 1;
                updateChildren(x, y);
            } else {
                grid[x][y] = 2;
                updateGrid(x, y);
            }
            counter++;

            for (short num : grid[0]) {
                if (num == 1) {
                    answer = counter;
                    break;
                }
            }

            if (answer != -1) {
                break;
            }
        }

        //        for (short[] x : map) {
        //            for (short y : x) {
        //                System.out.print(y + " ");
        //            }
        //            System.out.println("");
        //        }
        System.out.println(answer);
    }

    private void updateGrid(int x, int y) {
        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
            grid[x][y] = 1;
            updateChildren(x, y);
        } else if (x + 1 < N && grid[x + 1][y] == 1) {
            grid[x][y] = 1;
            updateChildren(x, y);
            return;
        } else if (y - 1 > 0 && grid[x][y - 1] == 1) {
            grid[x][y] = 1;
            updateChildren(x, y);
            return;
        } else if (y + 1 < N && grid[x][y + 1] == 1) {
            grid[x][y] = 1;
            updateChildren(x, y);
            return;
        }
    }

    private void updateChildren(int x, int y) {
        if (grid[x][y] != 1) return;

        if (x - 1 >= 0 && grid[x - 1][y] == 2) {
            grid[x - 1][y] = 1;
            updateChildren(x - 1, y);
        }

        if (x + 1 < N && grid[x + 1][y] == 2) {
            grid[x + 1][y] = 1;
            updateChildren(x + 1, y);
        }

        if (y - 1 > 0 && grid[x][y - 1] == 2) {
            grid[x][y - 1] = 1;
            updateChildren(x, y - 1);
        }

        if (y + 1 < N && grid[x][y + 1] == 2) {
            grid[x][y + 1] = 1;
            updateChildren(x, y + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new MysteriousMaze().solve();
    }
}
