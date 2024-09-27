import sys

input = sys.stdin.readline

n = int(input())
current = list(map(int, input().rstrip()))
target = list(map(int, input().rstrip()))


def case1():
    data = current[:]  # 깊은복사
    # 1번 스위치 체크
    cnt = 1
    data[0] = 1 if data[0] == 0 else 0
    data[1] = 1 if data[1] == 0 else 0

    # 2~n-1번 스위치 체크
    for i in range(1, n - 1):
        if data[i - 1] != target[i - 1]:
            cnt += 1
            data[i - 1] = 1 if data[i - 1] == 0 else 0
            data[i] = 1 if data[i] == 0 else 0
            data[i + 1] = 1 if data[i + 1] == 0 else 0

    # n번 스위치 체크
    if data[n - 2] == target[n - 2] and data[n - 1] == target[n - 1]:
        return cnt
    elif data[n - 2] != target[n - 2] and data[n - 1] != target[n - 1]:
        cnt += 1
        data[n - 2] = 1 if data[n - 2] == 0 else 0
        data[n - 1] = 1 if data[n - 1] == 0 else 0
        return cnt
    else:
        return 1e9

def case2():
    data = current[:]  # 깊은복사
    # 1번 스위치 체크x
    cnt = 0
    # 2~n-1번 스위치 체크
    for i in range(1, n - 1):
        if data[i - 1] != target[i - 1]:
            cnt += 1
            data[i - 1] = 1 if data[i - 1] == 0 else 0
            data[i] = 1 if data[i] == 0 else 0
            data[i + 1] = 1 if data[i + 1] == 0 else 0

    # n번 스위치 체크
    if data[n - 2] == target[n - 2] and data[n - 1] == target[n - 1]:
        return cnt
    elif data[n - 2] != target[n - 2] and data[n - 1] != target[n - 1]:
        cnt += 1
        data[n - 2] = 1 if data[n - 2] == 0 else 0
        data[n - 1] = 1 if data[n - 1] == 0 else 0
        return cnt
    else:
        return 1e9


result = min(case1(),case2())
if result == 1e9:
    print(-1)
else:
    print(result)