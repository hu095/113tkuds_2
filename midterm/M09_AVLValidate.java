import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n)，每節點檢查 BST 與高度平衡
 * Space Complexity: O(h)，遞迴深度
 */

public class M09_AVLValidate {
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

    static boolean checkBST(Node root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return checkBST(root.left, min, root.val) && checkBST(root.right, root.val, max);
    }

    static int checkAVL(Node root) {
        if (root == null) return 0;
        int lh = checkAVL(root.left);
        if (lh == -1) return -1;
        int rh = checkAVL(root.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] parts = br.readLine().trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(parts[i]);

        Node root = buildTree(arr);

        if (!checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }
}
