import java.io.*;
import java.util.*;

/*
 * Time Complexity: O(n log n)，建堆 O(n)，每次取出 O(log n)
 * Space Complexity: O(n)，存 (score,index) 配對
 */

public class M11_HeapSortWithTie {
    static class Item implements Comparable<Item> {
        int score, idx;
        Item(int s, int i){ score=s; idx=i; }
        public int compareTo(Item o) {
            if (score != o.score) return score - o.score; // 遞增排序
            return idx - o.idx; // tie-break by index
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] parts = br.readLine().trim().split("\\s+");
        PriorityQueue<Item> pq = new PriorityQueue<>();
        for (int i=0;i<n;i++) {
            int s = Integer.parseInt(parts[i]);
            pq.add(new Item(s,i));
        }

        // === 輸出區分 ===
        System.out.println("Input:");
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print(parts[i] + (i==n-1 ? "\n" : " "));
        }

        StringBuilder out = new StringBuilder();
        while(!pq.isEmpty()){
            out.append(pq.poll().score).append(" ");
        }

        System.out.println("\nOutput:");
        System.out.println(out.toString().trim());
    }
}
