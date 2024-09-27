import sys

input = sys.stdin.readline

n = int(input())
result = 0
board = [0] * n

def is_possible(x, y):
    for i in range(x):
        # 같은 열에 다른 퀸이 있으면 불가능
        if y == board[i]:
            return False
        # 대각선 위치에 다른 퀸이 있으면 불가능
        elif abs(y - board[i]) == abs(x - i):
            return False
    return True


def n_queens(x):
    if x == n:
        global result
        result += 1
        return

    for y in range(n):
        board[x] = y
        if is_possible(x, y):
            n_queens(x + 1)


n_queens(0)
print(result)