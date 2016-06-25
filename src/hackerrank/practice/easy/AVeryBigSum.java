package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class AVeryBigSum {

    public long findSum() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += scanner.nextInt();
        }
        return sum;
    }

    public static void main(String[] args) {
        AVeryBigSum aVeryBigSum = new AVeryBigSum();
        System.out.println(aVeryBigSum.findSum());
    }
}
