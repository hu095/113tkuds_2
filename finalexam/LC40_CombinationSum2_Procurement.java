// 題目 20 防災物資組合總和 (II)
// 檔名：LC40_CombinationSum2_Procurement.java
//
// 輸入：
// n target
// n 個整數（可含重複價格）
//
// 輸出：
// 每行一個升序組合（組內遞增；每個價格最多使用一次；去除重複組合）
// 無解則不輸出任何行
//
// 範例：
// Input:
// 7 8
// 10 1 2 7 6 1 5
// Output:
// 1 1 6
// 1 2 5
// 1 7
// 2 6

import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    private static void dfs(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> out) {
        if (remain == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            // 同層跳過重複，避免組合重複
            if (i > start && a[i] == a[i - 1]) continue;
            if (a[i] > remain) break; // 剪枝
            path.add(a[i]);
            dfs(a, i + 1, remain - a[i], path, out); // II 版：i+1（每價用一次）
            path.remove(path.size() - 1);
        }
    }

    private static void sortCombinations(List<List<Integer>> out) {
        out.sort((x, y) -> {
            int n = Math.min(x.size(), y.size());
            for (int i = 0; i < n; i++) {
                int c = Integer.compare(x.get(i), y.get(i));
                if (c != 0) return c;
            }
            return Integer.compare(x.size(), y.size());
        });
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String[] first = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int target = Integer.parseInt(first[1]);

        String[] parts = n > 0 ? br.readLine().trim().split("\\s+") : new String[0];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(parts[i]);

        Arrays.sort(a);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(a, 0, target, new ArrayList<>(), ans);
        sortCombinations(ans);

        for (List<Integer> comb : ans) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) out.print(" ");
                out.print(comb.get(i));
            }
            out.println();
        }
        out.flush();
    }
}
