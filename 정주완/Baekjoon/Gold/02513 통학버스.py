import sys
from collections import deque

input = sys.stdin.readline

n, k, s = map(int, input().split())

data = []
left_apartment = deque([])
right_apartment = deque([])

for _ in range(n):
    data.append(list(map(int, input().split())))

data.sort(key=lambda x: x[0])

# 학교에서 먼 아파트가 큐의 맨 앞으로 오도록 저장
for locate, students in data:
    if locate < s:
        left_apartment.append([locate, students])
    else:
        right_apartment.appendleft([locate, students])


def calculate_bus_routes(apartments, school_location, bus_capacity):
    total_distance = 0
    while apartments:
        students_on_bus = 0
        max_distance = 0
        while apartments and students_on_bus < bus_capacity:
            locate, students = apartments[0]
            # 다 태워도 버스 정원보다 작으면 전부 태운다
            if students_on_bus + students <= bus_capacity:
                apartments.popleft()
                students_on_bus += students
                max_distance = max(max_distance, abs(school_location - locate))
            # 버스 정원을 초과하면 일단 태울 수 있는 학생만큼 다 태운다.
            else:
                apartments[0][1] -= (bus_capacity - students_on_bus)
                students_on_bus = bus_capacity
                max_distance = max(max_distance, abs(school_location - locate))
        total_distance += (max_distance * 2)
    return total_distance


result = 0
result += calculate_bus_routes(left_apartment, s, k)
result += calculate_bus_routes(right_apartment, s, k)

print(result)