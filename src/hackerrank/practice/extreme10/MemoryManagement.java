/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.practice.extreme10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class MemoryManagement {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int numOfTests = scanner.nextInt();

        for (int i = 0; i < numOfTests; i++) {
            int numOfPages = scanner.nextInt();
            int pageSize = scanner.nextInt();
            int memoryAccesses = scanner.nextInt();

            int fifo = 0;
            int lru = 0;

            LinkedList<Integer> fifoPages = new LinkedList<>();
            Set<Integer> fifoSet = new HashSet<>();

            LinkedList<Integer> lruPages = new LinkedList<>();
            Set<Integer> lruSet = new HashSet<>();

            for (int j = 0; j < memoryAccesses; j++) {
                int address = scanner.nextInt();
                Integer pageNumber = address / pageSize;

                // Handling FIFO
                if (!fifoSet.contains(pageNumber)) {
                    // Couldn't find page in cache
                    if (!fifoPages.isEmpty() && fifoPages.size() == numOfPages) {
                        // Remove head. A replacement
                        int removed = fifoPages.removeFirst();
                        fifoSet.remove(removed);
                        fifo++;
                    }

                    fifoPages.addLast(pageNumber);
                    fifoSet.add(pageNumber);
                }

                if (lruSet.contains(pageNumber)) {
                    lruPages.remove(pageNumber);
                    lruPages.addFirst(pageNumber);
                } else {
                    if (lruPages.size() == numOfPages) {
                        int removed = lruPages.removeLast();
                        lruSet.remove(removed);
                        lru++;
                    }

                    lruPages.addFirst(pageNumber);
                    lruSet.add(pageNumber);
                }
            }

            System.out.println(String.format("%s %d %d", fifo > lru ? "yes" : "no", fifo, lru));
        }
    }

    public static void main(String[] args) {
        new MemoryManagement().readAndSolve();
    }
}
