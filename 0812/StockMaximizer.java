import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int K) {
        List<Integer> profits = new ArrayList<>();
        int n = prices.length;

        // 找出所有可獲利交易
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                profits.add(prices[i] - prices[i - 1]);
            }
        }

        // 大根堆取最大 K 筆獲利
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int totalProfit = 0;
        for (int i = 0; i < K && !maxHeap.isEmpty(); i++) {
            totalProfit += maxHeap.poll();
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        int[][] priceSets = {
            {2, 4, 1},
            {3, 2, 6, 5, 0, 3},
            {1, 2, 3, 4, 5}
        };
        int[] Ks = {2, 2, 2};
        String[] explanations = {
            "(在價格2時買入，在價格4時賣出)",
            "(在價格2時買入，在價格6時賣出得利潤4；在價格0時買入，在價格3時賣出得利潤3)",
            "(一次交易：1買入5賣出)"
        };

        for (int i = 0; i < priceSets.length; i++) {
            int profit = maxProfit(priceSets[i], Ks[i]);
            System.out.print("價格：" + Arrays.toString(priceSets[i]) + ", K=" + Ks[i] + "\n");
            System.out.println("答案：" + profit + " " + explanations[i] + "\n");
        }
    }
}
