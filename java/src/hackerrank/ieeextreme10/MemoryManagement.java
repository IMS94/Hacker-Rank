
package hackerrank.ieeextreme10;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MemoryManagement {

    private void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int x = 0; x < testCases; x++) {
            String line[] = scanner.nextLine().split(" ");

            int numberOfPages = Integer.parseInt(line[0]);
            int pageSize = Integer.parseInt(line[1]);
            int memoryAccessesCount = Integer.parseInt(line[2]);


            Queue<Integer> fifo = new ArrayDeque<>(numberOfPages);
            Stack<Integer> lru = new Stack<>();

            int fifoSwaps = 0;
            int lruSwaps = 0;
            for (int access = 0; access < memoryAccessesCount; access++) {
                long addressRequested = Long.parseLong(scanner.nextLine());
                int pageRequested = (int) Math.floor((double) addressRequested / (double) pageSize);

                /* FIFO */
                if (fifo.contains(pageRequested)) {
                    //Fine
                } else {
                    if (fifo.size() == numberOfPages) {
                        fifo.remove();
                        fifoSwaps++;
                    }
                    fifo.add(pageRequested);
                }

                int index;
                if ((index = lru.lastIndexOf(pageRequested)) != -1) {
                    int page = lru.remove(index);
                    lru.push(page);
                } else {
                    if (lru.size() == numberOfPages) {
                        lru.remove(0);
                        lruSwaps++;
                    }
                    lru.push(pageRequested);
                }
            }

            System.out.printf("%s %d %d\n", fifoSwaps > lruSwaps ? "yes" : "no", fifoSwaps, lruSwaps);
        }
    }

    public static void main(String[] args) {
        new MemoryManagement().solve();
    }
}
