import sys

input = sys.stdin.readline


def find_parent(parent, x):
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return x


def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


n, m, k = map(int, input().split())
candies = [0] + list(map(int, input().split()))
parent = [0] * (n + 1)
for i in range(1, n + 1):
    parent[i] = i

for i in range(m):
    a, b = map(int, input().split())
    union_parent(parent, a, b)

groups = [[0, 0] for _ in range(n + 1)]
for i in range(1, n + 1):
    if i != parent[i]:
        p = find_parent(parent, i)
        groups[p][0] = groups[p][0] + 1
        groups[p][1] = groups[p][1] + candies[i]
    else:
        groups[i][0] = 1
        groups[i][1] = candies[i]

dp = [0 for _ in range(k + 1)]
for i in range(len(groups)):
    if groups[i][0] > 0:
        w, v = groups[i]
        for j in range(k - 1, w - 1, -1):
            if j >= w:
                dp[j] = max(dp[j], dp[j - w] + v)


print(dp[k - 1])
