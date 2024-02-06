import sys

input = sys.stdin.readline


# 특정 원소가 속한 집합을 찾기
def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]


# 두 원소가 속한 집합을 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


n = int(input())

# 그래프(트리)를 인접리스트 형태로 저장할 이차원 리스트 초기화
graph = [[] for _ in range(n + 1)]
# 노드의 방문 여부를 저장할 리스트 초기화
visited = [False] * (n + 1)

# 모든 간선을 담을 리스트와 최종 비용을 담을 변수
edges = []

# 부모 테이블 상에서, 부모를 자기 자신으로 초기화
parent = [0] * (n + 1)  # 부모 테이블 초기화
for i in range(1, n + 1):
    parent[i] = i

# 간선 리스트 입력받기
for i in range(1, n + 1):
    data = list(map(int, sys.stdin.readline().split()))
    for j in range(i + 1, n + 1):
        if j != i:
            edges.append((data[j - 1], i, j))

# 간선을 비용순으로 정렬
edges.sort()

c = 0
m = 0
new_edges = []

# 간선을 하나씩 확인하며 최소신장 트리 만들기
for edge in edges:
    cost, a, b = edge
    # 이미 설치된 고속도로는 트리에 추가
    if cost < 0:
        c -= cost
        union_parent(parent, a, b)
    # 사이클이 발생하지 않는 경우에만 트리에 추가
    elif find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        c += cost
        m += 1
        new_edges.append((a, b))

print(c, m)
for edge in new_edges:
    a, b = edge
    print(a, b)

'''
5
   0  -10 1000  -20 1000
 -10    0   10  -30 1000
1000   10    0   30   10
 -20  -30   30    0   20
1000 1000   10   20    0
'''