import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int number) {
        BitSet colorBitset = intToBitset(number);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }




    // return the list of rotations needed to solve a rubik's cube

    public int cost(){
        //check each face for number of colors present
        int cost = 0;
        for(int f = 0; f < 6; f++){
            //iterate through all of the faces
            int[] colors = new int[6];
            for(int b = 0; b < 4; b++){
                //iterate through all of the blocks
                colors[getColor(f * 4 + b)]++;
            }
            //System.out.println(Arrays.toString(colors));
            for(int i = 0; i < 6; i++){
                if(colors[i] > 0) cost++;
            }
        }
        return cost;
    }


    public int cost2(){
        //check each face for number of colors present
        //go through each and see how far it is from it's face???
        //not orientation agnostic though
        int cost = 0;
        for(int f = 0; f < 6; f++){
            //iterate through all of the faces
            for(int b = 0; b < 4; b++){
                int color = getColor(f * 4 + b);

                if(Math.abs(color - f) == 3){
                    cost++;
                }
                if(Math.abs(color - f) > 0){
                    cost++;
                }

            }
        }
        return cost;
    }

    public int cost3(){
        //trying manhattan dist of each cubie
        //color should always be index//6
        //cubies are  (0,16,21), (1,9,17), (2,5,8), ( 3, 4, 20), (12,22,19), (13,10,18), (14,6,11), (15,7,23)
        int[][] cubies = {{0,16,21}, {1,9,17}, {2,5,8}, {3,4,20}, {12,22,19}, {13,10,18},{14,6,11}, {15,7,23}};
        //or iterate through x and y
        //or see how many sides in common
        //if 2 then cost is one
        //if 1 then cost is two
        //if 3 then cost is zero
        //if zero then cost is 3

        int cost = 0;
        for(int[] cubie: cubies){
            int[] colors = new int[3];
                    for(int i = 0; i < 3; i++){
                        colors[i] = getColor(cubie[i]);
                    }
                    //this counts how many of the colors are present
                    int count = 0;
                    for(int c: cubie){
                        if(contains(colors, Math.floorDiv(c,6))) count++;
                    }
                    cost += 3 - count;

        }
        return cost / 4;
    }


    public static boolean contains(int[] array, int v){
        for(int i: array){
            if(i == v) return true;
        }
        return false;
    }


    private class State {
        public RubiksCube r;
        public ArrayList<Character> moves;
        public int cost;

        public State(RubiksCube r, ArrayList<Character> moves){
            this.r = r;
            this.moves = moves;
            this.cost = this.moves.size() + r.cost3();
        }

        public boolean equals(State s) {
            return s.r.equals(r);
        }
        public boolean equals(Object s) {
            return s.equals(r);
        }

        private State[] neighbors(){
            //not tested yet
            State[] n = new State[6];
            char[] turns = {'u', 'U', 'r', 'R', 'f', 'F'};
            for(int i = 0; i < 6; i++){
                ArrayList<Character> moves = (ArrayList) this.moves.clone();
                moves.add(turns[i]);
                n[i] = new State(this.r.rotate(turns[i]), moves);
            }
            return n;
        }

    }


    public List<Character> solve() {

        State c = new State(this, new ArrayList<>());

        PriorityQueue<State> pq = new PriorityQueue<>((State a, State b) -> a.cost - b.cost);
        pq.add(c);
        //Checks to see if cube has existed before and how many moves it took to get there
        HashMap<RubiksCube, Integer> v = new HashMap<>();
        v.put(this, 0);
        while(!pq.isEmpty()){
            c = pq.poll();
            if(c.r.isSolved()){
                System.out.println(c.moves);
                return c.moves;
            }
            for(State n: c.neighbors()){
                if(!v.containsKey(n.r) || n.moves.size() < v.get(n.r)){
                    v.put(n.r, n.moves.size());
                    pq.add(new State(n.r, n.moves));
                }
            }

        }

        return new ArrayList<>();
    }

}