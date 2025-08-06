import java.util.Scanner;

public class TicTacToeBoard {
    private int[][] board;
    private int currentPlayer;

    public TicTacToeBoard() {
        board = new int[3][3]; // 0 表示空格, 1 表示玩家1, 2 表示玩家2
        currentPlayer = 1;
    }

    // 顯示棋盤
    public void printBoard() {
        System.out.println("目前棋盤：");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char mark = ' ';
                if (board[i][j] == 1) mark = 'O';
                else if (board[i][j] == 2) mark = 'X';
                System.out.print(" " + mark + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }
    }

    // 根據 1~9 放置棋子
    public boolean placeMark(int position) {
        if (position < 1 || position > 9) {
            System.out.println("無效位置！請輸入 1 到 9。");
            return false;
        }

        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (board[row][col] != 0) {
            System.out.println("這個位置已被佔用！");
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    // 切換玩家
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    // 檢查是否有人獲勝
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) return true;

            if (board[0][i] != 0 &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]) return true;
        }

        if (board[0][0] != 0 &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) return true;

        if (board[0][2] != 0 &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) return true;

        return false;
    }

    // 檢查是否平手
    public boolean isDraw() {
        for (int[] row : board)
            for (int cell : row)
                if (cell == 0) return false;
        return true;
    }

    // 主程式
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("井字遊戲開始！玩家1是 O，玩家2是 X。");
        System.out.println("請使用數字 1 到 9 表示位置：");
        System.out.println(" 1 | 2 | 3\n---+---+---\n 4 | 5 | 6\n---+---+---\n 7 | 8 | 9");

        while (true) {
            game.printBoard();
            System.out.println("輪到玩家 " + game.currentPlayer + "，請輸入位置（1-9）：");
            int pos = scanner.nextInt();

            if (game.placeMark(pos)) {
                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println("玩家 " + game.currentPlayer + " 獲勝！");
                    break;
                } else if (game.isDraw()) {
                    game.printBoard();
                    System.out.println("平手！");
                    break;
                }
                game.switchPlayer();
            }
        }
        scanner.close();
    }
}
