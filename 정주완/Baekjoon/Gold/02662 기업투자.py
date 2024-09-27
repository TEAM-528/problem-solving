import sys

input = sys.stdin.readline

n, m = map(int, input().split())
data = [[0] * (m + 1)]

# 최대 이익을 저장할 dp 테이블
dp = [[0] * (n + 1) for _ in range(m + 1)]
# 최대 이익을 만드는 투자 방식을 저장할 테이블
dp2 = [[[0 for _ in range(m + 1)] for _ in range(n + 1)] for _ in range(m + 1)]

for _ in range(n):
    data.append(list(map(int, input().split())))

for i in range(1, m + 1):
    for j in range(1, n + 1):
        # dp[i][j]에는 투자금액 = j 이고, i번째 투자 기업까지 체크했을 때의 최대 이익 저장
        for k in range(0, j + 1):
            # dp[i][j]가 dp[i - 1][j - k]에 i번째 기업에 k만큼 투자한 한 값을 더한 값으로 갱신되면
            if dp[i][j] < dp[i - 1][j - k] + data[k][i]:
                dp[i][j] = dp[i - 1][j - k] + data[k][i]
                # dp2[i][j]는 dp2[i - 1][j - k]에서 i에 k만큼 더 투자했다고 저장
                dp2[i][j] = dp2[i - 1][j - k].copy()
                dp2[i][j][i] += k

print(dp[m][n])
for i in range(1, m + 1):
    print(dp2[m][n][i], end=' ')
