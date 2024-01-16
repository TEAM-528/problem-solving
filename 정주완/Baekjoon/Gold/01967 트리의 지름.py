import sys

# Python이 정한 최대 재귀 깊이 늘리기
# 백준 기준 기본값 1000
sys.setrecursionlimit(10 ** 6)

n = int(input())

# 그래프(트리)를 인접리스트 형태로 저장할 이차원 리스트 초기화
graph = [[] for _ in range(n + 1)]
# 노드의 방문 여부를 저장할 리스트 초기화
visited = [False] * (n + 1)

# 입력을 받아 트리 생성
for _ in range(n - 1):
    parent, child, weight = map(int, sys.stdin.readline().split())
    # 각 노드 배열에 인접 노드와 가중치 저장
    graph[parent].append([child, weight])
    graph[child].append([parent, weight])

result = 0


def dfs(v):
    visited[v] = True

    # 자식들의 높이를 저장할 배열 초기화
    # 자식이 없을 경우 기본값은 0, 0
    child_height = [0, 0]
    # 모든 자식(인접 노드)에 대해서
    for node, weight in graph[v]:
        # 방문하지 않은 노드이면
        if not visited[node]:
            # 반환값 + 가중치 를 자식 높이 배열에 추가
            child_height.append(dfs(node) + weight)

    # 내림차순으로 높이 배열 정렬
    child_height.sort(reverse=True)

    # 자식 길이 중 가장 긴 2개를 합한 값과 최대값 비교하여 갱신
    global result
    result = max(result, child_height[0] + child_height[1])

    # 자식 중 가장 긴 경로의 길이 반환
    return child_height[0]


# 1번 노드부터 탐색 시작
dfs(1)
print(result)