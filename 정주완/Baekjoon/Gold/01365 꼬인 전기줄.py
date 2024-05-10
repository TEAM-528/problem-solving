import sys

input = sys.stdin.readline

n = int(input())
data = list(map(int, input().split()))


# target 보다 작은 값 중 가장 큰 값의 index 반환
def binary_search(array, target):
    start, end = 0, len(array) - 1
    result = -1
    while start <= end:
        mid = (start + end) // 2
        if array[mid] < target:
            result = mid
            start = mid + 1
        else:
            end = mid - 1
    return result


# 가장 긴 증가하는 부분 수열(LIS)의 길이를 반환하는 함수
def LIS(array):
    dp = [0]
    for i in range(len(data)):
        idx = binary_search(dp, data[i]) + 1
        if idx >= len(dp):
            dp.append(data[i])
        else:
            dp[idx] = min(dp[idx], data[i])
    return len(dp) - 1


print(n - LIS(data))
