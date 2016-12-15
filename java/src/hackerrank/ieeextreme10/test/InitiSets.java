/*
 * Copyright (c) 2010-2016 AdroitLogic Private Ltd. (http://adroitlogic.org). All Rights Reserved.
 *
 * AdroitLogic PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package hackerrank.ieeextreme10.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InitiSets {
    int mod = 1000000007;

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            String[] parts = scanner.nextLine().split(" ");
            long N = Long.parseLong(parts[0]);
            long A = Long.parseLong(parts[1]);
            long B = Long.parseLong(parts[2]);

            Set<Long> primeFactors = new HashSet<>();
            long factor = 2;
            long leftOver = N;
            while (factor * factor <= N) {
                if (leftOver % factor == 0) {
                    leftOver = leftOver / factor;
                    primeFactors.add(factor);
                } else {
                    factor++;
                }
            }

            long sum = 0;
            long totientN = phi(N, primeFactors);
            for (long num = A; num <= B; num++) {
                if (Math.pow(num, totientN) % N == 1) {
                    sum = (sum + num) % mod;
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

    private long phi(long N, Set<Long> factors) {
        if (factors.size() == 0) {
            return N - 1;
        }
        double result = (double) N;
        for (long factor : factors) {
            result = result * (1.0 - 1.0 / factor);
        }
        return (long) result;
    }

    public static void main(String[] args) {
        new InitiSets().solve();
    }
}
