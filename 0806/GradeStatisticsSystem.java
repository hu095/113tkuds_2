import java.util.Arrays;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int max = scores[0], min = scores[0], sum = 0;
        for (int score : scores) {
            if (score > max) max = score;
            if (score < min) min = score;
            sum += score;
        }

        double avg = sum / (double) scores.length;

        int a = 0, b = 0, c = 0, d = 0, f = 0;
        int aboveAvg = 0;

        for (int score : scores) {
            if (score >= 90) a++;
            else if (score >= 80) b++;
            else if (score >= 70) c++;
            else if (score >= 60) d++;
            else f++;

            if (score > avg) aboveAvg++;
        }

        System.out.println("成績報表");
        System.out.println("成績列表：" + Arrays.toString(scores));
        System.out.printf("平均分數：%.2f\n最高分：%d\n最低分：%d\n", avg, max, min);
        System.out.printf("等第統計：A=%d B=%d C=%d D=%d F=%d\n", a, b, c, d, f);
        System.out.printf("高於平均分的人數：%d\n", aboveAvg);
    }
}
