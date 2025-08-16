public class AVLBasicsExercise {

    private static class Node {
        int key;
        int height;
        Node left, right;
        Node(int k) { this.key = k; this.height = 1; }
    }

    private Node root;

    public void insert(int key) { root = insert(root, key); }

    public boolean contains(int key) { return contains(root, key); }

    public int height() { return height(root); }

    public boolean isValidAVL() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) && isBalanced(root).balanced;
    }

    // ===== BST + AVL helpers =====
    private static int height(Node n) { return n == null ? 0 : n.height; }

    private static int balanceFactor(Node n) { return (n == null) ? 0 : height(n.left) - height(n.right); }

    private static void update(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }

    private static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y);
        update(x);
        return x;
    }

    private static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x);
        update(y);
        return y;
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; 
        update(node);
        int bf = balanceFactor(node);

        // LL
        if (bf > 1 && key < node.left.key) return rotateRight(node);
        // RR
        if (bf < -1 && key > node.right.key) return rotateLeft(node);
        // LR
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private boolean contains(Node n, int key) {
        if (n == null) return false;
        if (key == n.key) return true;
        return key < n.key ? contains(n.left, key) : contains(n.right, key);
    }

    private boolean isBST(Node n, int lo, int hi) {
        if (n == null) return true;
        if (n.key <= lo || n.key >= hi) return false;
        return isBST(n.left, lo, n.key) && isBST(n.right, n.key, hi);
    }

    private static class BalanceInfo {
        boolean balanced; int height;
        BalanceInfo(boolean b, int h){balanced=b;height=h;}
    }

    private BalanceInfo isBalanced(Node n) {
        if (n == null) return new BalanceInfo(true, 0);
        BalanceInfo L = isBalanced(n.left);
        BalanceInfo R = isBalanced(n.right);
        boolean ok = L.balanced && R.balanced && Math.abs(L.height - R.height) <= 1;
        return new BalanceInfo(ok, 1 + Math.max(L.height, R.height));
    }

    public static void main(String[] args) {
        AVLBasicsExercise tree = new AVLBasicsExercise();
        int[] nums = { 10, 20, 30, 40, 50, 25 };
        for (int x : nums) tree.insert(x);

        System.out.println("Height: " + tree.height());
        System.out.println("Contains 25? " + tree.contains(25));
        System.out.println("Valid AVL? " + tree.isValidAVL());
    }
}
