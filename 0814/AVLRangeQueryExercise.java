import java.util.*;

public class AVLRangeQueryExercise {

    private static class Node {
        int key, height = 1;
        Node left, right;
        Node(int k){ key = k; }
    }

    private Node root;

    public void insert(int key){ root = insert(root, key); }

    public List<Integer> rangeQuery(int min, int max){
        List<Integer> res = new ArrayList<>();
        rangeDFS(root, min, max, res);
        return res;
    }

    private static int h(Node n){ return n==null?0:n.height; }
    private static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }
    private static int bf(Node n){ return n==null?0:h(n.left)-h(n.right); }

    private static Node rotL(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        upd(x); upd(y);
        return y;
    }

    private static Node rotR(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        upd(y); upd(x);
        return x;
    }

    private Node balance(Node n){
        upd(n);
        int b = bf(n);
        if(b > 1){
            if(bf(n.left) < 0) n.left = rotL(n.left);
            return rotR(n);
        }
        if(b < -1){
            if(bf(n.right) > 0) n.right = rotR(n.right);
            return rotL(n);
        }
        return n;
    }

    private Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key < n.key) n.left = insert(n.left, key);
        else if(key > n.key) n.right = insert(n.right, key);
        else return n;
        return balance(n);
    }

    private void rangeDFS(Node n, int min, int max, List<Integer> out){
        if(n==null) return;
        if(n.key > min) rangeDFS(n.left, min, max, out);     
        if(min <= n.key && n.key <= max) out.add(n.key);     
        if(n.key < max) rangeDFS(n.right, min, max, out);     
    }

    // Demo
    public static void main(String[] args){
        AVLRangeQueryExercise t = new AVLRangeQueryExercise();
        for(int x: new int[]{15,10,20,8,12,17,25,6,11,13}) t.insert(x);
        System.out.println(t.rangeQuery(10, 17)); 
    }
}
