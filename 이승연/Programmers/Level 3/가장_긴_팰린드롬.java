import java.util.*;

public class 가장_긴_팰린드롬 {
    class Solution {
        public int solution(String s) {
            int answer = 1;
            int[][] dp = new int[s.length()+1][s.length()+1];
            for(int i=0; i<dp.length; i++) {
                dp[i][i] = 1;
            }

            for(int i=1; i<dp.length; i++) {
                for(int j=1; j<dp.length-1 && i+j-1<dp.length; j++) {
                    int start = j;
                    int end = j+i-1;

                    if (start+1==end && s.charAt(start-1)==s.charAt(end-1)) {
                        dp[start][end] = 2;
                        answer = Math.max(dp[start][end], answer);
                        continue;
                    }

                    if(dp[start+1][end-1]!=0 && s.charAt(start-1)==s.charAt(end-1)) {
                        dp[start][end] = dp[start+1][end-1]+2;
                        answer = Math.max(dp[start][end], answer);
                    }
                }
            }

            return answer;
        }
    }
}