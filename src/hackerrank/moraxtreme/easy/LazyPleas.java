package hackerrank.moraxtreme.easy;

import java.awt.*;
import java.util.*;

public class LazyPleas {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int popularWordCount = scanner.nextInt();
        int pleasCount = scanner.nextInt();
        scanner.nextLine();
        Set<String> words = new HashSet<>();
        for (int i = 0; i < popularWordCount; i++) {
            words.add(scanner.nextLine());
        }

        //        Map<String, Set<String>> pleas = new HashMap<>();
        int counts[] = new int[pleasCount];
        String[] lines = new String[pleasCount];

        for (int i = 0; i < pleasCount; i++) {
            String line = scanner.nextLine();
            lines[i] = line;
            String[] parts = line.split(" ");
            for (String part : parts) {
                if (words.contains(part)) {
                    counts[i]++;
                }
            }
            //            pleas.put(line, new HashSet<>(Arrays.asList(parts)));
        }

        int maxPopularWordCount = 0;
        String popularString = null;
        for (int i = 0; i < pleasCount; i++) {
            if (counts[i] > maxPopularWordCount) {
                maxPopularWordCount = counts[i];
                popularString = lines[i];
            }
        }

        //
        //        for (Map.Entry<String, Set<String>> entry : pleas.entrySet()) {
        //            int count = 0;
        //            Set<String> wordSet = entry.getValue();
        //            for (String word : words) {
        //                if (wordSet.contains(word)) {
        //                    String line = entry.getKey();
        //                    int index = 0;
        //                    while (index != -1) {
        //                        index = line.indexOf(word);
        //                        count++;
        //                        index = line.indexOf(word, index + word.length());
        //                    }
        //                }
        //            }
        //            if (count > maxPopularWordCount) {
        //                maxPopularWordCount = count;
        //                popularString = entry.getKey();
        //            }
        //        }
        System.out.println(popularString);
    }

    public static void main(String[] args) {
        new LazyPleas().solve();
    }
}
