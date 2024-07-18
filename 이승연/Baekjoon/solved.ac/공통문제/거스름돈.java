package Baekjoon.problem;
import java.util.*;
public class 거스름돈 {
    class Solution {
        public int solution(int n, int[] money) {
            int answer = 0;
            int[] dp = new int[n+1];

            dp[0] = 1;
            for(int i=0; i<money.length; i++) {
                for(int j=1; j<=n; j++) {
                    if (j-money[i] >= 0) {
                        dp[j] += (dp[j-money[i]]%1000000007);
                    }
                }
            }
            return dp[n];
        }
    }
}
