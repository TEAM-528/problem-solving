public class 보행자_천국 {
    class Solution {
        int MOD = 20170805;
        int[][][] dp;
        public int solution(int m, int n, int[][] cityMap) {
            int answer = 0;
            dp = new int[m][n][2];
            dp[0][0][0] = 1;
            dp[0][0][1] = 1;
            for(int i=0; i<dp.length; i++) {
                for(int j=0; j<dp[0].length; j++) {
                    if (i==0 && j==0) {
                        continue;
                    }
                    if (cityMap[i][j] == 1) continue;
                    if (i==0) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = dp[i][j-1][1];
                        continue;
                    }
                    if (j==0) {
                        dp[i][j][0] = dp[i-1][j][0];
                        dp[i][j][1] = 0;
                        continue;
                    }
                    if (cityMap[i-1][j] == 0) {
                        dp[i][j][0] += (dp[i-1][j][0]+dp[i-1][j][1])%MOD;
                    }
                    if (cityMap[i-1][j] == 2) {
                        dp[i][j][0] += dp[i-1][j][0]%MOD;
                    }
                    if (cityMap[i][j-1] == 0) {
                        dp[i][j][1] += (dp[i][j-1][0]+dp[i][j-1][1])%MOD;
                    }
                    if (cityMap[i][j-1] == 2) {
                        dp[i][j][1] += dp[i][j-1][1]%MOD;
                    }
                }
            }
            answer = (dp[m-1][n-1][0]+dp[m-1][n-1][1])%MOD;
            return answer;
        }
    }
}
