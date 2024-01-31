package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구간_합_구하기_4 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        int[][] dp = new int[N+1][N+1];
        String[] n = br.readLine().split(" ");
        for(int i=0; i<n.length; i++) {
            dp[i+1][i+1] = Integer.parseInt(n[i]);
        }

        for(int i=1; i<N+1; i++) {
            if (i == 1) {
                for(int j=1; j<N+1-i; j++) {
                    dp[j][j+1] = dp[j][j]+dp[j+1][j+1];
                }
                continue;
            }

            for(int j=1; j<N+1-i; j++) {
                dp[j][j+i] = dp[j][j+1]+dp[j+2][j+i];
            }
        }

        while (M != 0) {
            String[] info = br.readLine().split(" ");
            int i = Integer.parseInt(info[0]);
            int j = Integer.parseInt(info[1]);

            System.out.println("\ndp: "+dp[i][j]);
            M--;
        }
    }
}
