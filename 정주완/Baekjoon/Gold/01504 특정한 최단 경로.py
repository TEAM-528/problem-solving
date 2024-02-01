import sys
import heapq

input = sys.stdin.readline
INF = int(1e9)

n, e = map(int, input().split())

graph = [[] for _ in range(n + 1)]
d_from_1 = [INF] * (n + 1)
d_from_v1 = [INF] * (n + 1)
d_from_v2 = [INF] * (n + 1)
d4 = [INF] * (n + 1)

for i in range(0, e):
    a, b, c = list(map(int, sys.stdin.readline().split()))
    graph[a].append([b, c])
    graph[b].append([a, c])

v1, v2 = map(int, input().split())


def dijkstra(start, distance):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        dist, now = heapq.heappop(q)
        # 현재 노드가 이미 처리된 적있는 노드라면 무시
        if distance[now] < dist:
            continue
        # 현재 노드와 연결된 다른 인접한 노드들을 확인
        for i in graph[now]:
            cost = dist + i[1]
            # 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))


dijkstra(1, d_from_1)
dijkstra(v1, d_from_v1)
dijkstra(v2, d_from_v2)

length1 = d_from_1[v1] + d_from_v1[v2] + d_from_v2[n]
length2 = d_from_1[v2] + d_from_v2[v1] + d_from_v1[n]
result = min(length1, length2)
if result >= INF:
    result = -1

print(result)

'''
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
'''
