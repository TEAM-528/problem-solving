import sys
from collections import deque

def bfs(graph, start, visited, size):
    startX, startY = start
    queue = deque([[startX, startY]])
    visited[startX][startY] = 0

    pos_list = [] # 먹을 수 있는 모든 물고기의 좌표를 저장

    while queue:
        x, y = queue.popleft()
        dx = [-1, 0, 0, 1]
        dy = [0, -1, 1, 0]

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            elif visited[nx][ny] == -1:
                # 본인보다 큰 물고기 칸에는 갈 수 없다.
                if graph[nx][ny] > size:
                    continue
                # 빈 칸이나 크기가 같은 물고기가 있는 칸은 지나갈 수 있다.
                elif graph[nx][ny] == 0 or graph[nx][ny] == size:
                    queue.append([nx, ny])
                    visited[nx][ny] = visited[x][y] + 1
                # 본인보다 작은 물고기는 먹을 수 있다.
                elif graph[nx][ny] < size:
                    # 본인보다 작은 물고기의 좌표와 해당 물고기까지의 거리(=이동 시간)
                    pos_list.append([nx, ny, visited[x][y] + 1])

    if pos_list:
        # 거리 순으로 오름차순 정렬, 거리가 같으면 가장 위, 가장 왼쪽에 있는 물고기가 앞에 오도록 정렬
        pos_list.sort(key=lambda x: (x[2], x[0], x[1]))
        return pos_list[0]


input = sys.stdin.readline

n = int(input())

space = []
visited = [[-1] * n for _ in range(n)]

baby_shark_locate = [-1, -1]
baby_shark_size = 2
baby_shark_eat = 0

for i in range(n):
    data = list(map(int, input().split()))
    if 9 in data:
        # 아기 상어 초기 위치를 저장
        baby_shark_locate = [i, data.index(9)]
        data[data.index(9)] = 0
    space.append(data)


result = 0

while True:
    next_locate_data = bfs(space, baby_shark_locate, visited, baby_shark_size)
    if next_locate_data == None:
        # 더 이상 먹을 수 있는 물고기가 없으면 종료
        break
    else:
        nx, ny, l = next_locate_data
        space[nx][ny] = 0 # 물고기를 먹은 후에는 빈칸으로 설정
        baby_shark_locate = [nx, ny]

        result += l
        baby_shark_eat += 1

        # 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
        if baby_shark_eat == baby_shark_size:
            baby_shark_size += 1
            baby_shark_eat = 0
        visited = [[-1] * n for _ in range(n)]

print(result)