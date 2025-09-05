// 題目 15 去重學生成績單
// 檔名：LC26_RemoveDuplicates_Scores.java
//
// 輸入：
// n
// n 個已排序整數（可能有重複）
//
// 輸出：
// 新長度
// 去重後的前段結果（以空白分隔）
//
// 範例：
// Input :
// 7
// 0 0 1 1 1 2 2
// Output:
// 3
// 0 1 2

import java.io.*;

public class LC26_RemoveDuplicates_Scores {

    // 主解法：原地去重，返回新長度
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // 慢指標，指向最後一個不重複元素
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1; // 新長度
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String nLine = br.readLine();
        if (nLine == null || nLine.trim().isEmpty()) {
            out.println(0);
            out.flush();
            return;
        }

        int n = Integer.parseInt(nLine.trim());
        if (n == 0) {
            out.println(0);
            out.flush();
            return;
        }

        String[] parts = br.readLine().trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        int newLen = removeDuplicates(nums);
        out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            if (i > 0) out.print(" ");
            out.print(nums[i]);
        }
        out.println();

        out.flush();
    }
}
