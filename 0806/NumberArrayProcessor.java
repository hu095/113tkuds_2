import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {1, 3, 5, 7, 9};

        System.out.println("=== 移除重複元素 ===");
        System.out.println(Arrays.toString(removeDuplicates(arr1)));

        System.out.println("\n=== 合併兩個排序陣列 ===");
        System.out.println(Arrays.toString(mergeSortedArrays(arr1, arr2)));

        System.out.println("\n=== 出現頻率最高的元素 ===");
        System.out.println("出現最多的值為：" + findMostFrequent(arr1));

        System.out.println("\n=== 陣列分割 ===");
        splitArray(arr1);
    }

    public static int[] removeDuplicates(int[] array) {
        return Arrays.stream(array).distinct().toArray();
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
            result[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    public static int findMostFrequent(int[] array) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : array)
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void splitArray(int[] array) {
        int sum = Arrays.stream(array).sum();
        int half = sum / 2;

        List<Integer> part1 = new ArrayList<>();
        int currentSum = 0;

        for (int i = 0; i < array.length; i++) {
            if (currentSum + array[i] <= half) {
                currentSum += array[i];
                part1.add(array[i]);
            }
        }

        System.out.println("子陣列 1：" + part1);
        System.out.println("子陣列 2：" + Arrays.toString(
            Arrays.stream(array).filter(x -> !part1.contains(x) || part1.remove((Integer) x)).toArray()
        ));
    }
}
