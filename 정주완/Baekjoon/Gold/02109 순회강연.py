import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())

data = []
schedule = [0] * 10001

for _ in range(n):
    p, d = map(int, input().split())
    data.append([p, d])

data.sort(key=lambda x: (x[1], x[0]), reverse=True)


def fine_schedule(p, d):
    for i in range(d, 0, -1):
        if schedule[i] == 0:
            schedule[i] = p
            return
        elif schedule[i] < p:
            fine_schedule(schedule[i], i - 1)
            schedule[i] = p
            return


for p, d in data:
    if schedule[d] == 0:
        schedule[d] = p
    elif schedule[d] < p:
        fine_schedule(schedule[d], d - 1)
        schedule[d] = p
    elif schedule[d] >= p:
        fine_schedule(p, d - 1)

print(sum(schedule))