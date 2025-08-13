import java.util.*;

public class MergeKSortedArrays {

    static class Node {
        int val, arrIdx, idxInArr;
        Node(int v, int a, int i) { val = v; arrIdx = a; idxInArr = i; }
    }

    public static int[] merge(int[][] arrays) {
        if (arrays == null) return new int[0];

        int total = 0;
        for (int[] a : arrays) total += (a == null ? 0 : a.length);
        if (total == 0) return new int[0];

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        int[] res = new int[total];
        int p = 0;
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            res[p++] = cur.val;
            int ni = cur.idxInArr + 1;
            if (ni < arrays[cur.arrIdx].length) {
                minHeap.offer(new Node(arrays[cur.arrIdx][ni], cur.arrIdx, ni));
            }
        }
        return res;
    }

    private static void testCase(int[][] input) {
        System.out.println("輸入：" + Arrays.deepToString(input));
        System.out.println("輸出：" + Arrays.toString(merge(input)));
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        testCase(new int[][]{{1,4,5}, {1,3,4}, {2,6}});
        testCase(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});
        testCase(new int[][]{{1}, {0}});
    }
}
