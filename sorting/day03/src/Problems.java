import java.util.Arrays;
import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        int[] counts = new int[201];
        for(int i: A){
            counts[i + 100]++;
        }
        int index = 0;
        for(int i = 0; i < 201; i++){
            for(int j = 0; j < counts[i]; j++){
                A[index] = i - 100;
                index++;
            }
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        n = A[0].length() - n - 1;
        int max = A[0].charAt(n) + 1;
        for(String s: A){
            if(s.charAt(n) > max){
                max = s.charAt(n);
            }
        }
        LinkedList<String>[] L = new LinkedList[max + 1];
        for (int i = 0; i < max + 1; i++)
            L[i] = new LinkedList<>();
        String[] count = new String[max + 1];
        for(String s: A){
            L[s.charAt(n)].add(s);
        }
        int j = 0;
        for (LinkedList<String> list : L) {
            for(String entry: list){
                A[j] = entry;
                j++;

            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i < S[0].length(); i++){
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
