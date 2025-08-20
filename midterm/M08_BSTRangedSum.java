import java.io.*;
import java.util.*;



public class M08_BSTRangedSum {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    static long dfs(Node root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return dfs(root.right, L, R);
        if (root.val > R) return dfs(root.left, L, R);
        return root.val + dfs(root.left, L, R) + dfs(root.right, L, R);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] parts = br.readLine().trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);
        String[] lr = br.readLine().trim().split("\\s+");
        int L = Integer.parseInt(lr[0]);
        int R = Integer.parseInt(lr[1]);

        Node root = buildTree(arr);
        long sum = dfs(root, L, R);
        System.out.println("Sum: " + sum);
    }
}
