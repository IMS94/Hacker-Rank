package hackerrank.practice.moderate;

import java.util.*;

/**
 * Created by imesha on 7/26/16.
 */
public class ShortestReach {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        List<int[]> answers = new ArrayList<>();

        for (int i = 0; i < testCases; i++) {
            int nodes = scanner.nextInt();
            int edges = scanner.nextInt();
            int[][] graph = new int[nodes][nodes];

            for (int j = 0; j < edges; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                graph[x - 1][y - 1] = 6;
                graph[y - 1][x - 1] = 6;
            }

            // Start position
            int start = scanner.nextInt();
            int[] distance = new int[nodes];
            Arrays.fill(distance, Integer.MAX_VALUE);

            // Dijkstra's algorithm
            // 0 : nodes-1 are nodes
            List<Integer> list = new ArrayList<>(), closed = new ArrayList<>();
            list.add(start - 1);
            distance[start - 1] = 0;

            while (!list.isEmpty()) {
                Collections.sort(list);
                int currentNode = list.remove(0);
                for (int neighbourNode = 0; neighbourNode < nodes; neighbourNode++) {
                    if (neighbourNode == currentNode || graph[currentNode][neighbourNode] != 6
                            || closed.contains(neighbourNode))
                        continue;

                    // If the distance to newNode is lower through the current node, replace it
                    if (distance[neighbourNode] > distance[currentNode] + graph[currentNode][neighbourNode]) {
                        distance[neighbourNode] = distance[currentNode] + graph[currentNode][neighbourNode];
                    }

                    // add the neighbour node to the queue if it is not in list or closed lists
                    if (!list.contains(neighbourNode)) {
                        list.add(neighbourNode);
                    }
                }
                closed.add(currentNode);
            }
            answers.add(distance);
        }

        for (int[] ans : answers) {
            for (int x = 0; x < ans.length; x++) {
                if (ans[x] == 0) continue;
                System.out.print(ans[x] == Integer.MAX_VALUE ? -1 : ans[x]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ShortestReach shortestReach = new ShortestReach();
        shortestReach.solve();

    }
}
