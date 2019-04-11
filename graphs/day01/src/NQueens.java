import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }
    public static boolean checkAll(char[][] board, int r, int c){
        if(checkDiagonal(board, r, c)) return true;
        for(int i = 0; i < Math.max(r, c); i++){
            if(board[r][i] == 'Q') return true;
            if(board[i][c] == 'Q') return true;
        }
        //optimize this later to not check unvisited
        return false;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] r : board){
            Arrays.fill(r, '.');
        }
        helper2(board, 0, 0, n, answers);
        return answers;
    }


    public static void helper2(char[][] board, int r, int c, int rm, List<char[][]> answers){
        if(c >= board.length){
            c = 0;
            r++;
        }
        if(rm == 0){
            answers.add(copyOf(board));
        }
        else if(r >= board.length){
            ;
        } else {
            if(!checkAll(board, r, c)){
                board[r][c] = 'Q';
                helper2(board, r + 1, 0, rm - 1, answers);
                board[r][c] = '.';
            }
            helper2(board, r, c + 1, rm, answers);
        }
    }

}
