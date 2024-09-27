import sys
from collections import deque
input = sys.stdin.readline

def bfs(graph, visited, start):
    startX, startY = start
    queue = deque([[startX, startY, 0]])
    visited[startX][startY][0] = 0

    # key_dict = {'a': 0b000001, 'b': 0b000010, 'c': 0b000100, 'd': 0b001000, 'e': 0b010000, 'f': 0b100000}
    key_dict = {'a': 1, 'b': 2, 'c': 4, 'd': 8, 'e': 16, 'f': 32}

    while queue:
        x, y, key = queue.popleft()
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            elif visited[nx][ny][key] == -1:
                # 출구 만나면 이동거리 반환
                if graph[nx][ny] == '1':
                    visited[nx][ny][key] = visited[x][y][key] + 1
                    return visited[nx][ny][key]

                # 키 만나면 키를 저장하고, 키 정보 가지고 새로 탐색 시작
                elif graph[nx][ny] in ['a', 'b', 'c', 'd', 'e', 'f']:
                    updated_key = key | key_dict[graph[nx][ny]]
                    visited[nx][ny][updated_key] = visited[x][y][key] + 1
                    queue.append([nx, ny, updated_key])

                # 문을 만나면
                elif graph[nx][ny] in ['A', 'B', 'C', 'D', 'E', 'F']:
                    visited[nx][ny][key] = visited[x][y][key] + 1
                    # 그 문에 맞는 열쇠가 있을 때만 해당 좌표를 큐에 추가
                    if key & key_dict[graph[nx][ny].lower()]:
                        queue.append([nx, ny, key])

                # 빈칸을 만나면, 해당 좌표 큐에 추가
                elif graph[nx][ny] == '.':
                    queue.append([nx, ny, key])
                    visited[nx][ny][key] = visited[x][y][key] + 1


n, m = map(int, input().split())
graph = []
visited = [[[-1] * 64 for _ in range(m)] for _ in range(n)]

minsik_locate = [0, 0]

for i in range(n):
    data = list(input())
    if '0' in data:
        minsik_locate = [i, data.index('0')]
        data[data.index('0')] = '.'
    graph.append(data)

result = bfs(graph, visited, minsik_locate)

if result == None:
    print(-1)
else:
    print(result)