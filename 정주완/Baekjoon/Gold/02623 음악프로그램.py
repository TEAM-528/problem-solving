import sys

input = sys.stdin.readline

n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]
indegree = [0] * (n + 1)

for _ in range(m):
    data = list(map(int, input().split()))
    for i in range(1, data[0]):
        graph[data[i]].append(data[i + 1])
        indegree[data[i + 1]] += 1

res = []


def topology_sort():
    stack = []
    for i in range(1, n + 1):
        if indegree[i] == 0:
            stack.append(i)
    while stack:
        s = stack.pop()
        res.append(s)
        for i in graph[s]:
            indegree[i] -= 1
            if indegree[i] == 0:
                stack.append(i)


topology_sort()

if len(res) != n:
    print(0)
else:
    for i in res:
        print(i)