/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.done;

import java.util.Scanner;

/**
 * Completed with the same implementation in C++. This code timed out.
 */
public class Rumour {

    public void read() {
        Scanner scanner = new Scanner(System.in);
        int queries = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < queries; i++) {
            String[] parts = scanner.nextLine().split(" ");
            long p = Long.parseLong(parts[0]);
            long q = Long.parseLong(parts[1]);

            long x = 0, y = 0;
            while (p != q) {
                while (p > q) {
                    x++;
                    p = p / 2;
                }

                while (q > p) {
                    y++;
                    q = q / 2;
                }
            }

            System.out.println(x + y);
        }
    }

    public static void main(String[] args) {
        Rumour rumour = new Rumour();
        rumour.read();
    }
}
