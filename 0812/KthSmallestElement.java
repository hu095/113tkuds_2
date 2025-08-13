import java.util.*;

/**
 * 題目 4：找到陣列中第 K 小的元素（使用 Heap）
 *
 * 方法1：大小為 K 的 Max Heap（維護目前最小的 K 個；堆頂是這 K 個中的最大值 → 也就是第 K 小）
 *   - 時間：O(n log k)
 *   - 空間：O(k)
 *
 * 方法2：Min Heap 直接取出 K 次
 *   - 時間：O(n) 建堆 + O(k log n) 取出 = O(n + k log n)
 *   - 空間：O(n)
 */
public class KthSmallestElement {

    /** 方法1：大小為 K 的 Max Heap */
    public static int kthSmallestByMaxHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("invalid input");
        }
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : nums) {
            max.offer(x);
            if (max.size() > k) max.poll();  // 只保留最小的 K 個
        }
        return max.peek(); // 此時堆頂是第 K 小
    }

    /** 方法2：Min Heap 取出 K 次 */
    public static int kthSmallestByMinHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("invalid input");
        }
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int x : nums) min.offer(x);
        int ans = -1;
        for (int i = 0; i < k; i++) ans = min.poll();
        return ans;
    }

    // ===== 簡單效能比較（nanoseconds）與測試 =====
    private static void runCase(int[] arr, int k) {
        System.out.println("陣列: " + Arrays.toString(arr) + ", K=" + k);

        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int expected = sorted[k - 1];
        System.out.println("排序後: " + Arrays.toString(sorted));
        System.out.println("正確答案: " + expected);

        long t1 = System.nanoTime();
        int a = kthSmallestByMaxHeap(arr, k);
        long t2 = System.nanoTime();
        int b = kthSmallestByMinHeap(arr, k);
        long t3 = System.nanoTime();

        System.out.println("\n方法1 - Max Heap (O(n log k)) → 結果: " + a + ", 時間: " + (t2 - t1));
        System.out.println("方法2 - Min Heap 取 K 次 (O(n + k log n)) → 結果: " + b + ", 時間: " + (t3 - t2));
        System.out.println("=========================================\n");
    }

    public static void main(String[] args) {
        runCase(new int[]{7, 10, 4, 3, 20, 15}, 3);      // 7
        runCase(new int[]{1}, 1);                         // 1
        runCase(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 4);    // 3

        // 小提示：
        // - 當 k 遠小於 n 時，方法1 通常更快更省空間。
        // - 若你已經需要遍歷並放進堆、而且 k 接近 n，方法2 有時更直觀。
    }
}
