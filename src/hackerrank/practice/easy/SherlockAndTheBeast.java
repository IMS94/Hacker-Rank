package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/25/16.
 */
public class SherlockAndTheBeast {
    private int T;
    private String[] answers;

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        answers = new String[T];

        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int max = -1;
            for (int r = 0; r <= N; r += 3) {
                if ((N - r) % 5 == 0 && r > max) {
                    max = r;
                }
            }

            if (max == -1) {
                answers[i] = String.valueOf(max);
            } else {
                String ans = new String(new char[max]).replace("\0", "5");
                ans += new String(new char[N - max]).replace("\0", "3");
                answers[i] = ans;
            }
        }

        for (String ans : answers) {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        SherlockAndTheBeast sherlockAndTheBeast = new SherlockAndTheBeast();
        sherlockAndTheBeast.readAndSolve();
    }
}
