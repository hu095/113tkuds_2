// 練習 3.10：多路樹與決策樹
// 檔名：MultiWayTreeAndDecisionTree.java

import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ===== 多路樹結構 =====
    static class MultiWayTreeNode {
        String val;
        List<MultiWayTreeNode> children;

        MultiWayTreeNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    // ===== 多路樹：深度優先走訪 =====
    public static void dfs(MultiWayTreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiWayTreeNode child : root.children) {
            dfs(child);
        }
    }

    // ===== 多路樹：廣度優先走訪 =====
    public static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode node = queue.poll();
            System.out.print(node.val + " ");
            for (MultiWayTreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // ===== 計算多路樹高度 =====
    public static int getHeight(MultiWayTreeNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    // ===== 印出每個節點的度數 =====
    public static void printDegree(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println("節點 " + node.val + " 的度數為：" + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegree(child);
        }
    }

    // ===== 簡單的決策樹實作：猜數字遊戲 =====
    static class DecisionNode {
        String question;
        DecisionNode yesBranch;
        DecisionNode noBranch;

        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void runDecisionTree(Scanner scanner, DecisionNode node) {
        if (node == null) return;
        if (node.yesBranch == null && node.noBranch == null) {
            System.out.println("猜測：" + node.question);
            return;
        }
        System.out.println(node.question + " (y/n)");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("y")) {
            runDecisionTree(scanner, node.yesBranch);
        } else {
            runDecisionTree(scanner, node.noBranch);
        }
    }

    public static void main(String[] args) {
        // ===== 1. 建立多路樹 =====
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.children.add(b);
        root.children.add(c);
        root.children.add(d);
        b.children.add(e);
        b.children.add(f);

        System.out.println("1. 多路樹建立完成");

        // ===== 2. 深度優先走訪 =====
        System.out.print("2. 多路樹 DFS：");
        dfs(root);
        System.out.println();

        // ===== 3. 廣度優先走訪 =====
        System.out.print("3. 多路樹 BFS：");
        bfs(root);
        System.out.println();

        // ===== 4. 計算多路樹高度 =====
        int height = getHeight(root);
        System.out.println("4. 多路樹高度為：" + height);

        // ===== 5. 每個節點的度數 =====
        System.out.println("5. 每個節點的度數：");
        printDegree(root);

        // ===== 6. 模擬決策樹：猜數字遊戲 =====
        System.out.println("6. 模擬簡單的猜數字決策樹");

        DecisionNode rootDecision = new DecisionNode("你猜的是偶數嗎？");
        rootDecision.yesBranch = new DecisionNode("你猜的是4嗎？");
        rootDecision.noBranch = new DecisionNode("你猜的是3嗎？");

        Scanner scanner = new Scanner(System.in);
        runDecisionTree(scanner, rootDecision);
        scanner.close();
    }
}
