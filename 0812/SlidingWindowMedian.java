import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 小的一半
    private PriorityQueue<Integer> minHeap; // 大的一半

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = findMedian();
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        double[] res1 = swm.medianSlidingWindow(nums1, k1);
        System.out.println("輸入：" + Arrays.toString(nums1) + ", K=" + k1);
        System.out.println("輸出：" + Arrays.toString(res1));
        System.out.println("解釋：");
        for (int i = 0; i <= nums1.length - k1; i++) {
            int[] window = Arrays.copyOfRange(nums1, i, i + k1);
            System.out.println(Arrays.toString(window) + " → 中位數 " + res1[i]);
        }

        System.out.println();

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        double[] res2 = swm.medianSlidingWindow(nums2, k2);
        System.out.println("輸入：" + Arrays.toString(nums2) + ", K=" + k2);
        System.out.println("輸出：" + Arrays.toString(res2));
        System.out.println("解釋：");
        for (int i = 0; i <= nums2.length - k2; i++) {
            int[] window = Arrays.copyOfRange(nums2, i, i + k2);
            System.out.println(Arrays.toString(window) + " → 中位數 " + res2[i]);
        }
    }
}
