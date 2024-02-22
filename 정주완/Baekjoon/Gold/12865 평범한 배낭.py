import sys

input = sys.stdin.readline
n, k = map(int, input().split())

dp = [[0] * (k + 1) for _ in range(n + 1)]
arr = [[0,0]]

for _ in range(n):
    weight, value = map(int, input().split())
    arr.append([weight, value])

for i in range(1, n + 1):
    for j in range(1, k + 1):
        w, v = arr[i]
        if j >= w:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - w] + v)
        else:
            dp[i][j] = dp[i - 1][j]


print(dp[n][k])