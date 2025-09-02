import java.util.Arrays;

public class lt_03_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("Example 1: " + sol.lengthOfLongestSubstring("abcabcbb")); // 預期 3
        System.out.println("Example 2: " + sol.lengthOfLongestSubstring("bbbbb"));    // 預期 1
        System.out.println("Example 3: " + sol.lengthOfLongestSubstring("pwwkew"));   // 預期 3
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        Arrays.fill(last, -1);

        int left = 0, ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (last[c] >= left) {
                left = last[c] + 1; // 收縮左邊界
            }

            last[c] = right;
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
