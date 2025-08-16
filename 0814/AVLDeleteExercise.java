
public class AVLDeleteExercise {

    private static class Node {
        int key, height = 1;
        Node left, right;
        Node(int k){ key = k; }
    }

    private Node root;

    public void insert(int key){ root = insert(root, key); }
    public void delete(int key){ root = delete(root, key); }
    public boolean contains(int key){ return contains(root, key); }
    public int height(){ return height(root); }
    public void inOrderPrint(){ inOrder(root); System.out.println(); }

    private static int height(Node n){ return n==null?0:n.height; }
    private static void update(Node n){ if(n!=null) n.height = 1 + Math.max(height(n.left), height(n.right)); }
    private static int bf(Node n){ return n==null?0:height(n.left)-height(n.right); }

    private static Node rotateLeft(Node x){
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        update(x); update(y);
        return y;
    }

    private static Node rotateRight(Node y){
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        update(y); update(x);
        return x;
    }

    private Node balance(Node n){
        update(n);
        int b = bf(n);
        if(b > 1){
            if(bf(n.left) < 0) n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if(b < -1){
            if(bf(n.right) > 0) n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    private Node insert(Node n, int key){
        if(n==null) return new Node(key);
        if(key < n.key) n.left = insert(n.left, key);
        else if(key > n.key) n.right = insert(n.right, key);
        else return n; // ignore dup
        return balance(n);
    }

    private Node delete(Node n, int key){
        if(n==null) return null;
        if(key < n.key) n.left = delete(n.left, key);
        else if(key > n.key) n.right = delete(n.right, key);
        else {
            // found
            if(n.left == null || n.right == null){
                n = (n.left != null) ? n.left : n.right; 
            } else {
                Node succ = minNode(n.right);
                n.key = succ.key;
                n.right = delete(n.right, succ.key);
            }
        }
        if(n == null) return null;
        return balance(n);
    }

    private Node minNode(Node n){
        while(n.left != null) n = n.left;
        return n;
    }

    private boolean contains(Node n, int key){
        if(n==null) return false;
        if(key == n.key) return true;
        return key < n.key ? contains(n.left, key) : contains(n.right, key);
    }

    private void inOrder(Node n){
        if(n==null) return;
        inOrder(n.left);
        System.out.print(n.key + " ");
        inOrder(n.right);
    }

    public static void main(String[] args){
        AVLDeleteExercise t = new AVLDeleteExercise();
        int[] arr = { 9,5,10,0,6,11,-1,1,2 };
        for(int x: arr) t.insert(x);
        System.out.print("Before delete: "); t.inOrderPrint();

        t.delete(10);  
        System.out.print("After delete 10: "); t.inOrderPrint();

        t.delete(9);   
        System.out.print("After delete 9: "); t.inOrderPrint();

        t.delete(-1); 
        System.out.print("After delete -1: "); t.inOrderPrint();

        System.out.println("Height: " + t.height());
    }
}
