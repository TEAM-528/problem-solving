# 48m
from collections import deque


def bfs(board):
    N = len(board)
    visited = [[[999 for _ in range(N)] for _ in range(N)] for _ in range(2)]
    visited[0][0][1] = 0

    queue = deque([[0, 0, 1]])
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    while queue:
        # state : 0이면 가로, 1이면 세로
        state, x, y = queue.popleft()

        # 회전
        if state == 0:
            # 아래 두칸 모두 0
            if (x + 1 < N and board[x + 1][y] == 0 and board[x + 1][y - 1] == 0):
                # 왼쪽 축, 시계방향 회전
                if (visited[1][x + 1][y - 1] > visited[state][x][y] + 1):
                    visited[1][x + 1][y - 1] = visited[state][x][y] + 1
                    queue.append([1, x + 1, y - 1])
                # 오른쪽 축, 반시계방향 회전
                if (visited[1][x + 1][y] > visited[state][x][y] + 1):
                    visited[1][x + 1][y] = visited[state][x][y] + 1
                    queue.append([1, x + 1, y])
            # 위 두칸 모두 0
            if (x - 1 >= 0 and board[x - 1][y] == 0 and board[x - 1][y - 1] == 0):
                # 왼쪽 축, 반시계방향 회전
                if (visited[1][x][y - 1] > visited[state][x][y] + 1):
                    visited[1][x][y - 1] = visited[state][x][y] + 1
                    queue.append([1, x, y - 1])
                    # 오른쪽 축, 시계방향 회전
                if (visited[1][x][y] > visited[state][x][y] + 1):
                    visited[1][x][y] = visited[state][x][y] + 1
                    queue.append([1, x, y])
        if state == 1:
            # 오른쪽 두칸 모두 0
            if (y + 1 < N and board[x][y + 1] == 0 and board[x - 1][y + 1] == 0):
                # 위쪽 축, 반시계방향 회전
                if (visited[0][x - 1][y + 1] > visited[state][x][y] + 1):
                    visited[0][x - 1][y + 1] = visited[state][x][y] + 1
                    queue.append([0, x - 1, y + 1])
                # 아래쪽 축, 시계방향 회전
                if (visited[0][x][y + 1] > visited[state][x][y] + 1):
                    visited[0][x][y + 1] = visited[state][x][y] + 1
                    queue.append([0, x, y + 1])
            # 왼쪽 두칸 모두 0
            if (y - 1 >= 0 and board[x][y - 1] == 0 and board[x - 1][y - 1] == 0):
                # 위쪽 축, 시계방향 회전
                if (visited[0][x - 1][y] > visited[state][x][y] + 1):
                    visited[0][x - 1][y] = visited[state][x][y] + 1
                    queue.append([0, x - 1, y])
                # 아래쪽 축, 반시계방향 회전
                if (visited[0][x][y] > visited[state][x][y] + 1):
                    visited[0][x][y] = visited[state][x][y] + 1
                    queue.append([0, x, y])

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if state == 0:
                if nx < 0 or nx >= N or ny < 1 or ny >= N:
                    continue
                if board[nx][ny] == 1 or board[nx][ny - 1] == 1:
                    continue
                if visited[state][nx][ny] > visited[state][x][y] + 1:
                    visited[state][nx][ny] = visited[state][x][y] + 1
                    queue.append([state, nx, ny])
            elif state == 1:
                if nx < 1 or nx >= N or ny < 0 or ny >= N:
                    continue
                if board[nx][ny] == 1 or board[nx - 1][ny] == 1:
                    continue
                if board[nx][ny] == 0 and visited[state][nx][ny] > visited[state][x][y] + 1:
                    visited[state][nx][ny] = visited[state][x][y] + 1
                    queue.append([state, nx, ny])
                    
    return min(visited[0][N - 1][N - 1], visited[1][N - 1][N - 1])


def solution(board):
    answer = bfs(board)
    return answer