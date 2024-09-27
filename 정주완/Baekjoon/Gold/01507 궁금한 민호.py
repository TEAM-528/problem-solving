import sys
input = sys.stdin.readline

n = int(input())

graph = []
graph2 = [[[] for _ in range(n)] for _ in range(n)]

for _ in range(n):
    graph.append(list(map(int, input().split())))

for a in range(n):
    for b in range(a, n):
        for c in range(n):
            if a == c or b == c:
                continue
            elif graph[a][b] == graph[a][c] + graph[c][b]:
                graph2[a][b] = [[a, c], [c, b]]
            elif graph[a][b] > graph[a][c] + graph[c][b]:
                print(-1)
                sys.exit()

result = 0
for a in range(n):
    for b in range(a, n):
        if graph2[a][b] == [] and a != b:
            result += graph[a][b]

print(result)
