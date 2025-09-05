
/**
 * 題目：LeetCode 37. Sudoku Solver
 *
 * 說明：
 *   將數獨盤面中所有 '.' 填滿，使得解答唯一且滿足以下條件：
 *   1. 每一列(row)數字 1~9 各出現一次
 *   2. 每一行(col)數字 1~9 各出現一次
 *   3. 每一個 3x3 區塊內數字 1~9 各出現一次
 *
 * 作法：
 *   - 使用回溯 (Backtracking) + 合法性檢查 (isValid)
 *   - 對每個空格嘗試填 1~9，若合法就繼續遞迴，否則回退
 *   - 當所有空格都填滿時即為答案
 *
 */
public class lt_37_SudokuSolver {

    // ===== LeetCode 可提交版本 =====
    static class Solution {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        // 回溯主函式
        private boolean solve(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') { // 找到空格
                        // 嘗試填 1~9
                        for (char num = '1'; num <= '9'; num++) {
                            if (isValid(board, i, j, num)) {
                                board[i][j] = num; // 填入
                                if (solve(board)) return true; // 遞迴成功就返回
                                board[i][j] = '.'; // 否則回退
                            }
                        }
                        return false; // 這格 1~9 都不合法 → 回溯
                    }
                }
            }
            return true; // 全部填滿即完成
        }

        // 檢查填入 num 是否合法
        private boolean isValid(char[][] board, int row, int col, char num) {
            // 檢查列
            for (int i = 0; i < 9; i++)
                if (board[row][i] == num) return false;

            // 檢查行
            for (int i = 0; i < 9; i++)
                if (board[i][col] == num) return false;

            // 檢查 3x3 區塊
            int br = (row / 3) * 3, bc = (col / 3) * 3;
            for (int i = br; i < br + 3; i++)
                for (int j = bc; j < bc + 3; j++)
                    if (board[i][j] == num) return false;

            return true; // 通過檢查 → 合法
        }
    }

    // ===== 測試用 Main (VS Code 版本) =====
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1 (題目測資)
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'9','8','.','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','8','.','.','2','.'},
            {'.','4','.','1','9','5','.','.','.'},
            {'.','.','8','.','.','.','7','9','.'}
        };

        System.out.println("Example 1:\n");
        System.out.println("Input:");
        printBoard(board);

        sol.solveSudoku(board);

        System.out.println("\nOutput:");
        printBoard(board);

        System.out.println("\nExplanation:");
        System.out.println("已將所有空格依規則填上，唯一解如上。");
    }

    // 格式化列印數獨盤面
    private static void printBoard(char[][] b) {
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0) System.out.print("| ");
                System.out.print(b[r][c] + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }
}

/**
 * 解題思路：
 *   1. 遍歷盤面，遇到空格就嘗試填入 1~9
 *   2. 每次填數前檢查「行、列、3x3 區塊」是否已存在相同數字
 *   3. 若合法則遞迴處理下一格，失敗則回溯
 *   4. 直到整個盤面填滿，題目保證唯一解
 *
 * 時間複雜度：
 *   - 最壞情況接近 9^(空格數)，但實際因約束大幅減枝
 *   - 可在合理時間內完成
 */
