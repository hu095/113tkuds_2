import java.util.*;

/**
 * 題目 10：多層級快取系統
 * 三層：L1(小,快), L2(中,中), L3(大,慢)
 *  - 每層使用 LinkedHashMap(accessOrder=true) 實作 LRU
 *  - 全域 max-heap (heat) 依 score 追蹤最應該升階的項目
 *  - get/put 時更新 freq 與 lastAccess，觸發 rebalance/promotion
 *
 * 容量：L1=2, L2=5, L3=10
 * 成本(僅作評分參考)：L1=1, L2=3, L3=10
 */
public class MultiLevelCacheSystem {

    // ====== 參數 ======
    private static final int CAP_L1 = 2, CAP_L2 = 5, CAP_L3 = 10;
    private static final int COST_L1 = 1, COST_L2 = 3, COST_L3 = 10;

    // ====== 內部資料結構 ======
    static class Entry {
        final int key;
        String value;
        int level;          // 1/2/3
        int freq = 0;       // 存取次數
        long lastAccess;    // 最近存取時間

        Entry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            touch();
        }

        void touch() {
            freq++;
            lastAccess = System.nanoTime();
        }

        // 分數越高越值得往上提；簡單模型：頻率為主、近期性次之、層級懲罰
        double score() {
            double recencyBoost = (lastAccess / 1e6); // 以 ms 粗略加權，越新越大
            double levelPenalty = switch (level) {
                case 1 -> 0.0;
                case 2 -> 1.5;  // 在低層有些懲罰 → 越可能升上去
                default -> 3.0;
            };
            return freq * 100.0 + recencyBoost - levelPenalty * 10.0;
        }

        @Override public String toString() { return key + ""; }
    }

    // 每層 LRU（accessOrder=true：迭代順序由最少最近使用到最新）
    private final LinkedHashMap<Integer, Entry> L1 = new LinkedHashMap<>(16, 0.75f, true);
    private final LinkedHashMap<Integer, Entry> L2 = new LinkedHashMap<>(16, 0.75f, true);
    private final LinkedHashMap<Integer, Entry> L3 = new LinkedHashMap<>(16, 0.75f, true);

    // Max-heap：根據 score 挑出該升階的熱資料（使用 lazy 機制，必要時檢查當前層）
    private final PriorityQueue<Entry> heat = new PriorityQueue<>((a, b) -> Double.compare(b.score(), a.score()));

    // ====== 公開 API ======

    /** 讀取 */
    public String get(int key) {
        Entry e = L1.get(key);
        if (e != null) {
            e.touch();
            heat.offer(e);
            // L1 命中不用升階，僅維持 LRU
            return e.value;
        }

        e = L2.remove(key);
        if (e != null) {
            e.touch();
            putToLevel(L1, e, 1, CAP_L1); // L2 命中 → 升至 L1
            heat.offer(e);
            maybePromote();               // 再嘗試全域升階（通常已在頂層，不會動）
            return e.value;
        }

        e = L3.remove(key);
        if (e != null) {
            e.touch();
            putToLevel(L2, e, 2, CAP_L2); // L3 命中 → 至少升到 L2
            heat.offer(e);
            maybePromote();               // 視分數再往 L1 推
            return e.value;
        }

        return null; // 未命中
    }

    /** 寫入（新資料先放 L1；若 key 已存在於任一層則更新值並依需要升階） */
    public void put(int key, String value) {
        Entry e = L1.get(key);
        if (e != null) {
            e.value = value; e.touch(); heat.offer(e);
            return;
        }
        e = L2.remove(key);
        if (e != null) {
            e.value = value; e.touch();
            putToLevel(L1, e, 1, CAP_L1);
            heat.offer(e);
            maybePromote();
            return;
        }
        e = L3.remove(key);
        if (e != null) {
            e.value = value; e.touch();
            putToLevel(L2, e, 2, CAP_L2);
            heat.offer(e);
            maybePromote();
            return;
        }

        // 全新
        Entry ne = new Entry(key, value, 1);
        putToLevel(L1, ne, 1, CAP_L1);
        heat.offer(ne);
        maybePromote();
    }

    // ====== 內部：層級維護 ======

    private void putToLevel(LinkedHashMap<Integer, Entry> levelMap, Entry e, int newLevel, int cap) {
        e.level = newLevel;
        levelMap.put(e.key, e);
        ensureCapacity(levelMap, cap, newLevel);
    }

    /** 保證層級容量；溢出就把 LRU 往下層丟，L3 再溢出就淘汰 */
    private void ensureCapacity(LinkedHashMap<Integer, Entry> levelMap, int cap, int level) {
        while (levelMap.size() > cap) {
            // 取出 LRU（迭代第一個）
            Map.Entry<Integer, Entry> eldest = levelMap.entrySet().iterator().next();
            levelMap.remove(eldest.getKey());
            Entry victim = eldest.getValue();

            if (level == 1) {
                putToLevel(L2, victim, 2, CAP_L2);
            } else if (level == 2) {
                putToLevel(L3, victim, 3, CAP_L3);
            } else {
                // L3 溢出 → 直接淘汰（丟掉）
            }
        }
    }

    /** 根據 heap 中目前最高分項決定是否升階（lazy：必要時才跳過不一致的項） */
    private void maybePromote() {
        // 做少量嘗試即可（避免過度抖動），這裡每次至多處理 2 個候選
        int tries = 2;
        while (tries-- > 0 && !heat.isEmpty()) {
            Entry top = heat.poll();
            if (top == null) break;
            // 確認 top 目前仍存在於某層
            Entry cur = findInLevels(top.key);
            if (cur == null) continue; // 已被淘汰

            // 視分數與層級嘗試升階
            if (cur.level == 3) {
                // 先升到 L2；如果分數仍高，heap 會再推一次
                L3.remove(cur.key);
                putToLevel(L2, cur, 2, CAP_L2);
            } else if (cur.level == 2) {
                // 升到 L1
                L2.remove(cur.key);
                putToLevel(L1, cur, 1, CAP_L1);
            } else {
                // 已在 L1，不動
            }
        }
    }

    private Entry findInLevels(int key) {
        Entry e = L1.get(key);
        if (e != null) return e;
        e = L2.get(key);
        if (e != null) return e;
        return L3.get(key);
    }

    // ====== 除錯輸出 ======
    private static <K, V> List<K> keys(LinkedHashMap<K, V> map) {
        // 顯示為 [最近] 方向較直覺：我們把迭代結果（LRU→MRU）反過來輸出
        List<K> list = new ArrayList<>(map.keySet());
        Collections.reverse(list);
        return list;
    }

    public void debugState() {
        System.out.println("L1: " + keys(L1) + ", L2: " + keys(L2) + ", L3: " + keys(L3));
    }

    // ====== Demo：依題目的測試流程輸出 ======
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        // put(1,"A"); put(2,"B"); put(3,"C");
        cache.put(1, "A"); cache.put(2, "B"); cache.put(3, "C");
        System.out.println("put(1, \"A\"); put(2, \"B\"); put(3, \"C\");");
        cache.debugState(); // 期望：L1: [2,3], L2: [1], L3: []

        // get(1); get(1); get(2);
        cache.get(1); cache.get(1); cache.get(2);
        System.out.println("\nget(1); get(1); get(2);");
        cache.debugState(); // 1 被頻繁存取，應移到 L1 （L1:[1,2], L2:[3]）

        // put(4,"D"); put(5,"E"); put(6,"F");
        cache.put(4, "D"); cache.put(5, "E"); cache.put(6, "F");
        System.out.println("\nput(4, \"D\"); put(5, \"E\"); put(6, \"F\");");
        cache.debugState(); // 依熱度/容量自動分佈
    }
}
