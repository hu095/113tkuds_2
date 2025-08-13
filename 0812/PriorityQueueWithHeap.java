import java.util.*;

/**
 * 題目 3：使用 Heap 實作優先級佇列
 * 功能：addTask / executeNext / peek / changePriority
 * 重點：
 *  - 使用 PriorityQueue（max-heap 行為）管理任務
 *  - 同優先級用 timestamp 先到先服務（FIFO）
 *  - changePriority 以「懶刪除」實作，不需整堆重建，O(log n)
 */
class Job {
    final String name;
    int priority;
    final long ts; // 用於同優先級：ts 越小代表越早加入

    Job(String name, int priority, long ts) {
        this.name = name;
        this.priority = priority;
        this.ts = ts;
    }

    @Override
    public String toString() { return name + "(" + priority + ")"; }
}

public class PriorityQueueWithHeap {

    // 比較器：優先級高在前；同優先級則先加入者在前
    private final PriorityQueue<Job> pq = new PriorityQueue<>(
        (a, b) -> (a.priority != b.priority)
                ? Integer.compare(b.priority, a.priority)
                : Long.compare(a.ts, b.ts)
    );

    // 紀錄「目前有效版本」：若 pq 頂端不是這裡指到的物件，代表是舊版本（殭屍）需丟棄
    private final Map<String, Job> live = new HashMap<>();

    /** 清掉堆頂所有殭屍（舊版本） */
    private void purge() {
        while (!pq.isEmpty()) {
            Job top = pq.peek();
            Job cur = live.get(top.name);
            if (cur == top) break;   // 頂端是有效版本 → 可用
            pq.poll();               // 頂端是舊版本 → 丟棄
        }
    }

    /** 添加任務；若同名已存在，視為更新優先級（沿用舊 timestamp 以維持 FIFO） */
    public void addTask(String name, int priority) {
        Job old = live.get(name);
        long ts = (old == null) ? System.nanoTime() : old.ts; // 保序：沿用舊 ts
        Job now = new Job(name, priority, ts);
        live.put(name, now);
        pq.offer(now);
    }

    /** 查看下一個要執行的任務（不移除） */
    public Job peek() {
        purge();
        return pq.peek();
    }

    /** 執行優先級最高的任務（移除並回傳） */
    public Job executeNext() {
        purge();
        Job j = pq.poll();
        if (j != null) {
            Job cur = live.get(j.name);
            if (cur == j) live.remove(j.name); // 只移除有效版本
        }
        return j;
    }

    /** 修改任務優先級；若不存在同名任務則不動作 */
    public void changePriority(String name, int newPriority) {
        Job old = live.get(name);
        if (old == null) return; // 無此任務
        Job now = new Job(name, newPriority, old.ts); // 保留舊 ts 維持相對順序
        live.put(name, now);
        pq.offer(now); // 舊物件留在堆中，之後由 purge 丟棄（懶刪除）
    }

    // ====== 簡單測試 ======
    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();

        // 題目測試案例
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        System.out.println(q.executeNext()); // 緊急修復(5)
        System.out.println(q.executeNext()); // 更新(3)
        System.out.println(q.executeNext()); // 備份(1)

        // 同優先級 FIFO
        q.addTask("A", 2);
        try { Thread.sleep(1); } catch (InterruptedException ignored) {}
        q.addTask("B", 2);
        System.out.println(q.peek());        // A(2) 應先於 B(2)

        // changePriority：A 升級後應該先執行
        q.changePriority("A", 10);
        System.out.println(q.executeNext()); // A(10)
        System.out.println(q.executeNext()); // B(2)
        System.out.println(q.executeNext()); // null（無任務）
    }
}
