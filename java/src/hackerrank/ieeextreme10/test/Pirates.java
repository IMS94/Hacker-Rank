package hackerrank.ieeextreme10.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pirates {

    private boolean grid[][];
    private int distances[][];
    int N;
    int M;

    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = reader.readLine().split(" ");

        N = Integer.parseInt(parts[0]);
        M = Integer.parseInt(parts[1]);
        int Q = Integer.parseInt(parts[2]);

        grid = new boolean[N][M];
        distances = new int[N][M];

        for (int x = 0; x < N; x++) {
            String row = reader.readLine();
            for (int y = 0; y < row.length(); y++) {
                grid[x][y] = (row.charAt(y) == 'O');
                distances[x][y] = Integer.MAX_VALUE;
            }
        }

        for (int x = 0; x < Q; x++) {

            for (int i = 0; i < N; i++) {
                for (int y = 0; y < M; y++) {
                    distances[i][y] = Integer.MAX_VALUE;
                }
            }

            String queries[] = reader.readLine().split(" ");
            int x1 = Integer.parseInt(queries[0]) - 1;
            int y1 = Integer.parseInt(queries[1]) - 1;
            int x2 = Integer.parseInt(queries[2]) - 1;
            int y2 = Integer.parseInt(queries[3]) - 1;
            distances[x1][y1] = 0;
            generateDistances(x1, y1);
            System.out.println(distances[x2][y2]);
        }
    }

    private void generateDistances(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;

                if (x + i >= 0 && x + i < N) {
                    if (y + j >= 0 && y + j < M) {
                        int newDist = distances[x][y] + (!grid[x][y] && grid[x + i][y + j] ? 1 : 0);
                        int oldDist = distances[x + i][y + j];
                        distances[x + i][y + j] = Math.min(newDist, distances[x + i][y + j]);
                        if (distances[x + i][y + j] < oldDist) {
                            generateDistances(x + i, y + j);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Pirates().solve();
    }
}
