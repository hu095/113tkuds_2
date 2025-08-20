import java.io.*;


public class M04_TieredTaxSimple {

    static long calcTax(long x) {
        if (x <= 120000) {
            return (x * 5) / 100;
        } else if (x <= 500000) {
            return (x * 12) / 100 - 9000;
        } else if (x <= 1000000) {
            return (x * 20) / 100 - 49000;
        } else {
            return (x * 30) / 100 - 120000;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        long sum = 0;
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(br.readLine().trim());
            long t = calcTax(x);
            sum += t;
            out.append("Tax: ").append(t).append('\n');
        }

        long avg = Math.round(sum * 1.0 / n); // ✅ 改成四捨五入
        out.append("Average: ").append(avg).append('\n');

        System.out.print(out.toString());
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 *   • 每筆收入只需判斷 4 個級距 → O(1)。
 *   • 共 n 筆輸入資料，總時間為 O(n)。
 *   • 額外空間只用常數變數 → O(1)。
 */