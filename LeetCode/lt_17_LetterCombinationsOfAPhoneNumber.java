import java.util.*;

// 題目：電話號碼的字母組合
// 給定一個僅包含數字 2–9 的字串 digits，
// 回傳所有它能表示的字母組合（電話按鍵的對應）。
// 可以以任意順序回傳答案。
public class lt_17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String digits1 = "23";
        System.out.println("Example 1:");
        System.out.println("Input:  " + digits1);
        System.out.println("Output: " + sol.letterCombinations(digits1));
        System.out.println("Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]\n");

        // Example 2
        String digits2 = "";
        System.out.println("Example 2:");
        System.out.println("Input:  \"" + digits2 + "\"");
        System.out.println("Output: " + sol.letterCombinations(digits2));
        System.out.println("Expected: []\n");

        // Example 3
        String digits3 = "2";
        System.out.println("Example 3:");
        System.out.println("Input:  " + digits3);
        System.out.println("Output: " + sol.letterCombinations(digits3));
        System.out.println("Expected: [a, b, c]\n");
    }
}

class Solution {
    // 數字到字母的對應表
    private static final String[] MAP = new String[10];
    static {
        MAP['2' - '0'] = "abc";
        MAP['3' - '0'] = "def";
        MAP['4' - '0'] = "ghi";
        MAP['5' - '0'] = "jkl";
        MAP['6' - '0'] = "mno";
        MAP['7' - '0'] = "pqrs";
        MAP['8' - '0'] = "tuv";
        MAP['9' - '0'] = "wxyz";
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        // 如果輸入為空，直接回傳空陣列
        if (digits == null || digits.length() == 0) return ans;
        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    // 回溯法產生所有組合
    private void backtrack(String digits, int idx, StringBuilder cur, List<String> ans) {
        // 當長度等於輸入長度，表示找到一個完整組合
        if (idx == digits.length()) {
            ans.add(cur.toString());
            return;
        }
        // 找出目前數字對應的字母集合
        String letters = MAP[digits.charAt(idx) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            cur.append(letters.charAt(i));         // 選擇一個字母
            backtrack(digits, idx + 1, cur, ans);  // 遞迴處理下一位數字
            cur.deleteCharAt(cur.length() - 1);    // 回溯，撤銷選擇
        }
    }
}

/*
解題思路：
1. 建立一個數字到字母的映射表，例如 '2' → "abc"，'3' → "def"。
2. 使用回溯法 (Backtracking)：
   - 每處理一個數字，依序嘗試對應的字母。
   - 當組合長度等於輸入字串長度時，加入結果集。
3. 若輸入為空字串，直接回傳 []。
4. 時間複雜度：約 O(3^n * 4^m)，其中 n 是對應三個字母的數字個數，m 是對應四個字母的數字個數。
5. 空間複雜度：O(k)，其中 k 為輸入字串長度（遞迴深度）。
*/
