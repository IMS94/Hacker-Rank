/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.trying;

import java.util.Scanner;

public class Knin {

    public void read() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int x = 0; x < testCases; x++) {
            int h = scanner.nextInt();
            int w = scanner.nextInt();

            // advance to next line
            scanner.nextLine();

            int[] sums = new int[h > w ? w : h];

            boolean valid = true;
            int parity = 0;
            for (int i = 0; i < h; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < w; j++) {
                    int val;
                    if (line.charAt(j) == '.') {
                        val = 0;
                    } else if (line.charAt(j) == '1') {
                        parity++;
                        val = 1;
                    } else {
                        parity--;
                        val = 2;
                    }

                    if (i < sums.length && j < sums.length && val != 0) {
                        sums[i > j ? i : j]++;
                    }
                }
            }

            if (parity != 0 && parity != 1) {
                valid = false;
            } else {
                int sum = 0;
                for (int i = 0; i < sums.length; i++) {
                    sum += sums[i];

                    if (i > 1 && sum > i) {
                        valid = false;
                        break;
                    }
                }
            }

            System.out.println(valid ? "YES" : "NO");
        }
    }

    public static void main(String[] args) {
        Knin knin = new Knin();
        knin.read();
    }
}

