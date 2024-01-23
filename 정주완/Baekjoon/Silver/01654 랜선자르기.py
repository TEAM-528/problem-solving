import sys

k, n = map(int, input().split())

arr = []
for _ in range(k):
    arr.append(int(sys.stdin.readline().rstrip()))

start = 1
end = max(arr)

result = 0

while start <= end:
    mid = (start + end) // 2

    tmp_sum = 0
    for i in range(k):
        tmp_sum += arr[i] // mid

    # 랜선 개수 합이 n보다 작으면 랜선 길이는 왼쪽에서 확인
    if tmp_sum < n:
        end = mid - 1
    # 랜선 개수 합이 n 이상이면 최대 랜선 길이를 갱신하고 오른쪽에서 확인
    elif tmp_sum >= n:
        result = mid
        start = mid + 1

print(result)