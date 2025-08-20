import java.io.*;
import java.util.*;


public class M12_MergeKTimeTables {
    static class Node implements Comparable<Node> {
        int time, listIdx, elemIdx;
        Node(int t,int l,int e){ time=t; listIdx=l; elemIdx=e; }
        public int compareTo(Node o){ return time-o.time; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        List<List<Integer>> lists = new ArrayList<>();

        // === 收集輸入 (同時存下來以便回顯) ===
        StringBuilder inputLog = new StringBuilder();
        inputLog.append(K).append("\n");

        for (int i=0;i<K;i++) {
            int len = Integer.parseInt(br.readLine().trim());
            inputLog.append(len).append("\n");
            String[] parts = br.readLine().trim().split("\\s+");
            List<Integer> cur = new ArrayList<>();
            for (int j=0;j<len;j++) {
                cur.add(Integer.parseInt(parts[j]));
                inputLog.append(parts[j]).append(j==len-1? "\n" : " ");
            }
            lists.add(cur);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i=0;i<K;i++) {
            if (!lists.get(i).isEmpty())
                pq.add(new Node(lists.get(i).get(0), i, 0));
        }

        StringBuilder merged = new StringBuilder();
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            merged.append(cur.time).append(" ");
            if (cur.elemIdx+1 < lists.get(cur.listIdx).size()) {
                int nt = lists.get(cur.listIdx).get(cur.elemIdx+1);
                pq.add(new Node(nt, cur.listIdx, cur.elemIdx+1));
            }
        }

        // === 輸出區分 ===
        System.out.println("Input:");
        System.out.print(inputLog.toString());
        System.out.println("\nOutput:");
        System.out.println(merged.toString().trim());
    }
}
