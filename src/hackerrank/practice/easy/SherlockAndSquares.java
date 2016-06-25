package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class SherlockAndSquares {
    private int T;
    private int[][] values;

    public void read() {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        values = new int[T][2];
        for (int i = 0; i < T; i++) {
            values[i][0] = scanner.nextInt();
            values[i][1] = scanner.nextInt();
        }
    }

    public void solve() {
        for (int i = 0; i < T; i++) {
            double B = Math.floor(Math.sqrt(values[i][1]));
            double A = Math.ceil(Math.sqrt(values[i][0]));
            double answer = B - A;
            answer = answer < 0 ? 0 : answer + 1;
            System.out.println((int)answer);
        }
    }

    public static void main(String[] args) {
        SherlockAndSquares sherlockAndSquares = new SherlockAndSquares();
        sherlockAndSquares.read();
        sherlockAndSquares.solve();
    }
}
