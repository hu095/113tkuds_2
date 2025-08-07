// 練習 3.2：BST範圍查詢系統
// 檔名：BSTRangeQuerySystem.java

import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    static class BST {
        TreeNode root;

        // 插入節點
        public void insert(int val) {
            root = insertRec(root, val);
        }

        private TreeNode insertRec(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.data) node.left = insertRec(node.left, val);
            else if (val > node.data) node.right = insertRec(node.right, val);
            return node;
        }

        // 範圍查詢：找出在 [min, max] 範圍內的所有節點
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        private void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null) return;
            if (node.data > min) rangeQueryHelper(node.left, min, max, result);
            if (node.data >= min && node.data <= max) result.add(node.data);
            if (node.data < max) rangeQueryHelper(node.right, min, max, result);
        }

        // 範圍計數
        public int rangeCount(int min, int max) {
            return rangeQuery(min, max).size();
        }

        // 範圍總和
        public int rangeSum(int min, int max) {
            return rangeQuery(min, max).stream().mapToInt(Integer::intValue).sum();
        }

        // 找出最接近的節點
        public int closestValue(int target) {
            TreeNode current = root;
            int closest = root.data;
            while (current != null) {
                if (Math.abs(current.data - target) < Math.abs(closest - target)) {
                    closest = current.data;
                }
                if (target < current.data) {
                    current = current.left;
                } else if (target > current.data) {
                    current = current.right;
                } else {
                    break;
                }
            }
            return closest;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int val : values) {
            bst.insert(val);
        }

        System.out.println("=== BST 範圍查詢系統 ===");

        System.out.println("範圍 [25, 60] 的節點: " + bst.rangeQuery(25, 60));
        System.out.println("節點數量: " + bst.rangeCount(25, 60));
        System.out.println("節點總和: " + bst.rangeSum(25, 60));

        int target = 33;
        System.out.println("最接近 " + target + " 的節點值為: " + bst.closestValue(target));
    }
}
