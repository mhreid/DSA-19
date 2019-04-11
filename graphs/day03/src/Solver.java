/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**F
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.cost = board.manhattan() + moves;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        //totally not sure if this is it
        return state.prev;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here
        State c = new State(initial, 0, null);
        solutionState = c;

        //solutionState = new State(initial,0 , null);
        PriorityQueue<State> pq = new PriorityQueue<>((State a, State b) -> a.cost - b.cost);
        pq.add(c);
        HashMap<Board, Integer> v = new HashMap<>();
        v.put(initial, 0);
        boolean solvable = isSolvable();
        while(!pq.isEmpty() && solvable){
            c = pq.poll();
            if(c.board.isGoal()){
                minMoves = c.moves;
                solutionState = c;
                solved = true;
                return;
            }
            for(Board n: c.board.neighbors()){
                if(!v.containsKey(n) || c.moves + 1 < v.get(n)) {

                    v.put(n, c.moves + 1);
                    pq.add(new State(n, c.moves + 1, c));
                }
            }

        }

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solutionState.board.solvable();

    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
