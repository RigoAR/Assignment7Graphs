import java.util.*;

public class PathFinder {
    private int vertices;
    private List<List<Edge>> graph;

    // Edge class to store destination and weight
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public PathFinder(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight)
    {  // Added weight parameter
        graph.get(source).add(new Edge(destination, weight));
    }

    //u is start and w is end
    public void findPathsOfLength7(int u, int w) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[vertices];

        path.add(u);
        visited[u] = true;

        findPathsRecursive(u, w, visited, path, 1);  // 1 is current path length
    }

    private void findPathsRecursive(int current, int end, boolean[] visited, List<Integer> path, int pathLength) {
        // If we've used 7 edges (path length is 8 including start and end nodes)
        if (pathLength == 8) {
            if (current == end) {
                printPath(path);
            }
            return;
        }

        // Try all neighbrs
        for (Edge edge : graph.get(current)) {
            int neighbor = edge.destination;
            if (!visited[neighbor]) {
                // Add to path
                visited[neighbor] = true;
                path.add(neighbor);

                // Recurse
                findPathsRecursive(neighbor, end, visited, path, pathLength + 1);

                // Backtrack
                visited[neighbor] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    private void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = scanner.nextInt();

        PathFinder finder = new PathFinder(v);

        System.out.print("Enter number of edges: ");
        int e = scanner.nextInt();

        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < e; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();  // Added weight input
            finder.addEdge(source, destination, weight);
        }

        System.out.print("Enter start vertex: ");
        int start = scanner.nextInt();

        System.out.print("Enter end vertex: ");
        int end = scanner.nextInt();

        System.out.println("Paths of length 7 from " + start + " to " + end + ":");
        finder.findPathsOfLength7(start, end);

        scanner.close();
    }
}