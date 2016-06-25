package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class FindDigits {
    private int T;
    private int[] N;

    public void read() {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        N = new int[T];
        for (int i = 0; i < T; i++) {
            N[i] = scanner.nextInt();
        }
    }

    public void solve() {
        for (int n : N) {
            int answer = 0;
            String number = String.valueOf(n);
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                int num = Character.getNumericValue(c);
                if (num != 0 && n % num == 0) {
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        FindDigits findDigits = new FindDigits();
        findDigits.read();
        findDigits.solve();
    }
}
