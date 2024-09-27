import sys

input = sys.stdin.readline

n, k = map(int, input().split())

data = []
use_room_info = [[0, 0] for _ in range(k)]

for _ in range(n):
    s, e = map(int, input().split())
    data.append([s, e])

data.sort(key=lambda x: x[1])
result = 0

for s, e in data:
    room_num = -1
    for i in range(k):
        if use_room_info[i][1] < s:
            room_num = i
            break
    if room_num >= 0:
        use_room_info[room_num] = [s, e]
        use_room_info.sort(key=lambda x: x[1], reverse=True)
        result += 1

print(result)