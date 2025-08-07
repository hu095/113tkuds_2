import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int data;
        TreeNode left, right;
        int size;  // 子樹節點數（包含自己）

        TreeNode(int data) {
            this.data = data;
            this.size = 1;
        }
    }

    static class BST {
        TreeNode root;

        // 插入節點（同時更新 size）
        public void insert(int val) {
            root = insertRec(root, val);
        }

        private TreeNode insertRec(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);

            if (val < node.data) {
                node.left = insertRec(node.left, val);
            } else if (val > node.data) {
                node.right = insertRec(node.right, val);
            }
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        private int getSize(TreeNode node) {
            return node == null ? 0 : node.size;
        }

        // 查詢第 k 小元素
        public int kthSmallest(int k) {
            return kthSmallestRec(root, k);
        }

        private int kthSmallestRec(TreeNode node, int k) {
            if (node == null) throw new IllegalArgumentException("k 超出範圍");

            int leftSize = getSize(node.left);

            if (k <= leftSize) {
                return kthSmallestRec(node.left, k);
            } else if (k == leftSize + 1) {
                return node.data;
            } else {
                return kthSmallestRec(node.right, k - leftSize - 1);
            }
        }

        // 查詢第 k 大元素
        public int kthLargest(int k) {
            int total = getSize(root);
            return kthSmallest(total - k + 1);
        }

        // 查詢第 k 小到第 j 小之間的所有元素
        public List<Integer> rangeKtoJ(int k, int j) {
            List<Integer> result = new ArrayList<>();
            inOrderRange(root, result, k, j, new int[]{0});
            return result;
        }

        private void inOrderRange(TreeNode node, List<Integer> result, int k, int j, int[] counter) {
            if (node == null || counter[0] >= j) return;

            inOrderRange(node.left, result, k, j, counter);

            counter[0]++;
            if (counter[0] >= k && counter[0] <= j) {
                result.add(node.data);
            }

            inOrderRange(node.right, result, k, j, counter);
        }

        // 刪除節點（並維護 size）
        public void delete(int val) {
            root = deleteRec(root, val);
        }

        private TreeNode deleteRec(TreeNode node, int val) {
            if (node == null) return null;

            if (val < node.data) {
                node.left = deleteRec(node.left, val);
            } else if (val > node.data) {
                node.right = deleteRec(node.right, val);
            } else {
                // 找到要刪除的節點
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                TreeNode successor = getMin(node.right);
                node.data = successor.data;
                node.right = deleteRec(node.right, successor.data);
            }

            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        private TreeNode getMin(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }

        // 中序列印
        public void inOrderPrint() {
            inOrder(root);
            System.out.println();
        }

        private void inOrder(TreeNode node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.data + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int val : values) bst.insert(val);

        System.out.println("=== 練習 3.4：BST 第k小/大元素查詢 ===");

        System.out.print("中序走訪（排序結果）: ");
        bst.inOrderPrint();

        System.out.println("第 2 小元素: " + bst.kthSmallest(2));
        System.out.println("第 2 大元素: " + bst.kthLargest(2));
        System.out.println("第 2 小 ~ 第 8 小元素: " + bst.rangeKtoJ(2, 8));

        System.out.println("\n--- 測試動態刪除後的第 2 小查詢 ---");
        bst.delete(20);  // 假設原本第 2 小是 20
        bst.inOrderPrint();
        System.out.println("刪除 20 後第 2 小: " + bst.kthSmallest(2));
    }
}
