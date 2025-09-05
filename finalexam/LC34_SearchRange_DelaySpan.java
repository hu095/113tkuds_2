// 題目 19 延誤等級首末定位
// 檔名：LC34_SearchRange_DelaySpan.java
//
// 輸入：
// n target
// n 個已排序（非遞減）整數
//
// 輸出：
// target 的 [首個索引 最後索引]，若不存在印出 "-1 -1"
//
// 範例：
// Input :
// 6 8
// 5 7 7 8 8 10
// Output:
// 3 4

import java.io.*;

public class LC34_SearchRange_DelaySpan {

    // lower_bound：回傳第一個 >= x 的索引；若都小於 x，回 n
    static int lowerBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // upper_bound：回傳第一個 > x 的索引；若都 <= x，回 n
    static int upperBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // 主函式：用兩次 lower_bound/upper_bound 求 [L, R]
    static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int L = lowerBound(nums, target);
        if (L == n || nums[L] != target) return new int[]{-1, -1};
        int R = upperBound(nums, target) - 1;
        return new int[]{L, R};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            out.println("-1 -1");
            out.flush();
            return;
        }
        String[] ft = first.trim().split("\\s+");
        int n = Integer.parseInt(ft[0]);
        int target = Integer.parseInt(ft[1]);

        int[] nums = new int[n];
        if (n > 0) {
            String line = br.readLine();
            String[] parts = (line == null ? "" : line.trim()).split("\\s+");
            for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);
        }

        int[] ans = searchRange(nums, target);
        out.println(ans[0] + " " + ans[1]);
        out.flush();
    }
}
