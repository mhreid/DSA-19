import java.lang.*;
import java.util.Arrays;
import java.util.HashMap;


public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        HashMap<Character, Integer> order = new HashMap<>();
        for (int i = 0; i < locks.length; i++) {
            order.put(locks[i], i);
        }
        int temp;
        for (int i = 0; i < keys.length * 2; i++) {
            temp = order.get(keys[i % keys.length]);
            swap(keys, temp, i % keys.length);
        }
        boolean repeat = true;
        while(repeat) {
            repeat = false;
            for (int i = 0; i < keys.length - 1; i++) {
                if (keys[i] > locks[i + 1]) {
                    repeat = true;
                    swap(keys, i, i + 1);
                    swap(locks, i, i+1);
                }
            }
        }

        result[0] = locks;
        result[1] = keys;
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        return result;
    }
}


