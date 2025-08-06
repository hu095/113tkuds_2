import java.util.Arrays;

public class SelectionSortImplementation {
    public static void selectionSort(int[] array) {
        int comparisons = 0, swaps = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                comparisons++;
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }

            if (i != minIdx) {
                int temp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪結果：%s\n", i + 1, Arrays.toString(array));
        }

        System.out.printf("總比較次數：%d，總交換次數：%d\n", comparisons, swaps);
    }

    public static void main(String[] args) {
        int[] numbers = {64, 25, 12, 22, 11};

        System.out.println("原始陣列：" + Arrays.toString(numbers));
        selectionSort(numbers);
        System.out.println("排序結果：" + Arrays.toString(numbers));
    }
}
