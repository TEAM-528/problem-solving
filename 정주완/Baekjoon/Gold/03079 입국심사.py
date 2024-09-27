import sys
input = sys.stdin.readline

n, m = map(int, input().split())

data = []
for _ in range(n):
    data.append(int(input()))

start = min(data)
answer = end = max(data) * m

while start <= end:
    mid = (start + end) // 2

    total = 0  # mid시간 동안 검사할 수 있는 사람의 수
    for i in range(n):
        total += mid // data[i]

    # mid 시간에 m명 이상을 검사할 수 있으면 (= 조건 만족)
    if total >= m:
        # 더 왼쪽을 탐색
        end = mid - 1
        answer = min(answer, mid)
    # m명을 검사할 수 없으면 (= 조건 불만족)
    else:
        # 더 오른쪽을 탐색
        start = mid + 1

print(answer)
