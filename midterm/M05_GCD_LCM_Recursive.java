import java.io.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b; // 先除後乘避免溢位
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split("\\s+");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);

        long g = gcd(a, b);
        long l = lcm(a, b);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
 * Time Complexity: O(log(min(a,b)))
 * 說明：
 *   • 遞迴歐幾里得算法每次取餘數，數值快速縮小，最多執行 O(log(min(a,b))) 次。
 *   • lcm 僅需一次除法與一次乘法 → O(1)。
 *   • 總合仍為 O(log(min(a,b)))。
 * Space Complexity: O(log(min(a,b)))，來自遞迴呼叫堆疊深度。
 */