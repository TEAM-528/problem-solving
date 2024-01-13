import sys

n = int(sys.stdin.readline())
data = list(map(int, sys.stdin.readline().split()))

stack = []
result = [0] * n

for i in range(n):
    if len(stack) != 0:
        while stack[len(stack) - 1][0] <= data[n - 1 - i]:
            result[stack[len(stack) - 1][1]] = n - i
            stack.pop()
            if len(stack) == 0:
                break

    stack.append([data[n - 1 - i], n - 1 - i])

for i in range(n):
    print(result[i], end=" ")