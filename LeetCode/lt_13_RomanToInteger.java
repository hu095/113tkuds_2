import java.util.HashMap;
import java.util.Map;


public class lt_13_RomanToInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String s1 = "III";
        int out1 = sol.romanToInt(s1);
        System.out.println("Example 1:");
        System.out.println();
        System.out.println("Input:  s = \"" + s1 + "\"");
        System.out.println("Output: " + out1);
        System.out.println("Explanation: III = 3.");
        System.out.println();

        // Example 2
        String s2 = "LVIII";
        int out2 = sol.romanToInt(s2);
        System.out.println("Example 2:");
        System.out.println();
        System.out.println("Input:  s = \"" + s2 + "\"");
        System.out.println("Output: " + out2);
        System.out.println("Explanation: L = 50, V = 5, III = 3 → 58.");
        System.out.println();

        // Example 3
        String s3 = "MCMXCIV";
        int out3 = sol.romanToInt(s3);
        System.out.println("Example 3:");
        System.out.println();
        System.out.println("Input:  s = \"" + s3 + "\"");
        System.out.println("Output: " + out3);
        System.out.println("Explanation: M = 1000, CM = 900, XC = 90, IV = 4 → 1994.");
    }
}

class Solution {
    public int romanToInt(String s) {
        // 羅馬數字對照表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        // 遍歷字串，處理減法規則
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            // 如果當前數字比下一個數字小，表示需要做減法
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }
        return total;
    }
}

/*
解題思路：
1. 羅馬數字的基本規則：
   - 符號由大到小排列，依序相加。
   - 遇到「小數字在大數字左邊」時，表示要做減法 (例如 IV=4, IX=9, XL=40, XC=90, CD=400, CM=900)。

2. 解法：
   - 建立一張 Map，存放每個字元對應的數值。
   - 從左到右遍歷字串：
     - 若當前字元小於右邊字元，表示要做減法，total 減去該值。
     - 否則直接加上該值。

3. 範例解析：
   - "MCMXCIV"
     M (1000) → +1000
     C (100) 小於 M (1000) → -100
     M (1000) → +1000
     X (10) 小於 C (100) → -10
     C (100) → +100
     I (1) 小於 V (5) → -1
     V (5) → +5
     總和 = 1994

4. 複雜度：
   - 時間 O(n)，一次遍歷字串即可。
   - 空間 O(1)，因為字母種類固定。
*/
