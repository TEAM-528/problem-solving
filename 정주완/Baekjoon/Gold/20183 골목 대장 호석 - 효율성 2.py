import sys

sys.setrecursionlimit(10 ** 6)

n, m, a, b, c = map(int, input().split())

# 그래프(트리)를 인접리스트 형태로 저장할 이차원 리스트 초기화
graph = [[] for _ in range(n + 1)]
# 노드의 방문 여부를 저장할 리스트 초기화
visited = [False] * (n + 1)
charge_list = []

# 입력을 받아 그래프 생성
for i in range(0, m):
    data = list(map(int, sys.stdin.readline().split()))
    graph[data[0]].append([data[1], data[2]])
    graph[data[1]].append([data[0], data[2]])
    charge_list.append(data[2])

for g in graph:
    g.sort(key=lambda x: x[1])


# dfs 성공 여부를 저장할 임시변수
tmp = False

# a에서 b까지 c로 도달 가능하고, 요금이 max_charge보다 크지 않은지 확인
def dfs(v, money, max_charge):
    global tmp
    if v == b:
        tmp = True
        return True

    else:
        visited[v] = True
        for i in graph[v]:
            if (not visited[i[0]]) and money - i[1] >= 0 and max_charge >= i[1]:
                if dfs(i[0], money - i[1], max_charge):
                    return True

    return False

result = int(1e10)

# 골목 요금 배열을 이분 탐색해서, 해당 요금으로 골목을 지날 수 있는지 확인
charge_list.sort()
start = 0
end = len(charge_list) - 1
while start <= end:
    mid = (start + end) // 2

    tmp = False
    visited = [False] * (n + 1)
    dfs(a, c, charge_list[mid])
    if tmp:
        end = mid - 1
        result = min(result, charge_list[mid])
    else:
        start = mid + 1

if result == int(1e10):
    result = -1
print(result)