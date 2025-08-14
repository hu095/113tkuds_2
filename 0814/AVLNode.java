public class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // 葉節點高度=1
    }

    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // 簡單測試用 main
    public static void main(String[] args) {
        // 建立節點
        AVLNode root = new AVLNode(10);
        root.left = new AVLNode(5);
        root.right = new AVLNode(20);

        // 更新子樹高度
        root.left.updateHeight();
        root.right.updateHeight();
        root.updateHeight();

        // 印出高度與平衡因子
        System.out.println("根節點高度: " + root.height);
        System.out.println("根節點平衡因子: " + root.getBalance());

        // 測試左子樹再加一層
        root.left.left = new AVLNode(3);
        root.left.left.updateHeight();
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("\n加入左子節點後:");
        System.out.println("根節點高度: " + root.height);
        System.out.println("根節點平衡因子: " + root.getBalance());
    }
}
