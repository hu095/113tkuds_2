import java.util.Arrays;

public class AdvancedArrayRecursion {

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 2. 遞迴合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        return mergeRecursive(arr1, arr2, 0, 0);
    }

    private static int[] mergeRecursive(int[] arr1, int[] arr2, int i, int j) {
        if (i == arr1.length) {
            return Arrays.copyOfRange(arr2, j, arr2.length);
        }
        if (j == arr2.length) {
            return Arrays.copyOfRange(arr1, i, arr1.length);
        }

        if (arr1[i] < arr2[j]) {
            int[] rest = mergeRecursive(arr1, arr2, i + 1, j);
            return prepend(arr1[i], rest);
        } else {
            int[] rest = mergeRecursive(arr1, arr2, i, j + 1);
            return prepend(arr2[j], rest);
        }
    }

    private static int[] prepend(int value, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = value;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    // 3. 遞迴尋找第 k 小元素（使用 QuickSelect）
    public static int findKthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    // 4. 遞迴檢查是否存在子序列總和等於目標值
    public static boolean isSubsetSum(int[] arr, int target) {
        return isSubsetSumRecursive(arr, arr.length, target);
    }

    private static boolean isSubsetSumRecursive(int[] arr, int n, int sum) {
        if (sum == 0) return true;
        if (n == 0) return false;

        if (arr[n - 1] > sum) {
            return isSubsetSumRecursive(arr, n - 1, sum);
        }

        return isSubsetSumRecursive(arr, n - 1, sum)
            || isSubsetSumRecursive(arr, n - 1, sum - arr[n - 1]);
    }

    // 測試用 main 函式
    public static void main(String[] args) {
        // 測試 quickSort
        int[] arr = {5, 2, 9, 1, 5, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(arr));

        // 測試 mergeSortedArrays
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] merged = mergeSortedArrays(a, b);
        System.out.println("Merged: " + Arrays.toString(merged));

        // 測試 findKthSmallest
        int[] c = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("第 " + k + " 小元素是: " + findKthSmallest(c, k));

        // 測試 isSubsetSum
        int[] d = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在總和為 " + target + " 的子序列: " + isSubsetSum(d, target));
    }
}
