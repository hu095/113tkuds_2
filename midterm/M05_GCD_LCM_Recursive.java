import java.io.*;

/*
 * Time Complexity:
 *   • gcd 使用歐幾里得遞迴：O(log(min(a,b)))
 *   • lcm 只需一次除法與乘法：O(1)
 *   • 總合：O(log(min(a,b)))
 * Space Complexity: O(log(min(a,b))) 遞迴深度
 */

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
