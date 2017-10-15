/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.trying;

import java.util.Scanner;

public class MisterCounter {

    private int n;
    private int[] array;
    private int numOfQueries;

    public void read() {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());

        array = new int[n];
        String vals[] = scanner.nextLine().split(" ");
        for (int i = 0; i < vals.length; i++) {
            array[i] = Integer.parseInt(vals[i]);
        }

        numOfQueries = scanner.nextInt();

        for (int i = 0; i < numOfQueries; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;

            solve(from, to);
        }
    }

    private void solve(int from, int to) {
        int count = getFa(from, to);

        System.out.println(count);
    }

    private int getFa(int from, int to) {
        int count = 0;

        int[] arr = new int[to - from + 1];

        // largest i s.t i > (i-1)
        int i = 0;
        int j = i;
        for (int x = 0; x < to - from + 1; x++) {
            arr[x] = array[from + x];

            if (x > 0 && arr[x - 1] < arr[x]) {
                i = x;
            }

            if (i > 0 && arr[i - 1] < arr[x]) {
                j = x;
            }
        }

        // No luck
        if (i == 0) {
            return count;
        }

        // swap arr[j] and arr[i-1]
        count += i == arr.length - 1 ? 2 : 1;

        // To reverse array
        int swaps = arr.length - i;
        swaps = swaps % 2 == 0 ? swaps : swaps - 1;
        count += swaps;

        return count;
    }

    public static void main(String[] args) {
        MisterCounter misterCounter = new MisterCounter();
        misterCounter.read();
    }
}
