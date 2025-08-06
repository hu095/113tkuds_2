import java.util.*;

public class NumberArrayProcessor {

    // 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }

    // 找出陣列中出現頻率最高的所有元素
    public static List<Integer> findMostFrequent(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = Collections.max(freqMap.values());
        List<Integer> mostFrequent = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == maxFreq) {
                mostFrequent.add(entry.getKey());
            }
        }
        return mostFrequent;
    }

    // 將陣列分割成兩個相等（或近似相等）的子陣列
    public static int[][] splitArray(int[] array) {
        int mid = array.length / 2;
        int[] firstHalf = Arrays.copyOfRange(array, 0, mid);
        int[] secondHalf = Arrays.copyOfRange(array, mid, array.length);
        return new int[][]{firstHalf, secondHalf};
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 3, 4, 4, 5};
        int[] array2 = {2, 3, 6, 7};

        System.out.println("原始陣列 array1: " + Arrays.toString(array1));
        System.out.println("原始陣列 array2: " + Arrays.toString(array2));

        // 移除重複
        int[] noDuplicates = removeDuplicates(array1);
        System.out.println("移除重複後: " + Arrays.toString(noDuplicates));

        // 合併已排序陣列
        int[] merged = mergeSortedArrays(array1, array2);
        System.out.println("合併後的排序陣列: " + Arrays.toString(merged));

        // 找出最常出現的元素
        List<Integer> mostFrequent = findMostFrequent(array1);
        System.out.println("最常出現的元素: " + mostFrequent);

        // 陣列分割
        int[][] split = splitArray(array1);
        System.out.println("分割後第一部分: " + Arrays.toString(split[0]));
        System.out.println("分割後第二部分: " + Arrays.toString(split[1]));
    }
}
