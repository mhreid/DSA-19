import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int[][] goal;



    private int[][] createGoal(){
    //sets up the goal state with 0 at the upper left
        int[][] goal = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                goal[i][j] = n * i + j + 1;
            }
        }
        goal[n - 1][n - 1] = 0;
        return goal;
    }

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = new int[b.length][b.length];
        for(int r = 0; r < b.length; r++){
            for(int c = 0; c<b.length; c++){
                tiles[r][c] = b[r][c];
            }
        }
        n = size();
        goal = createGoal();
    }

    /*
     * Size of the board (n)
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return tiles.length;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int d = 0;
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                d += dist(r, c);
            }
        }
        return d;
    }

    private int dist(int r, int c){
        int val = tiles[r][c];
        if(val == 0) return 0;
        val--;
        return Math.abs(val / n - r) + Math.abs(val % n - c);

    }
    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        //System.out.println(Arrays.deepToString(goal));
        return (equals(new Board(goal)));
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        int inversions = 0;
        for(int i = 0; i < n * n; i ++){
            for(int j = i + 1; j < n * n; j++){
                if(tiles[i / n][i % n]  > tiles[j /n][j % n] % (n * n) || tiles[i / n][i % n] == 0) inversions++;
            }
        }
        //System.out.println(inversions);
        return (inversions % 2 == 0);
    }



    public int hashCode(){
        /*int m = 2;
        int out = 0;
        for(int[] r: tiles){
            for(int i: r){
                out += i * m;
            }
            m++;
        }
        return out;*/
        return Arrays.deepHashCode(tiles);
    }


    /*
     * Return all neighboring boards in the state tree
     */

    public Iterable<Board> neighbors() {
        // TODO: Your code here
        ArrayList<Board> out = new ArrayList<>();
        int r = 0;
        int c = 0;
        while(tiles[r][c] != 0){
            c++;
            if(c == n){
                c = 0;
                r++;
            }
        }
        //now we have the location of the blank space

        //Board temp = new Board(tiles.clone());

        if(r > 0){
            //there is a top
            tiles[r][c] = tiles[r - 1][c];
            tiles[r - 1][c] = 0;
            //switch top and 0
            //System.out.println(Arrays.deepToString(temp.tiles));
            out.add(new Board(tiles));
            tiles[r - 1][c] = tiles[r][c];
            tiles[r][c] = 0;
        }
        if(r < n - 1){
            //there is a bottom
            //temp = new Board(tiles);
            tiles[r][c] = tiles[r + 1][c];
            tiles[r + 1][c] = 0;
            //switch bottom and 0;
            out.add(new Board(tiles));
            tiles[r + 1][c] = tiles[r][c];
            tiles[r][c] = 0;
        }
        if(c > 0){
            //there is a left
            //temp = new Board(tiles);
            tiles[r][c] = tiles[r][c - 1];
            tiles[r][c - 1] = 0;
            //switch left and 0;
            out.add(new Board(tiles));
            tiles[r][c - 1] = tiles[r][c];
            tiles[r][c] = 0;
        }
        if(c < n - 1){
            //there is a right
            //temp = new Board(tiles);
            tiles[r][c] = tiles[r][c + 1];
            tiles[r][c + 1] = 0;
            //switch right and 0;
            out.add(new Board(tiles));
            tiles[r][c + 1] = tiles[r][c];
            tiles[r][c] = 0;
        }
        for(Board o: out){
            //System.out.println(Arrays.deepToString(o.tiles));
        }
        return out;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
