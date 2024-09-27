import sys

input = sys.stdin.readline

data = input().rstrip()
explosion = input().rstrip()
stack = []

for d in data:
    stack.append(d)
    if len(stack) >= len(explosion) and ''.join(stack[-len(explosion):]) == explosion:
        for _ in range(len(explosion)):
            stack.pop()

if len(stack) == 0:
    print('FRULA')
else:
    print(''.join(stack))
