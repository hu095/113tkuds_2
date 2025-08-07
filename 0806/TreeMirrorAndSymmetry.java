
public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // 1. 判斷一棵二元樹是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 2. 比較兩棵樹是否互為鏡像
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.data == t2.data)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    // 3. 將一棵二元樹轉換為其鏡像樹
    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(tmp);
        return root;
    }

    // 4. 檢查一棵樹是否為另一棵樹的子樹
    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSameTree(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    // 輔助：判斷兩棵樹是否結構與資料相同
    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.data == t2.data)
            && isSameTree(t1.left, t2.left)
            && isSameTree(t1.right, t2.right);
    }

    // 中序列印（觀察鏡像變化用）
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 練習 3.3：樹的鏡像與對稱 ===");

        // 對稱樹測試
        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.left.right = new TreeNode(4);
        symmetricRoot.right.left = new TreeNode(4);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(symmetricRoot));
        System.out.print("原始樹中序走訪: ");
        inOrder(symmetricRoot);
        System.out.println();

        // 鏡像轉換測試
        TreeNode mirrored = mirror(symmetricRoot);
        System.out.print("鏡像樹中序走訪: ");
        inOrder(mirrored);
        System.out.println();

        // 互為鏡像測試
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(2);

        System.out.println("t1 和 t2 是否互為鏡像: " + isMirror(t1, t2));

        // 子樹測試
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);

        TreeNode sub = new TreeNode(5);
        sub.left = new TreeNode(3);
        sub.right = new TreeNode(7);

        System.out.println("sub 是否為 root 的子樹: " + isSubtree(root, sub));
    }
}
