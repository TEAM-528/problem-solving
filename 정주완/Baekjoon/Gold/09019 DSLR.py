import sys
from collections import deque

input = sys.stdin.readline


def D(n):
    return (2 * n) % 10000


def S(n):
    return (n - 1) % 10000


def L(n):
    return n // 1000 + (n % 1000) * 10


def R(n):
    return n // 10 + (n % 10) * 1000


T = int(input())
for _ in range(T):
    a, b = map(int, input().split())

    queue = deque([[a, '']])
    visited = [False] * 10001
    visited[a] = True

    while queue:
        v, cmd = queue.popleft()

        if v == b:
            print(cmd)
            break
        else:
            d = D(v)
            s = S(v)
            l = L(v)
            r = R(v)

            if not visited[d]:
                visited[d] = True
                queue.append([d, cmd + 'D'])
            if not visited[s]:
                visited[s] = True
                queue.append([s, cmd + 'S'])
            if not visited[l]:
                visited[l] = True
                queue.append([l, cmd + 'L'])
            if not visited[r]:
                visited[r] = True
                queue.append([r, cmd + 'R'])