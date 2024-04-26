n = int(input())
data = list(map(int, input().split()))
data.sort()

result = 0


def binary_search(array, target, start, end, i, j):
    while start <= end:
        mid = (start + end) // 2
        if array[mid] == target:
            if mid == i or mid == j:
                if mid != start and array[mid - 1] == array[mid] and mid - 1 != i and mid - 1 != j:
                    return mid - 1
                elif mid != end and array[mid + 1] == array[mid] and mid + 1 != i and mid + 1 != j:
                    return mid + 1
                else:
                    return None
            return mid
        elif array[mid] > target:
            end = mid - 1
        elif array[mid] < target:
            start = mid + 1
    return None


for i in range(n):
    for j in range(n):
        tmp = binary_search(data, data[i] - data[j], 0, n - 1, i, j)
        if tmp is not None and j != i:
            result += 1
            break

print(result)