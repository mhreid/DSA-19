import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            boolean add = true;
            for (int j = 1; j <= k; j++) {
                if ( i + j >= A.length || A[i] > A[i + j] ) {
                    add = false;
                    break;
                }
            }
            if(add){
                l.add(A[i]);
            } else {
                k--;
            }
        }
        return l;
    }


    public static boolean isPalindrome(Node n) {
        int size = 0;
        Node curr = n;
        while(curr != null){
            size++;
            curr = curr.next;
        }

        Node prev = null;
        Node ahead = n;

        for(int i = 0; i < size/2; i++){
            curr = ahead;
            ahead = curr.next;
            curr.next = prev;
            prev = curr;

        }

        Node curr2 = ahead;
        if(size % 2 == 1){
            curr2 = curr2.next;
        }

        for(int i = 0; i < size/2; i++){
            if(curr.val != curr2.val){
                return false;
            }
            curr = curr.next;
            curr2 = curr2.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        List<Character> stack = new LinkedList<>();
        String out = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '*' || s.charAt(i) ==  '/' || s.charAt(i) == '+' || s.charAt(i) == '-'){
                stack.add(s.charAt(i));
            } else if (s.charAt(i) == ')'){
                out += (((LinkedList<Character>) stack).removeLast());
                out += ' ';

            } else if (s.charAt(i) != '(' && s.charAt(i) != ' '){
                out += s.charAt(i);
                out += ' ';
            }
        }
        return out.substring(0, out.length() - 1);
    }

}
