import sys

input = sys.stdin.readline

n, k = map(int, input().split())
dp = [0] * (k + 1)
coin = []

for j in range(0, n):
    coin.append(int(input()))

coin.sort()
for i in range(0, n):
    for j in range(1, k + 1):
        if j - coin[i] == 0:
            dp[j] += 1
        if j - coin[i] >= 0:
            dp[j] += dp[j - coin[i]]

print(dp[k])