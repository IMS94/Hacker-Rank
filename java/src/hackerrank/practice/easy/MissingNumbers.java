package hackerrank.practice.easy;

import java.util.*;

public class MissingNumbers {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> map = new HashMap<>();

        for (String string : scanner.nextLine().split(" ")) {
            if (map.get(string) == null) {
                map.put(string, 1);
            } else {
                map.put(string, map.get(string) + 1);
            }
        }

        int m = Integer.parseInt(scanner.nextLine());
        for (String string : scanner.nextLine().split(" ")) {
            map.put(string, map.get(string) - 1);
        }


        List<Integer> results = new ArrayList<>(120);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                results.add(Integer.parseInt(entry.getKey()));
            }
        }

        Collections.sort(results);
        for (Integer i : results) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        new MissingNumbers().solve();
    }
}
