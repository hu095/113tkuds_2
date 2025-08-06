
public class MatrixCalculator {
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.printf("%4d", val);
            System.out.println();
        }
    }

    public static int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < b.length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int[][] t = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                t[j][i] = matrix[i][j];
        return t;
    }

    public static int[] findMinMax(int[][] matrix) {
        int min = matrix[0][0], max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) min = val;
                if (val > max) max = val;
            }
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
    int[][] a = {
        {1, 2},
        {3, 4}
    };
    int[][] b = {
        {5, 6},
        {7, 8}
    };

    System.out.println("矩陣 A:");
    printMatrix(a);
    System.out.println("矩陣 B:");
    printMatrix(b);

    System.out.println("\n矩陣加法 A + B:");
    printMatrix(add(a, b));

    System.out.println("\n矩陣乘法 A * B:");
    printMatrix(multiply(a, b));

    System.out.println("\n矩陣轉置 A:");
    printMatrix(transpose(a));

    System.out.println("\n矩陣轉置 B:");
    printMatrix(transpose(b));  // 加這一行即可印出 B 的轉置

    int[] minMaxA = findMinMax(a);
    int[] minMaxB = findMinMax(b);

    System.out.printf("\nA 的最小值：%d，最大值：%d\n", minMaxA[0], minMaxA[1]);
    System.out.printf("B 的最小值：%d，最大值：%d\n", minMaxB[0], minMaxB[1]);
    }
}
