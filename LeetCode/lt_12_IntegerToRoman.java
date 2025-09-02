// 題目：Integer to Roman
// 給定一個整數 num (1 <= num <= 3999)，將其轉換為羅馬數字並回傳。

public class lt_12_IntegerToRoman {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int n1 = 3749;
        String r1 = sol.intToRoman(n1);
        System.out.println("Example 1:");
        System.out.println();
        System.out.println("Input: num = " + n1);
        System.out.println("Output: \"" + r1 + "\"");
        System.out.println("Explanation:");
        System.out.println("3000 = MMM  (1000 + 1000 + 1000)");
        System.out.println("700  = DCC  (500 + 100 + 100)");
        System.out.println("40   = XL   (50 - 10)");
        System.out.println("9    = IX   (10 - 1)");
        System.out.println();

        // Example 2
        int n2 = 58;
        String r2 = sol.intToRoman(n2);
        System.out.println("Example 2:");
        System.out.println();
        System.out.println("Input: num = " + n2);
        System.out.println("Output: \"" + r2 + "\"");
        System.out.println("Explanation:");
        System.out.println("50 = L");
        System.out.println("8  = VIII (5 + 1 + 1 + 1)");
        System.out.println();

        // Example 3
        int n3 = 1994;
        String r3 = sol.intToRoman(n3);
        System.out.println("Example 3:");
        System.out.println();
        System.out.println("Input: num = " + n3);
        System.out.println("Output: \"" + r3 + "\"");
        System.out.println("Explanation:");
        System.out.println("1000 = M");
        System.out.println("900  = CM (1000 - 100)");
        System.out.println("90   = XC (100 - 10)");
        System.out.println("4    = IV (5 - 1)");
    }
}

class Solution {
    public String intToRoman(int num) {
        // 對照表：由大到小排列，包含「基本符號」與「減法表示」
        int[] vals    = {1000, 900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,   1};
        String[] syms = {"M",  "CM","D", "CD","C", "XC","L", "XL","X", "IX","V", "IV","I"};

        StringBuilder sb = new StringBuilder();
        // 從最大值開始，能減就減，並加上對應符號
        for (int i = 0; i < vals.length && num > 0; i++) {
            while (num >= vals[i]) {
                num -= vals[i];
                sb.append(syms[i]);
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字符號：
   - I=1, V=5, X=10, L=50, C=100, D=500, M=1000
   - 減法規則：
     4=IV, 9=IX, 40=XL, 90=XC, 400=CD, 900=CM
   - 其他數字使用加法組合，如 3=III, 8=VIII, 30=XXX。

2. 方法：貪婪法
   - 建立數值與符號對照表，依「由大到小」排序。
   - 從最大的數值開始，能減就減，並把對應符號拼接到答案字串。
   - 直到 num 減完為止。

3. 範例：
   num=1994
   - 減 1000 → "M"，剩 994
   - 減 900  → "CM"，剩 94
   - 減 90   → "XC"，剩 4
   - 減 4    → "IV"，完成 → "MCMXCIV"

4. 複雜度：
   - 對照表固定長度 13，最多跑幾十次迴圈。
   - 時間複雜度 O(1)，空間複雜度 O(1)。
*/
