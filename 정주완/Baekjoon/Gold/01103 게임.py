import sys

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n, m = map(int, input().split())

board = []
visited = [[False for _ in range(m)] for _ in range(n)]
dp = [[0 for _ in range(m)] for _ in range(n)]

for _ in range(n):
    board.append(input())

result = 0


def dfs(board, visited, dp, x, y, cnt):
    global result
    visited[x][y] = True

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    distance = int(board[x][y])
    for i in range(4):
        nx = x + dx[i] * distance
        ny = y + dy[i] * distance
        if nx < 0 or nx >= n or ny < 0 or ny >= m or board[nx][ny] == 'H' or cnt + 1 <= dp[nx][ny]:
            if result == -1:
                break
            else:
                result = max(result, cnt + 1)
                continue
        elif visited[nx][ny]:
            result = -1
            break
        else:
            dp[nx][ny] = cnt + 1
            dfs(board, visited, dp, nx, ny, cnt + 1)
            visited[nx][ny] = False


dfs(board, visited, dp, 0, 0, 0)
print(result)