// 練習 3.7：樹的路徑問題
// 檔名：TreePathProblems.java

import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 找出從根節點到所有葉節點的路徑
    public static List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfsAllPaths(root, path, result);
        return result;
    }

    private static void dfsAllPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsAllPaths(node.left, path, result);
            dfsAllPaths(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    // 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);
        return root.val + Math.max(left, right);
    }

    // 4. 計算樹中任意兩節點間的最大路徑和（任意兩節點）
    static class MaxPathSumResult {
        int maxSum = Integer.MIN_VALUE;
    }

    public static int maxPathSum(TreeNode root) {
        MaxPathSumResult result = new MaxPathSumResult();
        dfsMaxPath(root, result);
        return result.maxSum;
    }

    private static int dfsMaxPath(TreeNode node, MaxPathSumResult result) {
        if (node == null) return 0;
        int left = Math.max(0, dfsMaxPath(node.left, result));
        int right = Math.max(0, dfsMaxPath(node.right, result));
        result.maxSum = Math.max(result.maxSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // 新樹：
        //         8
        //        / \
        //       3   10
        //      / \    \
        //     1   6    14
        //        / \   /
        //       4   7 13

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        System.out.println("=== 練習 3.7：樹的路徑問題 ===");

        System.out.println("1. 所有根到葉節點的路徑：");
        List<List<Integer>> paths = allPaths(root);
        for (List<Integer> p : paths) System.out.println(p);

        int target = 27;
        System.out.println("2. 是否存在和為 " + target + " 的路徑：" + hasPathSum(root, target));

        System.out.println("3. 根到葉最大和：" + maxRootToLeafSum(root));

        System.out.println("4. 樹的最大路徑和（任意兩節點）：" + maxPathSum(root));
    }
}
