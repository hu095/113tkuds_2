import java.util.*;

/**
 * 題目：LeetCode 40. Combination Sum II
 *
 * 說明：
 *   - 每個數字「最多使用一次」
 *   - 結果中「不可有重複組合」
 *   - 回傳所有和為 target 的唯一組合（順序不限）
 *
 * 作法：
 *   1) 排序，方便剪枝與去重。
 *   2) 回溯：遞迴參數 (start, remain, path)。
 *   3) 去重：同一層若 a[i] 與 a[i-1] 相同，則跳過 (i > start && a[i] == a[i-1])。
 *   4) 每個元素用一次：遞迴時從 i+1 繼續。
 *
 * 編譯：
 *   javac -encoding UTF-8 lt_40_CombinationSum2.java
 * 執行：
 *   java lt_40_CombinationSum2
 */
public class lt_40_CombinationSum2 {

    // ===== LeetCode 可提交版本 =====
    static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates); // 排序便於剪枝 + 去重
            List<List<Integer>> ans = new ArrayList<>();
            backtrack(0, target, new ArrayList<>(), candidates, ans);
            return ans;
        }

        // 回溯：從 start 開始，找所有與 remain 相等的唯一組合
        private void backtrack(int start, int remain, List<Integer> path,
                               int[] a, List<List<Integer>> ans) {
            if (remain == 0) {                    // 命中一組
                ans.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i < a.length; i++) {
                if (i > start && a[i] == a[i - 1]) continue; // 去重
                int v = a[i];
                if (v > remain) break;             // 剪枝
                path.add(v);
                backtrack(i + 1, remain - v, path, a, ans); // i+1 → 避免重複用
                path.remove(path.size() - 1);      // 回溯
            }
        }
    }

    // ===== 測試用 main（比照 LeetCode 輸出格式）=====
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] e1 = {10,1,2,7,6,1,5};
        int t1 = 8;
        List<List<Integer>> r1 = sol.combinationSum2(e1, t1);
        System.out.println("Example 1:\n");
        System.out.println("Input: candidates = " + Arrays.toString(e1) + ", target = " + t1);
        System.out.println("Output:");
        print2D(r1);
        System.out.println();

        // Example 2
        int[] e2 = {2,5,2,1,2};
        int t2 = 5;
        List<List<Integer>> r2 = sol.combinationSum2(e2, t2);
        System.out.println("Example 2:\n");
        System.out.println("Input: candidates = " + Arrays.toString(e2) + ", target = " + t2);
        System.out.println("Output:");
        print2D(r2);
    }

    // 工具函式：輸出 List<List<Integer>>，LeetCode 風格
    private static void print2D(List<List<Integer>> lists) {
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.println("  " + list);
        }
        System.out.println("]");
    }
}
/**
 * 解題思路補充：
 *   - 與 #39 不同點：#39 可重複使用同一元素；#40 僅能使用一次且需去重。
 *   - 去重技巧：
 *       1) 排序讓相同值相鄰。
 *       2) 於同一遞迴層（固定的 start）遇到重複值時略過：i > start && a[i] == a[i-1]。
 *   - 為何遞迴用 i+1？
 *       * 保證同一元素不會在同一路徑中被二次選用。
 *   - 複雜度：
 *       * 取決於答案數量；最壞接近指數，但 n、target 有上限，實測可接受。
 */