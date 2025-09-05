// 題目 20 防災物資組合總和 (I)
// 檔名：LC39_CombinationSum_PPE.java
//
// 輸入：
// n target
// n 個整數（可含重複價格）
//
// 輸出：
// 每行一個升序組合（組內遞增；組與組之間以換行分隔）
// 無解則不輸出任何行
//
// 範例：
// Input:
// 4 7
// 2 3 6 7
// Output:
// 2 2 3
// 7

import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {

    private static void dfs(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> out) {
        if (remain == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            // 同層去重（處理輸入可能含重複價格）
            if (i > start && a[i] == a[i - 1]) continue;
            if (a[i] > remain) break; // 剪枝
            path.add(a[i]);
            dfs(a, i, remain - a[i], path, out); // I 版：i 不遞增 → 可重複使用
            path.remove(path.size() - 1);
        }
    }

    // 依字典序排序組合（穩定輸出）
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
