package hackerrank.ieeeextreme11.trying;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Please name your class Main
class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Solver solver = new Solver();
        try {
            solver.Solve();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.flush();
        }
    }
}

class Solver {

    private int n, m;
    private Scanner in;
    private PrintWriter out;
    private int[][] graph;

    private int blackKing;
    private int whiteKing;

    // functions used to comunicate with the interactor (the other program)
    // use this to get the position of the other player.
    // after using it you must do your own move
    // TL;DR getBlack() getBlack() is invalid
    private int getBlack() {
        return in.nextInt() - 1;
    }

    // use this to set your own move
    private void setWhite(int node) {
        System.out.println(node + 1);
        System.out.flush();
    }

    public void ReadGraph() {
        n = in.nextInt();
        graph = new int[n][n];

        m = in.nextInt();
        for (int i = 0; i < m; i += 1) {
            int a, b;
            a = in.nextInt();
            b = in.nextInt();
            graph[a - 1][b - 1] = 1;
            graph[b - 1][a - 1] = 1;
        }
    }

    void Solve() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);

        boolean myTurn = false;
        ReadGraph();

        ShortestPath shortestPath = new ShortestPath(graph);
        whiteKing = 1;
        setWhite(whiteKing);

        while (blackKing != whiteKing) {
            if (myTurn) {
                whiteKing = shortestPath.dijkstra(whiteKing, blackKing);
                setWhite(whiteKing);
                myTurn = false;
            } else {
                blackKing = getBlack();
                myTurn = true;
            }
        }
    }
}

class ShortestPath {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    private int V;
    private int[][] graph;

    ShortestPath(int[][] graph) {
        this.V = graph.length;
        this.graph = graph;
    }

    private int minDistance(int dist[], boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public int dijkstra(int src, int destination) {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        boolean sptSet[] = new boolean[V];

        // Set of predecessors
        Map<Integer, Integer> predecessors = new HashMap<>();

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            predecessors.put(i, null);
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    predecessors.put(v, u);
                }
        }

        if (dist[destination] == 1) {
            return destination;
        }

        int next = destination;
        while (predecessors.get(next) != src) {
            next = predecessors.get(next);
        }

        return next;
    }
}