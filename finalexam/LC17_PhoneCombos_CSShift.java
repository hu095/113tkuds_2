import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LC17_PhoneCombos_CSShift {

    private static final String[] MAP = new String[10];
    static {
        MAP[2] = "abc";
        MAP[3] = "def";
        MAP[4] = "ghi";
        MAP[5] = "jkl";
        MAP[6] = "mno";
        MAP[7] = "pqrs";
        MAP[8] = "tuv";
        MAP[9] = "wxyz";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String digits = br.readLine();
        if (digits == null) digits = "";
        digits = digits.trim();

        // 空字串：不輸出（依題意）
        if (digits.isEmpty()) return;

        List<String> ans = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), ans);

        StringBuilder out = new StringBuilder();
        for (String s : ans) out.append(s).append('\n');
        System.out.print(out.toString());
    }

    private static void backtrack(String digits, int idx, StringBuilder cur, List<String> out) {
        if (idx == digits.length()) {
            out.add(cur.toString());
            return;
        }
        char d = digits.charAt(idx);
        if (d < '2' || d > '9') {
            // 題目保證僅含 2–9；若有例外可直接略過或丟錯
            return;
        }
        String letters = MAP[d - '0'];
        for (int i = 0; i < letters.length(); i++) {
            cur.append(letters.charAt(i));
            backtrack(digits, idx + 1, cur, out);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
