import sys

input = sys.stdin.readline

n, k = map(int, input().split())
dp = [100005] * (k + 1)
coin = []

for j in range(0, n):
    coin.append(int(input()))

for i in range(0, n):
    for j in range(1, k + 1):
        if j % coin[i] == 0:
            dp[j] = min(dp[j], j // coin[i])

        if j - coin[i] >= 0:
            dp[j] = min(dp[j], dp[j - coin[i]] + 1)

if dp[k] == 100005:
    print(-1)
else:
    print(dp[k])