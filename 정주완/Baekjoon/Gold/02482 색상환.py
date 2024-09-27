import sys

input = sys.stdin.readline

n = int(input())
k = int(input())

dp = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

dp[2][0] = 1
dp[2][1] = 2

dp[3][0] = 1
dp[3][1] = 3

for i in range(4, n + 1):
    for j in range(k + 1):
        dp[i][j] = dp[i - 2][j - 1] + dp[i - 1][j]

print(dp[n][k] % 1000000003)