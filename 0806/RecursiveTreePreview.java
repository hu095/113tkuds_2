import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveTreePreview {

    // === 1. 遞迴計算資料夾總檔案數（模擬） ===
    static class FileSystemNode {
        String name;
        boolean isFile;
        List<FileSystemNode> children;

        public FileSystemNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) {
                children = new ArrayList<>();
            }
        }

        public void addChild(FileSystemNode child) {
            if (!isFile) {
                children.add(child);
            }
        }
    }

    public static int countFiles(FileSystemNode node) {
        if (node.isFile) {
            return 1;
        }
        int total = 0;
        for (FileSystemNode child : node.children) {
            total += countFiles(child);
        }
        return total;
    }


    // === 2. 遞迴列印多層選單結構 ===
    static class MenuItem {
        String title;
        List<MenuItem> subMenus;

        public MenuItem(String title) {
            this.title = title;
            this.subMenus = new ArrayList<>();
        }

        public void addSubMenu(MenuItem menu) {
            subMenus.add(menu);
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + "- " + menu.title);
        for (MenuItem sub : menu.subMenus) {
            printMenu(sub, level + 1);
        }
    }


    // === 3. 遞迴處理巢狀陣列的展平 ===
    public static List<Object> flattenNestedList(List<?> nestedList) {
        List<Object> flatList = new ArrayList<>();
        for (Object element : nestedList) {
            if (element instanceof List<?>) {
                flatList.addAll(flattenNestedList((List<?>) element));
            } else {
                flatList.add(element);
            }
        }
        return flatList;
    }


    // === 4. 遞迴計算巢狀清單最大深度 ===
    public static int maxDepth(List<?> nestedList) {
        int max = 1; // 單層最小深度為1
        for (Object element : nestedList) {
            if (element instanceof List<?>) {
                int depth = 1 + maxDepth((List<?>) element);
                if (depth > max) {
                    max = depth;
                }
            }
        }
        return max;
    }


    // 主程式測試
    public static void main(String[] args) {
        // 測試 1: 資料夾總檔案數
        FileSystemNode root = new FileSystemNode("root", false);
        FileSystemNode folderA = new FileSystemNode("folderA", false);
        FileSystemNode folderB = new FileSystemNode("folderB", false);
        FileSystemNode file1 = new FileSystemNode("file1.txt", true);
        FileSystemNode file2 = new FileSystemNode("file2.txt", true);
        FileSystemNode file3 = new FileSystemNode("file3.txt", true);

        root.addChild(folderA);
        root.addChild(file1);
        folderA.addChild(folderB);
        folderA.addChild(file2);
        folderB.addChild(file3);

        System.out.println("=== 資料夾總檔案數 ===");
        System.out.println("Total files: " + countFiles(root));  // 預期: 3

        // 測試 2: 多層選單列印
        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem menu1 = new MenuItem("檔案");
        MenuItem menu2 = new MenuItem("編輯");
        MenuItem submenu1 = new MenuItem("開啟");
        MenuItem submenu2 = new MenuItem("儲存");
        MenuItem submenu3 = new MenuItem("剪下");
        MenuItem submenu4 = new MenuItem("複製");

        mainMenu.addSubMenu(menu1);
        mainMenu.addSubMenu(menu2);
        menu1.addSubMenu(submenu1);
        menu1.addSubMenu(submenu2);
        menu2.addSubMenu(submenu3);
        menu2.addSubMenu(submenu4);

        System.out.println("\n=== 多層選單列印 ===");
        printMenu(mainMenu, 0);

        // 測試 3: 巢狀陣列展平
        List<Object> nestedList = Arrays.asList(
            1,
            Arrays.asList(2, 3),
            4,
            Arrays.asList(5, Arrays.asList(6, 7)),
            8
        );

        System.out.println("\n=== 巢狀陣列展平 ===");
        List<Object> flatList = flattenNestedList(nestedList);
        System.out.println(flatList);  // 預期: [1, 2, 3, 4, 5, 6, 7, 8]

        // 測試 4: 巢狀清單最大深度
        List<Object> nestedList2 = Arrays.asList(
            1,
            Arrays.asList(2, Arrays.asList(3, Arrays.asList(4))),
            5
        );
        System.out.println("\n=== 巢狀清單最大深度 ===");
        System.out.println("Max depth: " + maxDepth(nestedList2));  // 預期: 4
    }
}
