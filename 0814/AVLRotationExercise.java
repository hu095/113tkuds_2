public class AVLRotationExercise {

    static class Node {
        int key, height = 1;
        Node left, right;
        Node(int k){ key = k; }
    }

    static int h(Node n){ return n==null?0:n.height; }
    static void upd(Node n){ if(n!=null) n.height = 1 + Math.max(h(n.left), h(n.right)); }

    private static Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        upd(y); upd(x);
        return x;
    }

    private static Node rotateLeft(Node x){
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        upd(x); upd(y);
        return y;
    }

    private static Node rotateLeftRight(Node z){
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    private static Node rotateRightLeft(Node z){
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    static Node insertNoBalance(Node node, int key){
        if(node==null) return new Node(key);
        if(key < node.key) node.left = insertNoBalance(node.left, key);
        else if(key > node.key) node.right = insertNoBalance(node.right, key);
        upd(node);
        return node;
    }

    static void inOrder(Node n){
        if(n==null) return;
        inOrder(n.left);
        System.out.print(n.key + " ");
        inOrder(n.right);
    }

    public static void main(String[] args){
        Node rootLL = null;
        for(int x: new int[]{30,20,10}) rootLL = insertNoBalance(rootLL, x);
        rootLL = rotateRight(rootLL);
        System.out.print("LL fixed (inorder): "); inOrder(rootLL); System.out.println();

        Node rootRR = null;
        for(int x: new int[]{10,20,30}) rootRR = insertNoBalance(rootRR, x);
        rootRR = rotateLeft(rootRR);
        System.out.print("RR fixed (inorder): "); inOrder(rootRR); System.out.println();

        Node rootLR = null;
        for(int x: new int[]{30,10,20}) rootLR = insertNoBalance(rootLR, x);
        rootLR = rotateLeftRight(rootLR);
        System.out.print("LR fixed (inorder): "); inOrder(rootLR); System.out.println();

        Node rootRL = null;
        for(int x: new int[]{10,30,20}) rootRL = insertNoBalance(rootRL, x);
        rootRL = rotateRightLeft(rootRL);
        System.out.print("RL fixed (inorder): "); inOrder(rootRL); System.out.println();
    }
}
