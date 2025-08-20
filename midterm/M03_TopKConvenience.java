import java.io.*;
import java.util.*;


public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;
        Item(String name, int qty) { this.name = name; this.qty = qty; }
    }

    // 「堆內部」的比較器：定義何者為「較差」以放在堆頂（Min-Heap）
    // 我們希望堆頂永遠是 Top-K 裡「最差」的那個：qty 小者、qty 同則 name 字典序大的較差。
    static final Comparator<Item> HEAP_COMP = (a, b) -> {
        if (a.qty != b.qty) return Integer.compare(a.qty, b.qty); // qty 小的在前（較差）
        return b.name.compareTo(a.name); // qty 相同，name 較大的在前（較差）
    };

    // 判斷 cand 是否「比 worst 更好」
    static boolean betterThan(Item cand, Item worst) {
        if (cand.qty != worst.qty) return cand.qty > worst.qty;
        return cand.name.compareTo(worst.name) < 0; // name 較小者更好
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n, K
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> pq = new PriorityQueue<>(K, HEAP_COMP);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) { i--; continue; } // 防呆：跳過空行
            st = new StringTokenizer(line);
            String name = st.nextToken();         // 題目保證品名不含空白
            int qty = Integer.parseInt(st.nextToken());

            Item cand = new Item(name, qty);
            if (pq.size() < K) {
                pq.offer(cand);
            } else if (betterThan(cand, pq.peek())) {
                pq.poll();
                pq.offer(cand);
            }
        }

        // 倒出並依「qty 降冪、name 升冪」排序輸出
        List<Item> ans = new ArrayList<>(pq);
        ans.sort((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty); // qty 大的在前
            return a.name.compareTo(b.name);                          // name 小的在前
        });

        System.out.println("---");

        StringBuilder sb = new StringBuilder();
        for (Item it : ans) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：
 *   1) 每讀入一筆商品資料，可能觸發一次堆操作 (offer/poll)，成本 O(log K)。
 *   2) 共 n 筆輸入，因此維護堆的總成本為 O(n log K)。
 *   3) 最後輸出時對 K 筆資料排序，需 O(K log K)，但通常 K << n，可忽略。
 */