import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        LinkedList<Integer> unused = new LinkedList<Integer>(A);
        LinkedList<Integer> current = new LinkedList<>();
        permutationsHelper(current, unused, permutations);
        for(List<Integer> s: permutations){
            System.out.println(s.toString());
        }
        return permutations;
    }

    private static void permutationsHelper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations){
        if(unused.size() == 0){

            permutations.add(new LinkedList<>(current));
            System.out.println(current.toString());
        } else {
            for(int c: unused){
                LinkedList<Integer> pc = new LinkedList<>(current);
                pc.add(c);
                LinkedList<Integer> pu = new LinkedList<>(unused);
                pu.remove(pu.indexOf(c));
                permutationsHelper(pc, pu, permutations);
                pc.remove(pc.indexOf(c));
                pu.add(c);
            }
        }

    }

}
