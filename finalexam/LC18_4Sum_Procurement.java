// 題目 9 採購限額 4Sum
// 檔名：LC18_4Sum_Procurement.java
//
// 輸入：
// n target
// n 個整數
//
// 輸出：多行四元組（遞增，且各行以空白分隔；不重複）
// 無解則不輸出任何行
//
// 範例：
// Input:
// 6 0
// 1 0 -1 0 -2 2
// Output:
// -2 -1 1 2
// -2 0 0 2
// -1 0 0 1
//
// 作法：排序 → 枚舉 i、j → 夾逼 L、R；嚴格去重

import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {

    static List<List<Integer>> fourSum(int[] a, long target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = a.length;
        Arrays.sort(a);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue; // i 去重

            // 剪枝（可選）：最小可能和 / 最大可能和
            long minI = (long)a[i] + a[i + 1] + a[i + 2] + a[i + 3];
            if (minI > target) break;
            long maxI = (long)a[i] + a[n - 1] + a[n - 2] + a[n - 3];
            if (maxI < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue; // j 去重

                long minJ = (long)a[i] + a[j] + a[j + 1] + a[j + 2];
                if (minJ > target) break;
                long maxJ = (long)a[i] + a[j] + a[n - 1] + a[n - 2];
                if (maxJ < target) continue;

                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = (long)a[i] + a[j] + a[L] + a[R];
                    if (sum == target) {
                        res.add(Arrays.asList(a[i], a[j], a[L], a[R]));
                        int x = a[L], y = a[R];
                        while (L < R && a[L] == x) L++; // L 去重
                        while (L < R && a[R] == y) R--; // R 去重
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String[] ft = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(ft[0]);
        long target = Long.parseLong(ft[1]);

        String[] parts = n > 0 ? br.readLine().trim().split("\\s+") : new String[0];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(parts[i]);

        List<List<Integer>> ans = fourSum(a, target);
        for (List<Integer> q : ans) {
            for (int i = 0; i < q.size(); i++) {
                if (i > 0) out.print(" ");
                out.print(q.get(i));
            }
            out.println();
        }
        out.flush();
    }
}
