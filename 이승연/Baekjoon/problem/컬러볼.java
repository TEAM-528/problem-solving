package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 컬러볼 {
    static int N;
    static int[][] balls;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new int[N][3];
        answer = new int[N];
        for(int i=0; i<N; i++) {
            String[] parsed = br.readLine().split(" ");
            balls[i][0] = Integer.parseInt(parsed[0]); // 색
            balls[i][1] = Integer.parseInt(parsed[1]); // 크기
            balls[i][2] = i;
        }

        Arrays.sort(balls, (int[] o1, int[] o2) -> {
            return o1[1]-o2[1];
        });

        int[] sumByColor = new int[N+1];
        int sum = 0;
        int idx = 0;
        for(int i=0; i< balls.length; i++) {
            while(balls[idx][1] < balls[i][1]) {
                sum+=balls[idx][1];
                sumByColor[balls[idx][0]] += balls[idx][1];
                idx++;
            }
            answer[balls[i][2]] = sum-sumByColor[balls[i][0]];
        }

        for(int i=0; i<answer.length; i++)
            System.out.println(answer[i]);
    }
}
