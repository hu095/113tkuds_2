// 練習 3.5：層序走訪變形
// 檔名：LevelOrderTraversalVariations.java

import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 每層儲存在不同 List
    public static List<List<Integer>> levelOrderByLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.data);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // 之字形層序走訪
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    level.addLast(node.data);
                } else {
                    level.addFirst(node.data);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // 只列印每層最後一個節點
    public static List<Integer> rightmostNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode last = null;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                last = node;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(last.data);
        }
        return result;
    }

    // 垂直層序走訪
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            columnMap.putIfAbsent(current.col, new ArrayList<>());
            columnMap.get(current.col).add(current.node.data);

            if (current.node.left != null) queue.offer(new Pair(current.node.left, current.col - 1));
            if (current.node.right != null) queue.offer(new Pair(current.node.right, current.col + 1));
        }

        result.addAll(columnMap.values());
        return result;
    }

    static class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        // 範例樹：
        //       1
        //     /   \
        //    2     3
        //   / \   / \
        //  4   5 6   7

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("=== 層序走訪 - 每層儲存在 List 中 ===");
        for (List<Integer> level : levelOrderByLevels(root)) {
            System.out.println(level);
        }

        System.out.println("\n=== 之字形層序走訪 ===");
        for (List<Integer> level : zigzagLevelOrder(root)) {
            System.out.println(level);
        }

        System.out.println("\n=== 每層最後一個節點 ===");
        System.out.println(rightmostNodes(root));

        System.out.println("\n=== 垂直層序走訪 ===");
        for (List<Integer> column : verticalOrder(root)) {
            System.out.println(column);
        }
    }
}
