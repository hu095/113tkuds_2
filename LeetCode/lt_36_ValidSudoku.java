import java.util.Arrays;

public class lt_36_ValidSudoku {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'9', '8', '.', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'6', '.', '.', '.', '8', '.', '.', '2', '.'},
            {'.', '4', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '.', '8', '.', '.', '.', '7', '9', '.'}
        };
        System.out.println("Example 1:");
        System.out.println("Input:  " + Arrays.deepToString(board1));
        System.out.println("Output: " + sol.isValidSudoku(board1)); // Expected: true

        // 測試案例 2
        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'9', '8', '.', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'6', '.', '.', '.', '8', '.', '.', '2', '.'},
            {'.', '4', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '8', '9'}
        };
        System.out.println("Example 2:");
        System.out.println("Input:  " + Arrays.deepToString(board2));
        System.out.println("Output: " + sol.isValidSudoku(board2)); // Expected: false
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 使用 int 陣列來檢查每一行、每一列和每一個 3x3 小區塊
        int[] rows = new int[9];  // 行的數字標記
        int[] cols = new int[9];  // 列的數字標記
        int[] boxes = new int[9]; // 3x3 小區塊的數字標記

        // 檢查每個填入的數字是否符合 Sudoku 規則
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1'; // 把字符轉換成數字 1-9 對應 0-8
                    int boxIndex = (i / 3) * 3 + (j / 3); // 計算 3x3 小區塊的索引

                    // 檢查當前行、列、3x3 小區塊是否已經有相同的數字
                    if ((rows[i] & (1 << num)) != 0 || (cols[j] & (1 << num)) != 0 || (boxes[boxIndex] & (1 << num)) != 0) {
                        return false; // 如果已經有相同數字，返回 false
                    }

                    // 標記當前行、列和 3x3 小區塊已經使用了這個數字
                    rows[i] |= (1 << num);
                    cols[j] |= (1 << num);
                    boxes[boxIndex] |= (1 << num);
                }
            }
        }
        return true; // 如果整個過程沒有返回 false，則 Sudoku 合法
    }
}

/*
解題思路：
1. 我們需要檢查每一行、每一列和每個 3x3 小區塊中，數字是否有重複。
2. 使用三個 int 陣列來標記每行、每列、每個小區塊是否有出現過某個數字。每個陣列的大小為 9（0-8）對應行、列和小區塊。
3. 當處理到每個數字時，將其轉換為一個二進制數字，並使用位運算檢查該數字是否已經出現過。
4. 如果發現重複的數字，即返回 `false`。如果遍歷完後沒有發現問題，則返回 `true`。

時間複雜度：O(1)，因為我們遍歷每個格子固定次數，最大為 81 次。  
空間複雜度：O(1)，由於我們只使用了 3 個大小為 9 的陣列來標記數字，這是常數空間。
*/

