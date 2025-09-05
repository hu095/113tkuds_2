// 題目 17 公告全文搜尋
// 檔名：LC28_StrStr_NoticeSearch.java
//
// 輸入：
// haystack（公告全文，一行字串）
// needle（欲搜尋片段，一行字串）
//
// 輸出：
// needle 在 haystack 首次出現的索引，若不存在則輸出 -1
//
// 範例：
// Input :
// hello
// ll
// Output:
// 2

import java.io.*;

public class LC28_StrStr_NoticeSearch {

    // 主解法：暴力比對
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0; // needle 空字串 → 回 0
        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String haystack = br.readLine();
        String needle = br.readLine();

        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        int result = strStr(haystack, needle);
        System.out.println(result);
    }
}
