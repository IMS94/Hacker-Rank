package hackerrank.practice.easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IceCreamParlor {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < testCases; i++) {
            int money = Integer.parseInt(scanner.nextLine());
            int flavors = Integer.parseInt(scanner.nextLine());

            Map<Integer, List<Integer>> indexMap = new HashMap<>();

            List<Integer> costs = Stream.of(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            for (int j = 0; j < costs.size(); j++) {
                List<Integer> list = indexMap.computeIfAbsent(costs.get(j), k -> new ArrayList<>());
                list.add(j);
            }

            Collections.sort(costs);
            List<Integer> answers = new ArrayList<>(2);
            for (int index = 0; index < costs.size(); index++) {
                int flavor1Cost = costs.get(index);
                int possibleFlavor2Cost = money - flavor1Cost;

                if (binarySearch(costs, 0, costs.size() - 1, possibleFlavor2Cost)) {
                    // Found one. ela (y)
                    if (flavor1Cost == possibleFlavor2Cost) {
                        if (indexMap.get(flavor1Cost).size() > 1) {
                            answers.add(indexMap.get(flavor1Cost).get(0));
                            answers.add(indexMap.get(flavor1Cost).get(1));
                            break;
                        }
                    } else {
                        answers.add(indexMap.get(flavor1Cost).get(0));
                        answers.add(indexMap.get(possibleFlavor2Cost).get(0));
                        break;
                    }
                }
            }

            answers.stream().sorted().forEach(answer -> System.out.print((answer + 1) + " "));
            System.out.println("");
        }
    }

    private boolean binarySearch(List<Integer> list, int from, int to, int valueToFind) {
        if (from == to) {
            return list.get(from) == valueToFind;
        } else if (from > to) {
            return false;
        }

        int midPoint = (from + to) / 2;

        if (list.get(midPoint) == valueToFind) {
            return true;
        } else if (valueToFind > list.get(midPoint)) {
            return binarySearch(list, midPoint + 1, to, valueToFind);
        } else {
            return binarySearch(list, from, midPoint, valueToFind);
        }
    }

    public static void main(String[] args) {
        new IceCreamParlor().solve();
    }
}
