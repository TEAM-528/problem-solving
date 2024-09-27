import sys

input = sys.stdin.readline

n = int(input())

A = []
for i in range(n):
    A.append((int(input()), i))

sorted_A = sorted(A)

result = 0
for i in range(n):
    result = max(result, sorted_A[i][1] - A[i][1])

print(result + 1)