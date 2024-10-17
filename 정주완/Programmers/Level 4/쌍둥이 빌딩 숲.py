def solution(n, count):
    dp = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

    dp[1][1] = 1
    for i in range(2, n + 1):
        for j in range(1, n + 1):
            dp[i][j] = (i - 1) * 2 * dp[i - 1][j] + dp[i - 1][j - 1]

    return dp[n][count] % 1000000007