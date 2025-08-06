public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;

            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        double avg = (double) sum / scores.length;

        int aboveAverage = 0;
        for (int score : scores) {
            if (score > avg) aboveAverage++;
        }

        System.out.println("=== 成績統計報表 ===");
        System.out.println("總分數：" + sum);
        System.out.println("平均分數：" + avg);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第統計：A=" + countA + ", B=" + countB + ", C=" + countC + ", D=" + countD + ", F=" + countF);
        System.out.println("高於平均分的學生人數：" + aboveAverage);
    }
}

