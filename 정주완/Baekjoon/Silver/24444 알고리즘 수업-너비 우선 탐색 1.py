import sys

from collections import deque

# Python이 정한 최대 재귀 깊이 늘리기
# 백준 기준 기본값 1000
sys.setrecursionlimit(10 ** 6)

n, m, r = map(int, input().split())

graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)
order = [0] * (n + 1)

# 입력을 받아 트리 생성
for _ in range(m):
    u, v = map(int, sys.stdin.readline().split())
    # 각 노드 배열에 인접 노드와 가중치 저장
    graph[u].append(v)
    graph[v].append(u)

for g in graph:
    g.sort()

cnt = 1


def bfs(start):
    global cnt
    queue = deque([start])
    visited[start] = True

    while queue:
        v = queue.popleft()
        order[v] = cnt
        cnt += 1

        for w in graph[v]:
            if not visited[w]:
                queue.append(w)
                visited[w] = True


# 1번 노드부터 탐색 시작
bfs(r)

for i in range(1, n + 1):
    print(order[i])

'''
5 5 1
1 4
1 2
2 3
2 4
3 4
'''