public class lt_05_LongestPalindromicSubstring {
    public static void main(String[] args) {
        SolutionExpand expandSol = new SolutionExpand();
        SolutionManacher manacherSol = new SolutionManacher();

        String s1 = "babad";
        String s2 = "cbbd";

        System.out.println("=== 中心擴展法 Expand Around Center ===");
        System.out.println("Input: " + s1 + " -> " + expandSol.longestPalindrome(s1));
        System.out.println("Input: " + s2 + " -> " + expandSol.longestPalindrome(s2));

        System.out.println("\n=== Manacher 演算法 ===");
        System.out.println("Input: " + s1 + " -> " + manacherSol.longestPalindrome(s1));
        System.out.println("Input: " + s2 + " -> " + manacherSol.longestPalindrome(s2));
    }
}

// 版本 A：中心擴展法 O(n^2)
class SolutionExpand {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);     // 奇數中心
            int len2 = expand(s, i, i + 1); // 偶數中心
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                int half = (len - 1) / 2;
                start = i - half;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int L, int R) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--; R++;
        }
        return R - L - 1;
    }
}

// 版本 B：Manacher 演算法 O(n)
class SolutionManacher {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        // 預處理：^#a#b#a#$ （^/$ 為邊界哨兵）
        char[] t = new char[s.length() * 2 + 3];
        t[0] = '^';
        int idx = 1;
        for (int i = 0; i < s.length(); i++) {
            t[idx++] = '#';
            t[idx++] = s.charAt(i);
        }
        t[idx++] = '#';
        t[idx] = '$';

        int n = t.length;
        int[] p = new int[n]; // p[i] 代表回文半徑
        int center = 0, right = 0;
        int bestCenter = 0, bestLen = 0;

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            if (i < right) p[i] = Math.min(right - i, p[mirror]);

            while (t[i + p[i] + 1] == t[i - p[i] - 1]) p[i]++;

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            if (p[i] > bestLen) {
                bestLen = p[i];
                bestCenter = i;
            }
        }

        int start = (bestCenter - bestLen) / 2; // 去掉分隔符後的起點
        return s.substring(start, start + bestLen);
    }
}
