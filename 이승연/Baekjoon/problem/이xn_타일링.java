package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이xn_타일링 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        
        for(int i=1; i<dp.length; i++) {
            if (i==1 || i==2) {
                dp[i] = i;
                continue;
            }
            dp[i] = (dp[i-1]+dp[i-2])%10007;
        }

        System.out.println(dp[n]%10007);
    }
}
