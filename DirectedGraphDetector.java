import java.util.Scanner;

public class DirectedGraphDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrix: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        System.out.println("Enter the matrix elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean isDirected = isDirectedGraph(matrix, n);

        if (isDirected) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix represents an undirected graph.");
        }

        scanner.close();
    }

    public static boolean isDirectedGraph(int[][] matrix, int n) {
        // Check if matrix is symmetric
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }
}