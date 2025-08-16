
public class AVLBasicExercise {

    /* ====== 簡化版 AVL 節點 ====== */
    static class Node {
        int key;
        int height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    private Node root;

    /* ====== 基本工具 ====== */
    private int h(Node x) { return x == null ? 0 : x.height; }
    private int balance(Node x) { return x == null ? 0 : h(x.left) - h(x.right); }
    private void upd(Node x) { x.height = Math.max(h(x.left), h(x.right)) + 1; }

    /* ====== 單/雙旋 (簡化) ====== */
    private Node rightRotate(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        upd(y); upd(x);
        return x;
    }
    private Node leftRotate(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        upd(x); upd(y);
        return y;
    }

    /* ====== 插入 ====== */
    public void insert(int key) { root = insert(root, key); }
    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node; // 不插入重複

        upd(node);
        int bf = balance(node);

        // LL
        if (bf > 1 && key < node.left.key) return rightRotate(node);
        // RR
        if (bf < -1 && key > node.right.key) return leftRotate(node);
        // LR
        if (bf > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (bf < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /* ====== 搜尋 ====== */
    public boolean search(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) return true;
            cur = key < cur.key ? cur.left : cur.right;
        }
        return false;
    }

    /* ====== 高度 ====== */
    public int height() { return h(root); }

    /* ====== 驗證是否為 AVL ====== */
    public boolean isValidAVL() { return check(root) != -1; }
    private int check(Node x) {
        if (x == null) return 0;
        int lh = check(x.left);   if (lh == -1) return -1;
        int rh = check(x.right);  if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    /* ====== 簡易 Demo ====== */
    public static void main(String[] args) {
        AVLBasicExercise t = new AVLBasicExercise();
        int[] a = {10,20,30,40,50,25};
        for (int v : a) t.insert(v);
        System.out.println("height = " + t.height());
        System.out.println("search 25 = " + t.search(25));
        System.out.println("isValidAVL = " + t.isValidAVL());
    }
}
