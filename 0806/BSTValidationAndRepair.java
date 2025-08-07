// 練習 3.6：BST驗證與修復
// 檔名：BSTValidationAndRepair.java

import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // === 1. 驗證是否為合法 BST ===
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.data <= min || node.data >= max) return false;
        return isValidBSTHelper(node.left, min, node.data) && isValidBSTHelper(node.right, node.data, max);
    }

    // === 2. 找出 BST 中錯誤的節點（僅兩個錯誤節點情況） ===
    static TreeNode first = null, second = null, prev = null;

    public static void findMisplacedNodes(TreeNode node) {
        first = second = prev = null;
        inOrderDetect(node);
    }

    private static void inOrderDetect(TreeNode node) {
        if (node == null) return;
        inOrderDetect(node.left);
        if (prev != null && prev.data > node.data) {
            if (first == null) first = prev;
            second = node;
        }
        prev = node;
        inOrderDetect(node.right);
    }

    // === 3. 修復 BST ===
    public static void recoverBST(TreeNode root) {
        findMisplacedNodes(root);
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    // === 4. 中序收集值 ===
    public static void collectInOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        collectInOrder(node.left, list);
        list.add(node.data);
        collectInOrder(node.right, list);
    }

    // === 5. 最少移除節點讓其成為 BST ===
    public static int minRemovalsToBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        collectInOrder(root, values);
        return values.size() - lengthOfLIS(values);
    }

    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) i = -(i + 1);
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }

    // === 6. 中序列印 ===
    public static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void main(String[] args) {
        System.out.println("=== 練習 3.6：BST 驗證與修復 ===");

        // 錯誤 BST（交換了 15 和 25）
        TreeNode wrongRoot = new TreeNode(20);
        wrongRoot.left = new TreeNode(10);
        wrongRoot.right = new TreeNode(30);
        wrongRoot.left.left = new TreeNode(5);
        wrongRoot.left.right = new TreeNode(25); // 錯誤：應為 15
        wrongRoot.right.left = new TreeNode(15); // 錯誤：應為 25
        wrongRoot.right.right = new TreeNode(40);

        System.out.print("原始中序: ");
        inOrder(wrongRoot);
        System.out.println();
        System.out.println("是否合法 BST: " + isValidBST(wrongRoot));

        // 找出錯誤節點
        findMisplacedNodes(wrongRoot);
        System.out.println("誤置節點為: " +
                (first != null ? first.data : "無") + ", " +
                (second != null ? second.data : "無"));

        // 修復 BST
        recoverBST(wrongRoot);
        System.out.print("修復後中序: ");
        inOrder(wrongRoot);
        System.out.println();
        System.out.println("是否合法 BST: " + isValidBST(wrongRoot));

        // 最少移除節點才能變 BST
        TreeNode invalidRoot = new TreeNode(10);
        invalidRoot.left = new TreeNode(5);
        invalidRoot.right = new TreeNode(8);
        invalidRoot.right.right = new TreeNode(6); // 錯誤結構

        System.out.print("\n非 BST 中序: ");
        inOrder(invalidRoot);
        System.out.println();
        System.out.println("是否合法 BST: " + isValidBST(invalidRoot));
        System.out.println("最少需移除節點數: " + minRemovalsToBST(invalidRoot));
    }
}
