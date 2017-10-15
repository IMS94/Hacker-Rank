/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.done;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CollectingGold {

    private int n, m;
    private Map<Long, Map<Long, Integer>> graph = new HashMap<>();
    private long[] primes = new long[]{2, 6, 30, 210, 2310, 30030, 510510, 9699690, 223092870, 6469693230L,
            200560490130L, 7420738134810L, 304250263527210L, 13082761331670030L, 614889782588491410L};

    public void read() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long v = scanner.nextLong();
            graph.put(v, new HashMap<>());
            if (v < min) {
                min = v;
            }

            if (v > max) {
                max = v;
            }
        }

        for (int i = 0; i < m; i++) {
            long v = scanner.nextLong();
            long u = scanner.nextLong();
            int distance = scanner.nextInt();
            graph.get(v).put(u, distance);
            graph.get(u).put(v, distance);
        }

        CityGraph cityGraph = new CityGraph(graph);
        Map<Long, Integer> distances = cityGraph.dijkstra(min, max);
        Map<Long, Long> predecessors = cityGraph.getPredecessors();
        long node = max;
        int kilos = 0;
        while (true) {
            kilos += getKilos(node);
            if (node == min) {
                break;
            }

            node = predecessors.get(node);
        }
        System.out.printf("%d\n", kilos);
    }

    private int getKilos(long id) {
        int kilos = 0;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] <= id) {
                kilos++;
            } else {
                break;
            }
        }

        return kilos;
    }

    public static void main(String[] args) {
        CollectingGold collectingGold = new CollectingGold();
        collectingGold.read();
    }
}

@SuppressWarnings("Duplicates")
class CityGraph {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    private Map<Long, Map<Long, Integer>> graph;
    private Map<Long, Long> predecessors;
    private int V;

    CityGraph(Map<Long, Map<Long, Integer>> graph) {
        this.V = graph.size();
        this.graph = graph;
    }

    private long minDistance(Map<Long, Integer> dist, Map<Long, Boolean> sptSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        long minNode = -1;

        for (long v : graph.keySet()) {
            if (!sptSet.get(v) && dist.get(v) <= min) {
                min = dist.get(v);
                minNode = v;
            }
        }
        return minNode;
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public Map<Long, Integer> dijkstra(long src, long destination) {
        Map<Long, Integer> dist = new HashMap<>();
        // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Map<Long, Boolean> sptSet = new HashMap<>();

        // Set of predecessors
        predecessors = new HashMap<>();

        // Initialize all distances as INFINITE and stpSet[] as false
        for (long v : graph.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            predecessors.put(v, null);
            sptSet.put(v, false);
        }

        // Distance of source vertex from itself is always 0
        dist.put(src, 0);

        // Find shortest path for all vertices
        for (long tmp : graph.keySet()) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            long u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet.put(u, true);

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (long v : graph.get(u).keySet()) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet.get(v) && dist.get(u) != Integer.MAX_VALUE &&
                        dist.get(u) + graph.get(u).get(v) < dist.get(v)) {
                    dist.put(v, dist.get(u) + graph.get(u).get(v));
                    predecessors.put(v, u);
                }
            }
        }

        return dist;
    }

    public Map<Long, Long> getPredecessors() {
        return predecessors;
    }
}