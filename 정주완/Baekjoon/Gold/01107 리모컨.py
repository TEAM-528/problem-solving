import sys

input = sys.stdin.readline

n = int(input())
m = int(input())
buttons = []
if m > 0:
    buttons = list(map(int, input().split()))


def possible_num(num):
    if num == 0 and 0 in buttons:
        return False
    while num > 0:
        tmp = num % 10
        if tmp in buttons:
            return False
        num //= 10
    return True


plus_num = n
minus_num = n

while not possible_num(plus_num):
    plus_num += 1
    if plus_num > 1000000:
        break
while not possible_num(minus_num):
    minus_num -= 1
    if minus_num < 0:
        minus_num = 1000000
        break

res = min(abs(plus_num - n) + len(str(plus_num)),
          abs(minus_num - n) + len(str(minus_num)),
          abs(100 - n))

print(res)