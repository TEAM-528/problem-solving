import sys
sys.setrecursionlimit(10**6)

n, m, r = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)
order = [0] * (n + 1)

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

for g in graph:
    g.sort()

cnt = 1
def dfs(v):
    global cnt
    visited[v] = True
    order[v] = cnt
    cnt += 1
    for w in graph[v]:
        if not visited[w]:
            dfs(w)

dfs(r)

for i in range(1, n + 1):
    print(order[i])