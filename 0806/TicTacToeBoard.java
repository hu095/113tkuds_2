public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initializeBoard();
        makeMove(0, 0, 'X');
        makeMove(1, 1, 'O');
        makeMove(0, 1, 'X');
        makeMove(2, 2, 'O');
        makeMove(0, 2, 'X');
        printBoard();

        if (checkWin('X')) System.out.println("X 獲勝！");
        else if (checkWin('O')) System.out.println("O 獲勝！");
        else if (isBoardFull()) System.out.println("平手！");
        else System.out.println("遊戲尚未結束");
    }

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public static boolean makeMove(int row, int col, char player) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        System.out.println("無效位置！");
        return false;
    }

    public static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||  // 行
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))    // 列
                return true;

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||   // 對角線
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);     // 反對角
    }

    public static boolean isBoardFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public static void printBoard() {
        for (char[] row : board) {
            for (char cell : row)
                System.out.print("|" + cell);
            System.out.println("|");
        }
    }
}

