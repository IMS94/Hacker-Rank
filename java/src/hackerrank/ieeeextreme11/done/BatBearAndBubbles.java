/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.done;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BatBearAndBubbles {

    // Driver method to test above methods
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] secondLine = scanner.nextLine().split(" ");
            int numOfVertices = Integer.parseInt(secondLine[0]);
            int numOfEdges = Integer.parseInt(secondLine[1]);

            String[] pairs = scanner.nextLine().split(" ");
            Graph g = new Graph(numOfVertices);
            for (int j = 1; j < numOfEdges * 2; j += 2) {
                int v = Integer.parseInt(pairs[j]);
                int w = Integer.parseInt(pairs[j - 1]);
                g.addEdge(v, w);
            }

            if (g.isCyclic()) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private static class Graph {
        private int V;   // No. of vertices
        private LinkedList<Integer> adj[]; // Adjacency List Represntation

        // Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<>();
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }

        // A recursive function that uses visited[] and parent to detect
        // cycle in subgraph reachable from vertex v.
        private Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
            // Mark the current node as visited
            visited[v] = true;
            Integer i;

            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> it = adj[v].iterator();
            while (it.hasNext()) {
                i = it.next();

                // If an adjacent is not visited, then recur for that
                // adjacent
                if (!visited[i]) {
                    if (isCyclicUtil(i, visited, v)) {
                        return true;
                    }
                }

                // If an adjacent is visited and not parent of current
                // vertex, then there is a cycle.
                else if (i != parent) {
                    return true;
                }
            }
            return false;
        }

        // Returns true if the graph contains a cycle, else false.
        Boolean isCyclic() {
            // Mark all the vertices as not visited and not part of
            // recursion stack
            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper function to detect cycle in
            // different DFS trees
            for (int u = 0; u < V; u++)
                if (!visited[u]) // Don't recur for u if already visited
                    if (isCyclicUtil(u, visited, -1))
                        return true;

            return false;
        }
    }

}
