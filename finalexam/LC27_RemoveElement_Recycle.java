// 題目 16 回收站清單移除指定元素
// 檔名：LC27_RemoveElement_Recycle.java
//
// 輸入：
// n val
// n 個整數
//
// 輸出：
// 新長度
// 移除 val 後的前段內容（以空白分隔）
//
// 範例：
// Input :
// 4 2
// 3 2 2 3
// Output:
// 2
// 3 3

import java.io.*;

public class LC27_RemoveElement_Recycle {

    // 主解法：就地移除 val
    public static int removeElement(int[] nums, int val) {
        int i = 0; // 寫入位置
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i; // 新長度
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));

        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) {
            out.println(0);
            out.flush();
            return;
        }

        String[] first = line1.trim().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int val = Integer.parseInt(first[1]);

        if (n == 0) {
            out.println(0);
            out.flush();
            return;
        }

        String[] parts = br.readLine().trim().split("\\s+");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(parts[i]);

        int newLen = removeElement(nums, val);

        out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            if (i > 0) out.print(" ");
            out.print(nums[i]);
        }
        out.println();

        out.flush();
    }
}
