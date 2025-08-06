import java.util.HashSet;

public class AdvancedStringRecursion {

    // 1. 遞迴產生字串的所有排列組合
    public static void generatePermutations(String str) {
        generatePermutationsHelper("", str);
    }

    private static void generatePermutationsHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                generatePermutationsHelper(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1));
            }
        }
    }

    // 2. 遞迴實作字串匹配演算法 (類似 indexOf 功能)
    public static int recursiveStringMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0);
    }

    private static int matchHelper(String text, String pattern, int index) {
        if (index > text.length() - pattern.length()) {
            return -1;
        }

        if (text.startsWith(pattern, index)) {
            return index;
        }

        return matchHelper(text, pattern, index + 1);
    }

    // 3. 遞迴移除字串中的重複字符（保留第一次出現）
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, 0, new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, int index, HashSet<Character> seen) {
        if (index >= str.length()) {
            return "";
        }

        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDuplicatesHelper(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDuplicatesHelper(str, index + 1, seen);
        }
    }

    // 4. 遞迴計算字串的所有子字串組合
    public static void generateSubstrings(String str) {
        generateSubstringsHelper(str, 0, "");
    }

    private static void generateSubstringsHelper(String str, int index, String current) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                System.out.println(current);
            }
            return;
        }

        // 包含當前字符
        generateSubstringsHelper(str, index + 1, current + str.charAt(index));
        // 不包含當前字符
        generateSubstringsHelper(str, index + 1, current);
    }

    // 測試主程式
    public static void main(String[] args) {
        System.out.println("1. 所有排列組合:");
        generatePermutations("abc");

        System.out.println("\n2. 字串匹配:");
        String text = "hello world";
        String pattern = "world";
        int index = recursiveStringMatch(text, pattern);
        System.out.println("Pattern found at index: " + index);

        System.out.println("\n3. 移除重複字元:");
        String noDup = removeDuplicates("banana");
        System.out.println("結果: " + noDup);

        System.out.println("\n4. 所有子字串組合:");
        generateSubstrings("abc");
    }
}
