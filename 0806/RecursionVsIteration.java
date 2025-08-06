public class RecursionVsIteration {

    // 1. 計算二項式係數 C(n, k)
    // 遞迴版本
    public static long binomialCoefficientRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    // 迭代版本 (利用動態規劃)
    public static long binomialCoefficientIterative(int n, int k) {
        long[] C = new long[k + 1];
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                C[j] = C[j] + C[j - 1];
            }
        }
        return C[k];
    }


    // 2. 尋找陣列中所有元素的乘積
    // 遞迴版本
    public static long productRecursive(int[] arr, int index) {
        if (index >= arr.length) {
            return 1;  // 乘積的中性元素
        }
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 迭代版本
    public static long productIterative(int[] arr) {
        long product = 1;
        for (int val : arr) {
            product *= val;
        }
        return product;
    }


    // 3. 計算字串中元音字母的數量
    // 遞迴版本
    public static int countVowelsRecursive(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        char c = Character.toLowerCase(str.charAt(index));
        int count = ("aeiou".indexOf(c) != -1) ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    // 迭代版本
    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }


    // 4. 檢查括號是否配對正確
    // 遞迴版本 (只考慮 '(' 和 ')' )
    public static boolean isBalancedRecursive(String str) {
        return isBalancedHelper(str, 0, 0);
    }

    private static boolean isBalancedHelper(String str, int index, int count) {
        if (count < 0) return false;  // 遇到右括號多於左括號
        if (index == str.length()) return count == 0;
        char c = str.charAt(index);
        if (c == '(') {
            return isBalancedHelper(str, index + 1, count + 1);
        } else if (c == ')') {
            return isBalancedHelper(str, index + 1, count - 1);
        } else {
            return isBalancedHelper(str, index + 1, count);
        }
    }

    // 迭代版本
    public static boolean isBalancedIterative(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }


    public static void main(String[] args) {
        // 測試資料
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String testStr = "Hello (world) (())";

        // 1. 二項式係數
        System.out.println("=== 二項式係數 ===");
        System.out.println("遞迴: C(" + n + ", " + k + ") = " + binomialCoefficientRecursive(n, k));
        System.out.println("迭代: C(" + n + ", " + k + ") = " + binomialCoefficientIterative(n, k));

        // 2. 陣列乘積
        System.out.println("\n=== 陣列乘積 ===");
        System.out.println("遞迴: 乘積 = " + productRecursive(arr, 0));
        System.out.println("迭代: 乘積 = " + productIterative(arr));

        // 3. 元音字母計數
        System.out.println("\n=== 元音字母計數 ===");
        System.out.println("遞迴: 元音數 = " + countVowelsRecursive(testStr, 0));
        System.out.println("迭代: 元音數 = " + countVowelsIterative(testStr));

        // 4. 括號配對檢查
        System.out.println("\n=== 括號配對檢查 ===");
        System.out.println("遞迴: 配對正確？ " + isBalancedRecursive(testStr));
        System.out.println("迭代: 配對正確？ " + isBalancedIterative(testStr));
    }
}
