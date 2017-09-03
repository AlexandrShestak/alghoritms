import java.util.*;

public class Solution {

    //max weight number is Math.pow(10, 8) which binary representation is 101 1111 0101 1110 0001 0000 0000
    //so max weight bit is 27
    private static final int MAX_WEIGHT_BIT_NUMBER = 27;

    static int vertexCount = 0;
    static int edgesCount = 0;
    static Map<Integer, Set<Edge>> graph = new HashMap<>();

    public static void main(String[] args) {
        readGraph();

        PathAndCycles pathAndCycles = findPathToEndVertexAndAllCycles();
        Set<Integer> cyclesWeights = pathAndCycles.cycles;
        int anyPathLength = pathAndCycles.path;

        Set<Integer> cyclesBasis = findCyclesBasis(new ArrayList<>(cyclesWeights));

        int shortestPath = findShortestPath(anyPathLength, cyclesBasis);
        System.out.println(shortestPath);
    }

    /**
     *  Use cycles basis to minimize any path and as result return shortest path
     */
    private static int findShortestPath(int anyPathLength, Set<Integer> cyclesBasis) {
        for (Integer vectorFromBasis : cyclesBasis) {
            int highestBit = Integer.highestOneBit(vectorFromBasis);
            if ((anyPathLength & highestBit) != 0) {
                anyPathLength ^= vectorFromBasis;
            }
        }
        return anyPathLength;
    }

    private static void readGraph() {
        Scanner sc = new Scanner(System.in);

        vertexCount = sc.nextInt();
        edgesCount = sc.nextInt();

        for (int i = 0; i < edgesCount; i++) {
            int fromVertex = sc.nextInt() -1;
            int toVertex = sc.nextInt() -1;
            int weight = sc.nextInt();

            addEdge(fromVertex, toVertex, weight);
            if (toVertex != fromVertex) {
                addEdge(toVertex, fromVertex, weight);
            }
        }
    }

    /**
     *  Find basis of vectors from cycles of graph
     */
    private static Set<Integer> findCyclesBasis(List<Integer> cycles) {
        int iteration = 0;
        for (int bitPosition = MAX_WEIGHT_BIT_NUMBER ; bitPosition >= 0 ; bitPosition--) {
            int bitToCheckIntRepresentation = 1 << bitPosition;

            int positionToSwap = -1;
            for (int i = iteration ; i < cycles.size() ; i++) {
                if ((cycles.get(i) & bitToCheckIntRepresentation) != 0) {
                    positionToSwap = i;
                    break;
                }
            }
            if (positionToSwap != -1) {
                //swap
                Integer temp = cycles.get(iteration);
                cycles.set(iteration, cycles.get(positionToSwap));
                cycles.set(positionToSwap, temp);

                for (int i = 0; i < cycles.size(); i++) {
                    if (i == iteration || (cycles.get(i) & bitToCheckIntRepresentation) == 0) {
                        continue;
                    }
                    cycles.set(i, cycles.get(i) ^ cycles.get(iteration));
                }
                iteration++;
            }
        }

        return new HashSet<>(cycles);
    }

    private static void addEdge(int fromVertex, int toVertex, int weight) {
        if (!graph.containsKey(fromVertex)) {
            graph.put(fromVertex, new HashSet<>());
        }
        graph.get(fromVertex).add(new Edge(toVertex, weight));
    }


    static class PathAndCycles {
        Set<Integer> cycles;
        int path;
    }

    /**
     * Find any path from start vertex to end vertex and all cycles in graph.
     * This done in one function to save time and do two operations by one traversal of a graph
     */
    private static PathAndCycles findPathToEndVertexAndAllCycles() {
        int[] distances = new int[vertexCount];
        Arrays.fill(distances, -1);
        distances[0] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        Set<Integer> cycles = new HashSet<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Edge edge : graph.get(cur)) {
                int d = distances[cur] ^ edge.weight.intValue();
                int next = edge.toVertex;
                if (distances[next] == -1) {
                    distances[next] = d;
                    q.add(next);
                    continue;
                }
                cycles.add(d ^ distances[next]);
            }
        }
        PathAndCycles pathAndCycles = new PathAndCycles();
        pathAndCycles.cycles = cycles;
        pathAndCycles.path = distances[vertexCount -1];
        return pathAndCycles;
    }

    private static class Edge {
        private Integer toVertex;
        private Integer weight;

        Edge(Integer toVertex, Integer weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
}