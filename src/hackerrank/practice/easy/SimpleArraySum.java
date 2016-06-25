package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class SimpleArraySum {
    private int[] array;

    public int findSum() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += scanner.nextInt();
        }
        return sum;
    }

    public static void main(String[] args) {
        SimpleArraySum simpleArraySum = new SimpleArraySum();
        System.out.println(simpleArraySum.findSum());
    }
}
