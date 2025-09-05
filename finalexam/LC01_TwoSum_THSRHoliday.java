import java.util.*;

/**
 * 題目 1 高鐵連假加班車 Two Sum
 * 檔名：LC01_TwoSum_THSRHoliday.java
 *
 * 讀入：
 *   第一行：n target
 *   第二行：n 個整數（座位數）
 *
 * 輸出：
 *   兩個索引（i j），若無解輸出 -1 -1
 */
public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();      // 班次數量
        int target = sc.nextInt(); // 需求總座位
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] ans = twoSumIndices(nums, target);

        // 題目要求：輸出兩個索引或 -1 -1
        System.out.println(ans[0] + " " + ans[1]);

        sc.close();
    }

    /** 依提示使用 HashMap<需要的數, 索引>，單趟掃描完成 */
    private static int[] twoSumIndices(int[] nums, int target) {
        Map<Integer, Integer> needToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            // 若有人「曾經需要 x」，就湊成一組
            if (needToIndex.containsKey(x)) {
                return new int[]{needToIndex.get(x), i};
            }

            // 記錄「還需要 target - x」對應目前索引
            needToIndex.put(target - x, i);
        }
        return new int[]{-1, -1};
    }
}
