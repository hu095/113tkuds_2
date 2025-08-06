import java.util.Arrays;

public class SelectionSortImplementation {
    
    public static int[] selectionSort(int[] array) {
        int comparisons = 0, swaps = 0;
        int[] arr = Arrays.copyOf(array, array.length); // 複製避免修改原陣列

        System.out.println("選擇排序：");
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪排序後：%s\n", i + 1, Arrays.toString(arr));
        }

        System.out.printf("總比較次數：%d，總交換次數：%d\n", comparisons, swaps);
        return arr;
    }

    public static int[] bubbleSort(int[] array) {
        int comparisons = 0, swaps = 0;
        int[] arr = Arrays.copyOf(array, array.length);

        System.out.println();
        System.out.println("氣泡排序：");
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.printf("第 %d 輪排序後：%s\n", i + 1, Arrays.toString(arr));
            if (!swapped) break;
        }

        System.out.printf("總比較次數：%d，總交換次數：%d\n", comparisons, swaps);
        return arr;
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};

        System.out.println("原始陣列：" + Arrays.toString(array));

        System.out.println();

        // 執行選擇排序
        int[] sortedBySelection = selectionSort(array);

        // 執行氣泡排序
        int[] sortedByBubble = bubbleSort(array);

        System.out.println();
        // 最終排序結果比較
        System.out.println("\n--- 排序結果比較 ---");
        System.out.println("選擇排序結果：" + Arrays.toString(sortedBySelection));
        System.out.println("氣泡排序結果：" + Arrays.toString(sortedByBubble));
    }
}
