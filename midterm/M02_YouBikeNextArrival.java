import java.io.*;

public class M02_YouBikeNextArrival {
    static int toMinutes(String hhmm) {
        String[] p = hhmm.trim().split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toHHMM(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    static int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(br.readLine());
        }

        String queryStr = br.readLine();
        int q = toMinutes(queryStr);

        int idx = upperBound(times, q);
        if (idx == n) {
            System.out.println("答案 No bike");
        } else {
            System.out.println("答案 " + toHHMM(times[idx]));
        }
    }
}
