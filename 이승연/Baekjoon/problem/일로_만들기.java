package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일로_만들기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        
        int[] dp = new int[X+1];

        for(int i=1; i<dp.length; i++) {
            if (i == 1) {
                dp[i] = 0;
                continue;
            }
            if (i == 2 || i == 3) {
                dp[i] = 1;
                continue;
            }
            dp[i] = dp[i-1]+1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3]+1, dp[i]);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i/2]+1, dp[i]);
            }
        }

        System.out.println(dp[X]);
    }
}
