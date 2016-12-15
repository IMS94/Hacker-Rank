package hackerrank.ieeextreme10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("Duplicates")
public class Pirates {

    private Node[][] map;
    private int N;
    private int M;

    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = reader.readLine().split(" ");

        N = Integer.parseInt(parts[0]);
        M = Integer.parseInt(parts[1]);
        int Q = Integer.parseInt(parts[2]);

        map = new Node[N][M];

        for (int x = 0; x < N; x++) {
            String row = reader.readLine();
            for (int y = 0; y < row.length(); y++) {
                map[x][y] = new Node();
                map[x][y].x = x;
                map[x][y].y = y;
                map[x][y].isLand = (row.charAt(y) == 'O');
            }
        }

        Node[][] copy = map.clone();

        Set<Node> closedList = new HashSet<>();
        Set<Node> openList = new HashSet<>();

        for (int x = 0; x < Q; x++) {

            map = copy;
            String queries[] = reader.readLine().split(" ");
            int x1 = Integer.parseInt(queries[0]) - 1;
            int y1 = Integer.parseInt(queries[1]) - 1;
            int x2 = Integer.parseInt(queries[2]) - 1;
            int y2 = Integer.parseInt(queries[3]) - 1;

            map[x1][y1].distance = 0;
            map[x1][y1].fScore = 0;


            openList.clear();
            closedList.clear();

            openList.add(map[x1][y1]);
            while (!openList.isEmpty()) {
                Optional<Node> result = openList.parallelStream().min((n1, n2) -> Integer.compare(n2.fScore, n1.fScore));
                Node currentNode = result.get();
                openList.remove(currentNode);
                closedList.add(currentNode);

                if (currentNode.x == x2 && currentNode.y == y2) {
                    break;
                }

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;

                        if (currentNode.x + i >= 0 && currentNode.x + i < N) {
                            if (currentNode.y + j >= 0 && currentNode.y + j < M) {

                                Node neighbour = map[currentNode.x + i][currentNode.y + j];

                                if (!closedList.contains(neighbour)) {
                                    if (!openList.contains(neighbour)) {
                                        openList.add(neighbour);
                                        neighbour.parent = currentNode;
                                        neighbour.distance = currentNode.distance + (!currentNode.isLand && neighbour.isLand ? 1 : 0);
                                        neighbour.setHeuristic(x2, y2);
                                        neighbour.setfScore();
                                    } else {
                                        int distance = currentNode.distance + (!currentNode.isLand && neighbour.isLand ? 1 : 0);
                                        if (distance < neighbour.distance) {
                                            neighbour.distance = distance;
                                            neighbour.parent = currentNode;
                                            neighbour.setfScore();
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
            System.out.println(map[x2][y2].distance);
        }
    }

    public static void main(String[] args) throws IOException {
        new Pirates().solve();
    }

    class Node {
        boolean isLand = false;
        int x;
        int y;
        Node parent = null;
        int distance = Integer.MAX_VALUE;
        int fScore = Integer.MAX_VALUE;
        int heuristic = Integer.MAX_VALUE;

        void setHeuristic(int x2, int y2) {
            this.heuristic = Math.abs(x - x2) + Math.abs(y - y2);
        }

        void setfScore() {
            this.fScore = distance + heuristic;
        }
    }
}
