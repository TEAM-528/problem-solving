package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class µ¿Àü_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] dp = new int[k+1];
        int[] coin = new int[n];
        for(int i=0; i<n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        for(int i=1; i<dp.length; i++) {
            dp[i] = 10001;
        }

        for(int i=0; i<coin.length; i++) {
            for (int j=1; j<dp.length; j++) {
                if (j-coin[i] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
                }
            }
        }

        if (dp[k] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
