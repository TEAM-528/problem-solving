import sys

n, c = map(int, input().split())

arr = []
for _ in range(n):
    arr.append(int(sys.stdin.readline().rstrip()))
arr.sort()

start = 1
end = arr[n - 1] - arr[0]

result = 0

while start <= end:
    mid = (start + end) // 2

    current_router = arr[0]
    cnt = 1

    for i in range(1, len(arr)):
        if arr[i] >= current_router + mid:
            cnt += 1
            current_router = arr[i]
            
    if cnt < c:
        end = mid - 1
    elif cnt >= c:
        result = mid
        start = mid + 1

print(result)