import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n)，每個節點檢查一次
 * Space Complexity: O(h)，遞迴深度
 */

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color; // 'B' or 'R'
        Node left, right;
        Node(int v, char c) { val = v; color = c; }
    }

    static Node buildTree(String[] vals) {
        int n = vals.length / 2;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(vals[2*i]);
            char c = vals[2*i+1].charAt(0);
            if (v != -1) nodes[i] = new Node(v, c);
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int li = 2*i+1, ri = 2*i+2;
            if (li < n) nodes[i].left = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }
        return nodes[0];
    }

    static class Result {
        boolean ok;
        int blackHeight;
        Result(boolean o, int h) { ok=o; blackHeight=h; }
    }

    static Result check(Node root) {
        if (root == null) return new Result(true, 1); // NIL 視為黑
        Result L = check(root.left);
        if (!L.ok) return new Result(false, -1);
        Result R = check(root.right);
        if (!R.ok) return new Result(false, -1);
        // 檢查紅紅相鄰
        if (root.color == 'R') {
            if ((root.left != null && root.left.color == 'R') ||
                (root.right != null && root.right.color == 'R')) {
                throw new RuntimeException("RedRedViolation");
            }
        }
        // 黑高度一致
        if (L.blackHeight != R.blackHeight) {
            throw new RuntimeException("BlackHeightMismatch");
        }
        return new Result(true, L.blackHeight + (root.color=='B'?1:0));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] vals = new String[2*n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<2*n;i++) vals[i] = st.nextToken();

        Node root = buildTree(vals);

        // 規則 1: root 為黑
        if (root != null && root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        try {
            check(root);
            System.out.println("RB Valid");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
