import java.util.Arrays;

public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };

        System.out.println("=== 矩陣加法 ===");
        printMatrix(addMatrices(A, B));

        System.out.println("\n=== 矩陣乘法 ===");
        printMatrix(multiplyMatrices(A, B));

        System.out.println("\n=== 矩陣轉置 ===");
        printMatrix(transposeMatrix(B));

        System.out.println("\n=== 矩陣最大值與最小值 ===");
        findMinMax(A);
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                result[j][i] = matrix[i][j];
        return result;
    }

    public static void findMinMax(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];
        for (int[] row : matrix)
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        System.out.println("最大值：" + max + "，最小值：" + min);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }
}
