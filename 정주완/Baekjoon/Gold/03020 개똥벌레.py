import sys

n, h = map(int, sys.stdin.readline().split())

석순 = []
종유석 = []
for i in range(n):
    if i % 2 == 0:
        석순.append(int(sys.stdin.readline().rstrip()))
    else:
        종유석.append(int(sys.stdin.readline().rstrip()))

석순.sort()
종유석.sort()


# arr에서 target 보다 크거나 같은 숫자의 개수를 반환하는 이진 탐색 함수
def binary_search(arr, target):
    start = 0
    end = len(arr) - 1

    cnt = 0
    while start <= end:
        mid = (start + end) // 2

        # 중간 값이 n보다 크거나 같으면 왼쪽 부분 배열 검색
        if arr[mid] >= target:
            cnt = len(arr) - mid  # 현재 위치부터 끝까지의 숫자는 모두 n 이상
            end = mid - 1
        # 중간 값이 n보다 작으면 오른쪽 부분 배열 검색
        else:
            start = mid + 1
    return cnt


min_value = 200001
min_count = 0

for i in range(1, h + 1):
    장애물개수 = binary_search(석순, i) + binary_search(종유석, h - i + 1)
    if 장애물개수 < min_value:
        min_value = 장애물개수
        min_count = 1
    elif 장애물개수 == min_value:
        min_count += 1

print(min_value, min_count)

'''
5
4 1 5 2 3
5
1 3 7 9 5
'''