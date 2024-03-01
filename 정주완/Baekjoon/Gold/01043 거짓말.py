import sys

input = sys.stdin.readline


def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]


def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


n, m = map(int, input().split())

parent = [0] * (n + 1)
for i in range(1, n + 1):
    parent[i] = i

knowers = list(map(int, input().split()))
for i in range(1, knowers[0] + 1):
    parent[knowers[i]] = 0

parties = []
for _ in range(m):
    data = list(map(int, input().split()))

    parties.append(data[1:])
    for i in range(1, data[0] + 1):
        if find_parent(parent, data[i]) == 0:
            union_parent(parent, data[1], 0)
        else:
            union_parent(parent, data[1], data[i])

ans = 0

for party in parties:
    lie_possible = True
    for member in party:
        if find_parent(parent, member) == 0:
            lie_possible = False
    if lie_possible:
        ans += 1

print(ans)
