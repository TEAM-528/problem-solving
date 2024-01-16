import sys
from collections import deque

n, m, v = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

for g in graph:
    g.sort()


def dfs(v):
    visited[v] = True
    print(v, end=' ')
    for w in graph[v]:
        if not visited[w]:
            dfs(w)

def bfs(start):
    queue = deque([start])
    visited[start] = True
    while queue:
        v = queue.popleft()
        print(v, end=' ')
        for w in graph[v]:
            if not visited[w]:
                queue.append(w)
                visited[w] = True

dfs(v)
print('')
visited = [False] * (n + 1)
bfs(v)