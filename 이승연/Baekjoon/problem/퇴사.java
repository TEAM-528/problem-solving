package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Επ»η {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+1];
        int[] P = new int[N+1];
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            T[i+1] = Integer.parseInt(info[0]);
            P[i+1] = Integer.parseInt(info[1]);
        }

        int[] dp = new int[N+2];
        for(int i=1; i<dp.length-1; i++) {
            for(int j=i+T[i]; j<dp.length; j++) {
                dp[j] = Math.max(dp[i]+P[i], dp[j]);
            }            
        }

        System.out.println(dp[N+1]);
    }
}
