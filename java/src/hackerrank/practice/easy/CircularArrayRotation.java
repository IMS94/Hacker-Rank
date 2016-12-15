/*
 * Copyright (c) 2010-2016 AdroitLogic Private Ltd. (http://adroitlogic.org). All Rights Reserved.
 *
 * AdroitLogic PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package hackerrank.practice.easy;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CircularArrayRotation {

    int length, k, testCases;

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        length = scanner.nextInt();
        k = scanner.nextInt();
        testCases = scanner.nextInt();

        scanner.nextLine();

        List<Integer> array = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (int i = 0; i < testCases; i++) {
            int m = scanner.nextInt();
            int index = (m - k) % length;
            if (index < 0) {
                index = length + index;
            }
            System.out.println(array.get(index));
        }
    }

    public static void main(String[] args) {
        new CircularArrayRotation().solve();
    }
}
