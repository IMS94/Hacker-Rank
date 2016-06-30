package hackerrank.practice.easy;

import java.util.*;

/**
 * Created by imesha on 6/29/16.
 */
public class CutTheSticks {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Integer> sticks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            sticks.add(scanner.nextInt());
        }

        sticks.sort(null);

        while (!sticks.isEmpty()) {
            //print the number of sticks left
            System.out.println(sticks.size());

            int minLength = sticks.get(0);
            ListIterator<Integer> iterator = sticks.listIterator();
            while (iterator.hasNext()) {
                int stickLength = iterator.next();
                stickLength = stickLength - minLength;
                if (stickLength == 0) {
                    iterator.remove();
                } else {
                    iterator.set(stickLength);
                }
            }
        }
    }

    public static void main(String[] args) {
        CutTheSticks cutTheSticks = new CutTheSticks();
        cutTheSticks.readAndSolve();
    }
}
