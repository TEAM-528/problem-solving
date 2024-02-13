/**
 * [LeetCode] 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * 접근 방식
 * 1) 기존에 주어진 배열보다 한 행과 열이 더 큰 배열 dp를 선언한다.
 * 2) dp[1][1]이 시작점이므로 1로 채운다.
 * 3) (1, 1)부터 (N, M)까지 순회하며 dp 배울을 채울 때, obstacleGrid[i-1][j-1]이 1이라면 (i, j)에 벽이 있다는 뜻이므로 0으로 채운다.
 *    만약 obstacleGrid[i-1][j-1]이 0이라면 dp[i][j-1] + dp[i-1][j] 값을 입력한다.
 * 4) dp[N][M]을 반환한다.
 */

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int N = obstacleGrid.length;
        int M = obstacleGrid[0].length;
        int[][] dp = new int[N + 1][M + 1];
        dp[1][1] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                dp[i][j] += obstacleGrid[i-1][j-1] == 0 ? dp[i-1][j] + dp[i][j-1] : 0;
            }
        }
        return dp[N][M];
    }
}
