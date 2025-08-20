import java.io.*;

/*
 * Time Complexity: O(n)，需掃描並檢查字串
 * Space Complexity: O(n) 存清洗後的字元，或 O(1) 若用雙指標原地檢查
 */

public class M06_PalindromeClean {
    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        System.out.println(isPalindrome(sb.toString()) ? "Yes" : "No");
    }
}
