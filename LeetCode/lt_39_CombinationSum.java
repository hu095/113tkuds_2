import java.util.*;

/**
 * 題目：LeetCode 39. Combination Sum
 *
 * 說明：
 *   給定互異的整數陣列 candidates 與整數 target，找出所有可使元素和為 target 的「唯一組合」。
 *   - 每個元素可被使用「無限次」。
 *   - 兩組合被視為不同，只要至少一個數字的使用次數不同。
 *
 * 作法：
 *   - 先排序（方便剪枝）。
 *   - 回溯：從索引 start 開始枚舉，每次可重複使用當前元素（遞迴時 start 不遞增）。
 *
 */
public class lt_39_CombinationSum {

    // ===== LeetCode 可提交版本（內嵌）=====
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates); // 排序以利剪枝
            List<List<Integer>> ans = new ArrayList<>();
            backtrack(0, target, new ArrayList<>(), candidates, ans);
            return ans;
        }

        // 回溯：從 start 開始，找出所有和為 remain 的組合
        private void backtrack(int start, int remain, List<Integer> path,
                               int[] a, List<List<Integer>> ans) {
            if (remain == 0) {
                ans.add(new ArrayList<>(path)); // 命中一組
                return;
            }
            for (int i = start; i < a.length; i++) {
                int v = a[i];
                if (v > remain) break;          // 排序 → 可剪枝
                path.add(v);                    // 選 v
                backtrack(i, remain - v, path, a, ans); // i 不遞增 → 可重複用
                path.remove(path.size() - 1);   // 回溯
            }
        }
    }

    // ===== 測試用 main（輸出格式與 LeetCode 說明一致）=====
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] c1 = {2, 3, 6, 7};
        int t1 = 7;
        List<List<Integer>> o1 = sol.combinationSum(c1, t1);
        System.out.println("Example 1:\n");
        System.out.println("Input: candidates = " + Arrays.toString(c1) + ", target = " + t1);
        System.out.println("Output: " + list2DToString(o1));
        System.out.println("Explanation:");
        System.out.println("2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.");
        System.out.println("7 is a candidate, and 7 = 7.");
        System.out.println("These are the only two combinations.\n");

        // Example 2
        int[] c2 = {2, 3, 5};
        int t2 = 8;
        List<List<Integer>> o2 = sol.combinationSum(c2, t2);
        System.out.println("Example 2:\n");
        System.out.println("Input: candidates = " + Arrays.toString(c2) + ", target = " + t2);
        System.out.println("Output: " + list2DToString(o2));
        System.out.println();

        // Example 3
        int[] c3 = {2};
        int t3 = 1;
        List<List<Integer>> o3 = sol.combinationSum(c3, t3);
        System.out.println("Example 3:\n");
        System.out.println("Input: candidates = " + Arrays.toString(c3) + ", target = " + t3);
        System.out.println("Output: " + list2DToString(o3));
    }

    // 小工具：把 List<List<Integer>> 以 LeetCode 風格輸出
    private static String list2DToString(List<List<Integer>> lists) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lists.size(); i++) {
            sb.append(lists.get(i));
            if (i + 1 < lists.size()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}

/**
 * 解題思路：
 *   1) 排序 candidates，確保在回溯時能以「v > remain → break」做剪枝。
 *   2) backtrack(start, remain, path)：
 *      - remain == 0：找到一組，加入答案。
 *      - i 從 start 到末端：
 *          * 若 a[i] > remain：直接 break。
 *          * 選 a[i] → path 加入 a[i]，遞迴 backtrack(i, remain - a[i], path)
 *            （i 不變，代表可以重複使用同一元素）
 *          * 回溯：移除最後一個元素。
 *   3) 因為只能往右搜且不回頭（start 控制），自然避免了同組合的不同順序重複。
 *
 * 時間複雜度：
 *   - 取決於可行組合數量；最壞情況接近指數，但測資保證組合數 < 150，可接受。
 */
