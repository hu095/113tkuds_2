// 練習 3.1：二元樹基本操作練習
// 檔名：BinaryTreeBasicOperations.java

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 1. 計算總和與平均值
    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.data + sum(root.left) + sum(root.right);
    }

    public static int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    // 2. 找出最大與最小值節點
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.data, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 計算最大寬度
    public static int getMaxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 範例樹：
        //       10
        //      /  \
        //     5    15
        //    / \   /
        //   3   7 12

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);

        System.out.println("=== 二元樹基本操作練習 ===");

        int totalSum = sum(root);
        int totalCount = count(root);
        System.out.printf("節點總和: %d\n", totalSum);
        System.out.printf("節點平均值: %.2f\n", totalCount == 0 ? 0 : (double) totalSum / totalCount);

        System.out.println("最大值節點: " + findMax(root));
        System.out.println("最小值節點: " + findMin(root));

        System.out.println("樹的最大寬度: " + getMaxWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }
}
