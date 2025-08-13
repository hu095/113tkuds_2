import java.util.*;

public class MeetingRoomScheduler {

    // ========= Part 1：最少需要多少個會議室 =========
    // 思路：按開始時間排序，Min-Heap 存當前使用中會議室的「結束時間」。
    // 若最早結束時間 <= 新會議開始，表示可重用該房間（pop 再 push 新結束時間）；否則開新房間。
    public static int minRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> ends = new PriorityQueue<>(); // 最早結束在頂端
        for (int[] iv : intervals) {
            if (!ends.isEmpty() && ends.peek() <= iv[0]) {
                ends.poll(); // 釋放一間
            }
            ends.offer(iv[1]); // 佔用/續用一間
        }
        return ends.size();
    }

    // ========= Part 2：只有 1 間會議室時，最大化總會議時間 =========
    // 經典「加權區間排程」：
    // 1) 依結束時間排序
    // 2) p[i] = i 之前與 i 不重疊之最後一個區間
    // 3) dp[i] = max(dp[i-1], weight[i] + dp[p[i]])
    // 回溯選出最佳解（為了輸出安排）。
    public static class SingleRoomPlan {
        int totalTime;
        List<int[]> chosen; // 選到的區間列表（[start,end]）

        SingleRoomPlan(int totalTime, List<int[]> chosen) {
            this.totalTime = totalTime;
            this.chosen = chosen;
        }
    }

    public static SingleRoomPlan maxTotalTimeSingleRoom(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new SingleRoomPlan(0, new ArrayList<>());

        // 複製一份避免改動原資料
        int n = intervals.length;
        int[][] ivs = new int[n][2];
        for (int i = 0; i < n; i++) { ivs[i][0] = intervals[i][0]; ivs[i][1] = intervals[i][1]; }

        Arrays.sort(ivs, Comparator.comparingInt(a -> a[1])); // 按結束時間
        int[] start = new int[n], end = new int[n], w = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = ivs[i][0];
            end[i] = ivs[i][1];
            w[i] = Math.max(0, end[i] - start[i]); // 時長
        }

        // p[i]：在 i 之前與 i 不重疊之最後一個區間索引
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1, ans = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (end[mid] <= start[i]) { ans = mid; lo = mid + 1; }
                else hi = mid - 1;
            }
            p[i] = ans;
        }

        // DP
        int[] dp = new int[n];
        boolean[] take = new boolean[n];
        for (int i = 0; i < n; i++) {
            int takeVal = w[i] + (p[i] == -1 ? 0 : dp[p[i]]);
            int skipVal = (i == 0 ? 0 : dp[i - 1]);
            if (takeVal >= skipVal) {
                dp[i] = takeVal;
                take[i] = true;
            } else {
                dp[i] = skipVal;
                take[i] = false;
            }
        }

        // 回溯選出的區間
        List<int[]> chosen = new ArrayList<>();
        for (int i = n - 1; i >= 0; ) {
            if (take[i]) {
                chosen.add(new int[]{start[i], end[i]});
                i = p[i];
            } else {
                i = i - 1;
            }
        }
        Collections.reverse(chosen);
        return new SingleRoomPlan(dp[n - 1], chosen);
    }

    // ========= Demo：依照題目格式輸出 =========
    private static void printMinRoomsCase(int[][] meetings) {
        System.out.println("會議：" + Arrays.deepToString(meetings));
        System.out.println("答案：需要" + minRooms(meetings) + "個會議室\n");
    }

    private static void printSingleRoomBest(int[][] meetings) {
        System.out.println("如果只有1個會議室，會議：" + Arrays.deepToString(meetings));
        SingleRoomPlan plan = maxTotalTimeSingleRoom(meetings);
        System.out.print("最佳安排：");
        if (plan.chosen.isEmpty()) {
            System.out.println("[]，總時間 = 0\n");
            return;
        }
        // 列出選到的區間
        System.out.print("選擇");
        for (int i = 0; i < plan.chosen.size(); i++) {
            int[] iv = plan.chosen.get(i);
            System.out.print("[" + iv[0] + "," + iv[1] + "]");
            if (i + 1 < plan.chosen.size()) System.out.print("和");
        }
        System.out.println("，總時間 = " + plan.totalTime + "\n");
    }

    public static void main(String[] args) {
        // Part 1：最少會議室數
        printMinRoomsCase(new int[][]{{0,30},{5,10},{15,20}});   // 需要2個會議室
        printMinRoomsCase(new int[][]{{9,10},{4,9},{4,17}});     // 需要2個會議室
        printMinRoomsCase(new int[][]{{1,5},{8,9},{8,9}});       // 需要2個會議室

        // Part 2：只有 1 間會議室時，最大總時間
        printSingleRoomBest(new int[][]{{1,4},{2,3},{4,6}});     // 選擇[1,4]和[4,6]，總時間=5
    }
}
