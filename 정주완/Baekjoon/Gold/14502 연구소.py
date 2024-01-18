import sys
import copy
from collections import deque
from itertools import combinations

n, m = map(int, input().split())

input_graph = [[] for _ in range(n)]
visited = [[False] * m for _ in range(n)]

# 입력을 받아 그래프 생성
for i in range(n):
    input_graph[i] = list(map(int, sys.stdin.readline().split()))

# 빈칸의 좌표를 저장할 리스트
empty = []
for i in range(n):
    for j in range(m):
        if input_graph[i][j] == 0:
            empty.append([i, j])

# 벽을 세울 위치를 조합으로 생성
wall_list = list(combinations(empty, 3))


def bfs(graph, x, y):
    queue = deque()
    queue.append((x, y))

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    while queue:
        x, y = queue.popleft()

        # 상하좌우로 이동
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            # 주어진 범위를 벗어나면 pass
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            # 바이러스 주위에서 빈 칸을 발견하면
            if graph[nx][ny] == 0:
                # 바이러스를 퍼트리고 해당 위치에서 반복
                graph[nx][ny] = 2
                queue.append((nx, ny))


max_safe_size = 0
for wall in wall_list:
    copied_graph = copy.deepcopy(input_graph)
    # 벽을 3개 세운다
    copied_graph[wall[0][0]][wall[0][1]] = 1
    copied_graph[wall[1][0]][wall[1][1]] = 1
    copied_graph[wall[2][0]][wall[2][1]] = 1

    # bfs로 바이러스를 퍼트린다.
    for i in range(n):
        for j in range(m):
            if copied_graph[i][j] == 2:
                bfs(copied_graph, i, j)

    # 안전 영역 계산
    cnt = 0
    for i in range(n):
        for j in range(m):
            if copied_graph[i][j] == 0:
                cnt += 1
    max_safe_size = max(max_safe_size, cnt)

print(max_safe_size)