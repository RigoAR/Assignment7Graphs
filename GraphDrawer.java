import java.util.*;
import java.util.regex.*;

public class GraphDrawer {
    private String[] vertices;
    private int[] values;
    private int[][] matrix;

    public GraphDrawer(String input) {
        ArrayList<String> namesList = new ArrayList<>();
        ArrayList<Integer> valuesList = new ArrayList<>();

        //parses the entire input string
        Pattern pattern = Pattern.compile("\\(([A-Za-z]),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            namesList.add(matcher.group(1));
            valuesList.add(Integer.parseInt(matcher.group(2)));
        }

        // Convert to arrays
        int n = namesList.size();
        vertices = namesList.toArray(new String[0]);
        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = valuesList.get(i);
        }

        // Create adjacency matrx
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            // Right con
            int rightx = (i + values[i]) % n;
            matrix[i][rightx] = 1;

            // Left con
            int leftx = (i - values[i] + n) % n;
            matrix[i][leftx] = 1;
        }

        printAdjacencyMatrix();
    }

    private void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix Representation:");

//cloumn header
        System.out.print("  ");
        for (String v : vertices) {
            System.out.print(v + " ");
        }
        System.out.println();

    //matrix rows
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + " ");
            for (int j = 0; j < vertices.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // connections
        System.out.println("\nGraph Connections:");
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + " connects to: ");
            boolean first = true;
            for (int j = 0; j < vertices.length; j++) {
                if (matrix[i][j] == 1) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(vertices[j]);
                    first = false;
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        String input = scanner.nextLine();
        scanner.close();

        new GraphDrawer(input);
    }
}