import java.lang.reflect.Field;

public class AVLRotations {

    // 右旋
    public static AVLNode rightRotate(AVLNode y) {
        if (y == null || y.left == null) return y;
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left  = T2;

        y.updateHeight();
        x.updateHeight();
        return x;
    }

    // 左旋
    public static AVLNode leftRotate(AVLNode x) {
        if (x == null || x.right == null) return x;
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left  = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();
        return y;
    }

    // === 測試入口 ===
    public static void main(String[] args) {
        // 建一棵易於測試右旋的樹：y 為根，左子 x，x 的右子 T2
        AVLNode y = new AVLNode(30);
        y.left = new AVLNode(20);
        y.left.right = new AVLNode(25);

        safeUpdate(y);

        System.out.println("=== 旋轉前 ===");
        printTree(y);

        AVLNode newRoot = rightRotate(y);
        System.out.println("\n=== 右旋後 ===");
        printTree(newRoot);

        AVLNode restored = leftRotate(newRoot);
        System.out.println("\n=== 左旋回去後 ===");
        printTree(restored);
    }

    // ===== 輔助：列印樹結構（不綁定欄位名稱） =====
    private static void printTree(AVLNode node) {
        printNode(node, 0);
    }

    private static void printNode(AVLNode node, int depth) {
        if (node == null) return;
        String indent = "  ".repeat(depth);
        String v = readValueLikeField(node);       // 讀 value/key/val/data
        int h = readHeight(node);                  // 讀高度（若無則 -1）
        int b = readBalance(node);                 // 讀平衡因子（若無則 0）

        System.out.printf("%sNode(value=%s, height=%d, balance=%d)%n", indent, v, h, b);
        if (node.left != null) {
            System.out.printf("%s  L-> ", indent);
            printNode(node.left, depth + 2);
        }
        if (node.right != null) {
            System.out.printf("%s  R-> ", indent);
            printNode(node.right, depth + 2);
        }
    }

    // 嘗試讀取常見的值欄位名稱
    private static String readValueLikeField(AVLNode n) {
        String[] candidates = {"value", "key", "val", "data"};
        for (String name : candidates) {
            Field f = getFieldQuiet(n.getClass(), name);
            if (f != null) {
                try {
                    f.setAccessible(true);
                    Object o = f.get(n);
                    return String.valueOf(o);
                } catch (Throwable ignored) { }
            }
        }
        return "<?>"; // 找不到就顯示未知
    }

    private static int readHeight(AVLNode n) {
        try {
            Field f = getFieldQuiet(n.getClass(), "height");
            if (f != null) { f.setAccessible(true); return (int) f.get(n); }
        } catch (Throwable ignored) { }
        return -1;
    }

    private static int readBalance(AVLNode n) {
        try { return n.getBalance(); } catch (Throwable ignored) { }
        return 0;
    }

    private static void safeUpdate(AVLNode n) {
        if (n == null) return;
        try { n.updateHeight(); } catch (Throwable ignored) { }
        safeUpdate(n.left);
        safeUpdate(n.right);
    }

    private static Field getFieldQuiet(Class<?> c, String name) {
        while (c != null) {
            try { return c.getDeclaredField(name); }
            catch (NoSuchFieldException e) { c = c.getSuperclass(); }
        }
        return null;
    }
}
