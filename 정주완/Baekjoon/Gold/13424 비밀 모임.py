import sys

input = sys.stdin.readline

INF = int(1e9)

t = int(input())

for _ in range(t):
    n, m = map(int, input().split())

    # 2차원 리스트(그래프 표현)를 만들고, 모든 값을 무한으로 초기화
    graph = [[INF] * (n + 1) for _ in range(n + 1)]

    # 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
    for a in range(1, n + 1):
        for b in range(1, n + 1):
            if a == b:
                graph[a][b] = 0

    # 각 간선에 대한 정보를 입력받아, 그 값으로 초기화
    for _ in range(m):
        a, b, c = map(int, input().split())
        if c < graph[a][b]:
            graph[a][b] = c
            graph[b][a] = c

    # 점화식에 따라 플로이드 워셜 알고리즘을 수햄
    for k in range(1, n + 1):
        for a in range(1, n + 1):
            for b in range(1, n + 1):
                graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

    k = int(input())
    friends = list(map(int, input().split()))

    res = 1
    min_sum = INF
    for i in range(1, n + 1):
        distance_sum = 0
        for j in range(k):
            distance_sum += graph[friends[j]][i]
        if distance_sum < min_sum:
            min_sum = distance_sum
            res = i

    print(res)