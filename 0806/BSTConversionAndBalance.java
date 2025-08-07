// 練習 3.9：BST轉換與平衡
// 檔名：BSTConversionAndBalance.java

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 將 BST 轉換為排序的雙向鏈結串列
    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;

        DoublyListNode(int val) {
            this.val = val;
        }
    }

    static DoublyListNode prevNode = null;
    static DoublyListNode headNode = null;

    public static DoublyListNode bstToDoublyList(TreeNode root) {
        prevNode = null;
        headNode = null;
        inOrderToDoublyList(root);
        return headNode;
    }

    private static void inOrderToDoublyList(TreeNode node) {
        if (node == null) return;
        inOrderToDoublyList(node.left);
        DoublyListNode cur = new DoublyListNode(node.val);
        if (prevNode == null) {
            headNode = cur;
        } else {
            prevNode.next = cur;
            cur.prev = prevNode;
        }
        prevNode = cur;
        inOrderToDoublyList(node.right);
    }

    // 2. 將排序陣列轉換為平衡 BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBSTFromSortedArray(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBSTFromSortedArray(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBSTFromSortedArray(nums, left, mid - 1);
        node.right = buildBSTFromSortedArray(nums, mid + 1, right);
        return node;
    }

    // 3. 檢查 BST 是否平衡，並列印平衡因子
    static boolean isBalanced = true;

    public static boolean checkBalanced(TreeNode root) {
        isBalanced = true;
        System.out.println("3. 各節點平衡因子如下（左高 - 右高）：");
        heightAndPrintBalance(root);
        return isBalanced;
    }

    private static int heightAndPrintBalance(TreeNode node) {
        if (node == null) return 0;
        int left = heightAndPrintBalance(node.left);
        int right = heightAndPrintBalance(node.right);
        int balanceFactor = left - right;
        System.out.println("節點值 " + node.val + " 的平衡因子為：" + balanceFactor);
        if (Math.abs(balanceFactor) > 1) isBalanced = false;
        return Math.max(left, right) + 1;
    }

    // 4. 將 BST 中每個節點的值改為所有大於等於該節點值的總和
    static int sum = 0;

    public static void convertBSTToGreaterTree(TreeNode root) {
        sum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null) return;
        reverseInOrder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInOrder(node.left);
    }

    // 輔助：中序列印 BST 結構
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        // 建立 BST：
        //         5
        //       /   \
        //      3     8
        //     / \     \
        //    2   4     10
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        // 1. BST 轉雙向鏈表
        DoublyListNode dll = bstToDoublyList(root);
        System.out.print("1. BST 轉排序雙向鏈結串列：");
        for (DoublyListNode cur = dll; cur != null; cur = cur.next) {
            System.out.print(cur.val + " <-> ");
        }
        System.out.println("null");

        // 2. 排序陣列 -> 平衡 BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        System.out.print("2. 平衡 BST 中序走訪：");
        printInorder(balancedBST);
        System.out.println();

        // 3. 檢查 BST 是否平衡並列印平衡因子
        boolean balanced = checkBalanced(root);
        System.out.println("是否整體平衡：" + balanced);

        // 4. 節點值改為大於等於它的總和
        convertBSTToGreaterTree(root);
        System.out.print("4. 將BST中每個節點的值改為所有大於等於該節點值的總和：");
        printInorder(root);
        System.out.println();
    }
}
