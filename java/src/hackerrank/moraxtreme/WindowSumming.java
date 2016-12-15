
package hackerrank.moraxtreme;

import java.util.*;

public class WindowSumming {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int sequenceLength = scanner.nextInt();
        int windowLength = scanner.nextInt();

        Queue<Long> queue = new LinkedList<>();

        for (int i = 0; i < sequenceLength; i++) {
            long nextValue = scanner.nextLong();
            queue.add(nextValue);
            if (queue.size() > windowLength) queue.remove();
            int newValue = 0;
            Iterator<Long> iterator = queue.iterator();
            while (iterator.hasNext()) {
                newValue += iterator.next();
            }
            System.out.print(newValue + " ");
        }
    }

    public static void main(String[] args) {
        new WindowSumming().solve();
    }
}
