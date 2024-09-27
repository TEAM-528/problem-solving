import sys
import heapq

sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())
data = []
for _ in range(n):
    a, b = map(int, input().split())
    data.append([a, b])

l, p = map(int, input().split())
data.append([l, 0])
data.sort(key=lambda x: x[0])

answer = 0
heap = []
distance = 0
fuel = p

for i in range(n + 1):
    next_d, next_fuel = data[i]
    # 다음 주유소에 도달할 수 없으면 지나온 주유소 중 가장 연료가 많은 주유소 선택해서 주유
    if next_d - distance > fuel:
        while next_d - distance > fuel:
            if len(heap) == 0:
                print(-1)
                sys.exit()
            else:
                fuel += (-1 * heapq.heappop(heap))
                answer += 1
    fuel -= (next_d - distance)
    distance = next_d
    heapq.heappush(heap, -1 * next_fuel)

print(answer)