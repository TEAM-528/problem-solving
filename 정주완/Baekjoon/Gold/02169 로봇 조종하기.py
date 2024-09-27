import sys

input = sys.stdin.readline

n, m = map(int, input().split())
graph = []

for i in range(n):
    graph.append(list(map(int, input().split())))

for i in range(1, m):
    graph[0][i] += graph[0][i - 1]

for i in range(1, n):
    tmp_left = [0 for _ in range(m)]
    tmp_left[0] = graph[i][0] + graph[i - 1][0]
    for j in range(1, m):
        tmp_left[j] = graph[i][j] + max(graph[i - 1][j], tmp_left[j - 1])

    tmp_right = [0 for _ in range(m)]
    tmp_right[m - 1] = graph[i][m - 1] + graph[i - 1][m - 1]
    for j in range(m - 2, -1, -1):
        tmp_right[j] = graph[i][j] + max(graph[i - 1][j], tmp_right[j + 1])

    for j in range(m):
        graph[i][j] = max(tmp_left[j], tmp_right[j])

print(graph[n - 1][m - 1])