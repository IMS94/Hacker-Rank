
package hackerrank.moraxtreme;

import java.util.*;

public class FairAndSquare {
    private Set<Integer> squares = new HashSet<>();
    private Map<Integer, Integer> smallestCounts = new HashMap<>();
    int limit = 20000;

    public void solve() {
        for (int i = 1; i < limit; i++) {
            squares.add(i * i);
            if (i * i > 20000) break;
        }

        for (int i = 1; i < limit; i++) {
            if (squares.contains(i)) {
                smallestCounts.put(i, 1);
                continue;
            }
            int minCount = limit;
            for (int j = 1; j < i / 2 + 1; j++) {
                int count = smallestCounts.get(j) + smallestCounts.get(i - j);
                if (count < minCount) {
                    minCount = count;
                }
            }
            smallestCounts.put(i, minCount);
        }
//        System.out.println("Done calculations");
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            ans.add(smallestCounts.get(num));
        }

        for (Integer a : ans) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        new FairAndSquare().solve();
    }
}
