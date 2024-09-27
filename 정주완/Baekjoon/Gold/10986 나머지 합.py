import sys

input = sys.stdin.readline

n, m = map(int, input().split())
data = list(map(int, input().split()))

prefix_sum = [0] * n
mod_count = [0] * m

prefix_sum[0] = data[0]
mod_count[data[0] % m] += 1

for i in range(1, n):
    prefix_sum[i] = prefix_sum[i - 1] + data[i]
    mod_count[prefix_sum[i] % m] += 1

res = mod_count[0]


for i in mod_count:
    res += i * (i - 1) // 2

print(res)