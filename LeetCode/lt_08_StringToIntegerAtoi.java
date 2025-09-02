public class lt_08_StringToIntegerAtoi {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "42";
        String s2 = "   -042";
        String s3 = "1337c0d3";
        String s4 = "0-1";
        String s5 = "words and 987";
        String s6 = "-91283472332";  // 小於 INT_MIN，應回傳 INT_MIN
        String s7 = "91283472332";   // 大於 INT_MAX，應回傳 INT_MAX

        System.out.println("\"" + s1 + "\"  -> " + sol.myAtoi(s1)); // 42
        System.out.println("\"" + s2 + "\"  -> " + sol.myAtoi(s2)); // -42
        System.out.println("\"" + s3 + "\"  -> " + sol.myAtoi(s3)); // 1337
        System.out.println("\"" + s4 + "\"   -> " + sol.myAtoi(s4)); // 0
        System.out.println("\"" + s5 + "\" -> " + sol.myAtoi(s5)); // 0
        System.out.println("\"" + s6 + "\" -> " + sol.myAtoi(s6)); // -2147483648
        System.out.println("\"" + s7 + "\"  -> " + sol.myAtoi(s7)); // 2147483647
    }
}

class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        // 跳過前導空白
        while (i < n && s.charAt(i) == ' ') i++;
        if (i == n) return 0;

        // 讀取符號
        int sign = 1;
        char c = s.charAt(i);
        if (c == '+' || c == '-') {
            sign = (c == '-') ? -1 : 1;
            i++;
        }

        // 讀數字並處理溢位
        int res = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') break;
            int d = ch - '0';

            if (res > 214748364 || (res == 214748364 && d > (sign == 1 ? 7 : 8))) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + d;
            i++;
        }
        return sign * res;
    }
}
