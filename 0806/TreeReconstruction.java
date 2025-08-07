// 練習 3.8：樹的重建
// 檔名：TreeReconstruction.java

import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 根據前序和中序走訪結果重建二元樹
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int inRoot = inMap.get(pre[ps]);
        int leftSize = inRoot - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, inRoot - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, inRoot + 1, ie, inMap);
        return root;
    }

    // 2. 根據後序和中序走訪結果重建二元樹
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(post[pe]);
        int inRoot = inMap.get(post[pe]);
        int leftSize = inRoot - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, inRoot - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, inRoot + 1, ie, inMap);
        return root;
    }

    // 3. 根據層序走訪結果重建完全二元樹
    public static TreeNode buildFromLevelOrder(int[] levelorder) {
        if (levelorder.length == 0) return null;
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelorder.length) {
            TreeNode current = queue.poll();
            if (i < levelorder.length) {
                current.left = new TreeNode(levelorder[i++]);
                queue.offer(current.left);
            }
            if (i < levelorder.length) {
                current.right = new TreeNode(levelorder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    // 4. 驗證重建的樹是否正確（中序走訪列印）
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 驗證重建結果是否與原序列一致
    public static List<Integer> getInorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    public static boolean verifyReconstruction(int[] expectedInorder, TreeNode root) {
        List<Integer> actualInorder = getInorder(root);
        if (expectedInorder.length != actualInorder.size()) return false;
        for (int i = 0; i < expectedInorder.length; i++) {
            if (expectedInorder[i] != actualInorder.get(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 測試：前序 + 中序
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};
        TreeNode preInTree = buildFromPreIn(preorder, inorder);
        System.out.print("根據前序和中序走訪結果重建二元樹：");
        printInorder(preInTree);
        System.out.println("  驗證結果：" + verifyReconstruction(inorder, preInTree));

        // 測試：後序 + 中序
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode postInTree = buildFromPostIn(postorder, inorder);
        System.out.print("根據後序和中序走訪結果重建二元樹：");
        printInorder(postInTree);
        System.out.println("  驗證結果：" + verifyReconstruction(inorder, postInTree));

        // 測試：層序建完全二元樹
        int[] levelorder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode levelTree = buildFromLevelOrder(levelorder);
        System.out.print("根據層序走訪結果重建完全二元樹：");
        printInorder(levelTree);
        System.out.println("  驗證結果：" + verifyReconstruction(new int[]{4,2,5,1,6,3,7}, levelTree));
    }
}
