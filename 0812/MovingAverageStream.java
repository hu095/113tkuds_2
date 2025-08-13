import java.util.*;

/**
 * 檔名：MovingAverageStream.java
 * 功能：滑動視窗的移動平均數 + 中位數 + 極值（min/max）
 *
 * 設計：
 * - 佇列 window：維持視窗內元素與先進先出
 * - 和 sum（long）：O(1) 取得平均
 * - 兩個 Heap（lo=MaxHeap, hi=MinHeap）+ 懶刪除 delayed：O(log k) 維持中位數
 * - 兩個單調佇列（minQ 非遞減、maxQ 非遞增）：O(1) 攫取 min/max
 */
public class MovingAverageStream {

    /* ===================== 使用者要用的類別 ===================== */
    public static class MovingAverage {
        private final int k;
        private final Deque<Integer> window = new ArrayDeque<>();
        private long sum = 0;

        // for median
        private final PriorityQueue<Integer> lo = new PriorityQueue<>(Comparator.reverseOrder()); // MaxHeap
        private final PriorityQueue<Integer> hi = new PriorityQueue<>();                           // MinHeap
        private final Map<Integer, Integer> delayed = new HashMap<>();
        private int loSize = 0, hiSize = 0;

        // for min/max
        private final Deque<Integer> minQ = new ArrayDeque<>(); // 單調遞增（隊首最小）
        private final Deque<Integer> maxQ = new ArrayDeque<>(); // 單調遞減（隊首最大）

        public MovingAverage(int size) {
            if (size <= 0) throw new IllegalArgumentException("size must be > 0");
            this.k = size;
        }

        /** 加入新值並回傳當前滑動視窗的平均值 */
        public double next(int val) {
            window.offerLast(val);
            sum += val;
            // median 結構
            addMedian(val);
            // min/max 結構
            pushMin(val);
            pushMax(val);

            if (window.size() > k) {
                int old = window.pollFirst();
                sum -= old;
                removeMedian(old);
                pullMin(old);
                pullMax(old);
            }
            int sz = Math.min(window.size(), k);
            return sum * 1.0 / sz;
        }

        /** 取得視窗中位數；若視窗為空回傳 NaN */
        public double getMedian() {
            if (window.isEmpty()) return Double.NaN;
            if ((currentSize() & 1) == 1) return lo.peek();
            return ((long) lo.peek() + (long) hi.peek()) / 2.0;
        }

        /** 取得視窗最小值 */
        public int getMin() {
            if (window.isEmpty()) throw new NoSuchElementException("window is empty");
            return minQ.peekFirst();
        }

        /** 取得視窗最大值 */
        public int getMax() {
            if (window.isEmpty()) throw new NoSuchElementException("window is empty");
            return maxQ.peekFirst();
        }

        /* -------------------- 內部：Median -------------------- */
        private void addMedian(int x) {
            if (lo.isEmpty() || x <= lo.peek()) { lo.offer(x); loSize++; }
            else { hi.offer(x); hiSize++; }
            rebalance();
        }

        private void removeMedian(int x) {
            delayed.put(x, delayed.getOrDefault(x, 0) + 1);
            if (!lo.isEmpty() && x <= lo.peek()) loSize--; else hiSize--;
            prune(lo); prune(hi);
            rebalance();
        }

        private void rebalance() {
            if (loSize > hiSize + 1) {
                hi.offer(lo.poll()); loSize--; hiSize++; prune(lo);
            } else if (hiSize > loSize) {
                lo.offer(hi.poll()); hiSize--; loSize++; prune(hi);
            }
        }

        private void prune(PriorityQueue<Integer> pq) {
            while (!pq.isEmpty()) {
                int x = pq.peek();
                Integer c = delayed.get(x);
                if (c == null || c == 0) break;
                pq.poll();
                if (c == 1) delayed.remove(x); else delayed.put(x, c - 1);
            }
        }

        private int currentSize() { return loSize + hiSize; }

        /* -------------------- 內部：Min / Max（單調佇列） -------------------- */
        private void pushMin(int x) {
            while (!minQ.isEmpty() && minQ.peekLast() > x) minQ.pollLast();
            minQ.offerLast(x);
        }

        private void pullMin(int x) {
            if (!minQ.isEmpty() && minQ.peekFirst() == x) minQ.pollFirst();
        }

        private void pushMax(int x) {
            while (!maxQ.isEmpty() && maxQ.peekLast() < x) maxQ.pollLast();
            maxQ.offerLast(x);
        }

        private void pullMax(int x) {
            if (!maxQ.isEmpty() && maxQ.peekFirst() == x) maxQ.pollFirst();
        }
    }

    /* ===================== Demo（符合題目測試案例的輸出） ===================== */
    private static String fmt(double v) {
        // 最多兩位小數，去掉多餘的 0 與小數點
        String s = String.format(Locale.ROOT, "%.2f", v);
        if (s.indexOf('.') >= 0) {
            s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        }
        return s;
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println("MovingAverage ma = new MovingAverage(3);");
        double r1 = ma.next(1);
        double r2 = ma.next(10);
        double r3 = ma.next(3);
        double r4 = ma.next(5);
        System.out.println("ma.next(1) = " + fmt(r1));
        System.out.println("ma.next(10) = " + fmt(r2));
        System.out.println("ma.next(3) = " + fmt(r3) + " (1+10+3)/3");
        System.out.println("ma.next(5) = " + fmt(r4) + " (10+3+5)/3");
        System.out.println("ma.getMedian() = " + fmt(ma.getMedian()));
        System.out.println("ma.getMin() = " + ma.getMin());
        System.out.println("ma.getMax() = " + ma.getMax());
    }
}
