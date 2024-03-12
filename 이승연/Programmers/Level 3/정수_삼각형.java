public class 정수_삼각형 {
    class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int[][] dp = new int[triangle.length+1][triangle.length+1];

            for(int i=0; i<triangle.length; i++) {
                for(int j=0; j<triangle[i].length; j++) {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i][j]) + triangle[i][j];
                }
            }

            for(int i=1; i<dp.length; i++) {
                answer = Math.max(answer, dp[dp.length-1][i]);
            }

            return answer;
        }
    }
}