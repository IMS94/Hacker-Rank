package hackerrank.ieeextreme10;

import java.util.Scanner;

public class IntiSets {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            String[] parts = scanner.nextLine().split(" ");
            long N = Long.parseLong(parts[0]);
            long A = Long.parseLong(parts[1]);
            long B = Long.parseLong(parts[2]);

            long sum = 0;
            for (long num = A; num <= B; num++) {
                if (gcd(N, num) == 1) {
                    sum = (sum + num) % 1000000007;
                }
            }
            System.out.println(sum);
        }
    }

    private long gcd(long a, long b) {
        long t;
        while (b != 0) {
            t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    public static void main(String[] args) {
        new IntiSets().solve();
    }
}
