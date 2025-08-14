import java.util.*;

/**
 * 單檔可執行的 AVL Tree 實作（含 main）
 * 特色：
 *  - 插入 / 刪除皆自動維持平衡
 *  - 印出中序含平衡因子、以及層級結構
 *  - 內建 isValidAVL() 驗證
 */
public class AVLTree {

    /* ===================== 內部節點類別 ===================== */
    private static class AVLNode {
        int data;
        int height;
        AVLNode left, right;

        AVLNode(int data) {
            this.data = data;
            this.height = 1; // 新節點預設高度 1
        }

        void updateHeight() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            height = 1 + Math.max(lh, rh);
        }

        int getBalance() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            return lh - rh;
        }
    }

    /* ===================== 成員與工具 ===================== */
    private AVLNode root;

    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    /* ===================== 旋轉操作 ===================== */
    private AVLNode rightRotate(AVLNode y) {
        if (y == null || y.left == null) return y;

        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 自底向上更新高度
        y.updateHeight();
        x.updateHeight();

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        if (x == null || x.right == null) return x;

        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 自底向上更新高度
        x.updateHeight();
        y.updateHeight();

        return y;
    }

    /* ===================== 插入 ===================== */
    // 時間複雜度: O(log n), 空間複雜度: O(log n)
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        // 1) 標準 BST 插入
        if (node == null) return new AVLNode(data);

        if (data < node.data) {
            node.left = insertNode(node.left, data);
        } else if (data > node.data) {
            node.right = insertNode(node.right, data);
        } else {
            return node; // 重複值不插入
        }

        // 2) 更新高度
        node.updateHeight();

        // 3) 取得平衡因子
        int balance = node.getBalance();

        // 4) 四種失衡情況修正
        // LL
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /* ===================== 搜尋 ===================== */
    // 時間複雜度: O(log n), 空間複雜度: O(log n)
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }

    /* ===================== 刪除 ===================== */
    // 時間複雜度: O(log n), 空間複雜度: O(log n)
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        // 1) 標準 BST 刪除
        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            // 命中待刪節點
            if (node.left == null || node.right == null) {
                AVLNode child = (node.left != null) ? node.left : node.right;
                // 無子或單子：直接回傳 child 取代當前 node
                return (child == null) ? null : child;
            } else {
                // 兩子：用右子樹最小節點（中序後繼）替換
                AVLNode succ = findMin(node.right);
                node.data = succ.data;
                node.right = deleteNode(node.right, succ.data);
            }
        }

        // 2) 更新高度
        node.updateHeight();

        // 3) 重新平衡
        int balance = node.getBalance();

        // LL
        if (balance > 1 && node.left.getBalance() >= 0) {
            return rightRotate(node);
        }
        // LR
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && node.right.getBalance() <= 0) {
            return leftRotate(node);
        }
        // RL
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /* ===================== 驗證 / 列印 ===================== */
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        int rh = checkAVL(node.right);
        if (lh == -1 || rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    // 中序列印（顯示 data(balance)）
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // 額外：層級列印（看結構用）
    public void printLevelOrder() {
        if (root == null) {
            System.out.println("(empty)");
            return;
        }
        Queue<AVLNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < sz; i++) {
                AVLNode n = q.poll();
                if (n == null) {
                    line.append(" . ");
                    continue;
                }
                line.append(n.data).append("[h=").append(n.height)
                    .append(",b=").append(n.getBalance()).append("]  ");
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            System.out.println(line);
        }
    }

    /* ===================== 測試 main ===================== */
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 1) 插入測試
        int[] toInsert = {30, 20, 40, 10, 25, 35, 50, 5, 15, 27, 45, 60, 26};
        for (int x : toInsert) tree.insert(x);

        System.out.println("=== 插入完成（中序 with balance）===");
        tree.printTree();
        System.out.println("Valid AVL? " + tree.isValidAVL());

        System.out.println("\n=== 層級結構（值[高度,平衡]）===");
        tree.printLevelOrder();

        // 2) 搜尋測試
        int[] queries = {27, 99, 10};
        System.out.println("\n=== 搜尋 ===");
        for (int q : queries) {
            System.out.println("search(" + q + ") -> " + tree.search(q));
        }

        // 3) 刪除測試（涵蓋葉節點、單子、雙子）
        int[] toDelete = {5, 20, 30};
        for (int d : toDelete) {
            System.out.println("\n=== 刪除 " + d + " 後 ===");
            tree.delete(d);
            tree.printTree();
            System.out.println("Valid AVL? " + tree.isValidAVL());
        }
    }
}
