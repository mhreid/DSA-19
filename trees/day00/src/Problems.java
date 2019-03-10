import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> out = new BinarySearchTree();
        for(int value: values){
            out.add(value);
            if(Math.log(height(out.root)) / Math.log(2) > out.size()){

            }
        }
        return new BinarySearchTree<>();
    }
    private static int height(TreeNode n){
        if(n == null){
            return -1;
        }
        return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {

        return swap(n1,n2);
    }
    public static boolean swap(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null){
            return true;
        }
        if(n1.isLeaf() && n2.isLeaf() && n1.equals(n2)){
            return true;
        }
        if(n1.leftChild.equals(n2.leftChild) && n1.rightChild.equals(n2.rightChild)){
            return swap(n1.leftChild, n2.leftChild) && swap(n1.rightChild, n2.rightChild);
        } else if (n1.leftChild.equals(n2.rightChild) && n1.rightChild.equals(n2.leftChild)){
            return swap(n1.leftChild, n2.rightChild) && swap(n1.rightChild, n2.leftChild);
        } else {
            return false;
        }
    }
}
