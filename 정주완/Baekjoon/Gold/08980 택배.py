import sys
input = sys.stdin.readline

n, c = map(int, input().split())
m = int(input())

box_info = []
capacity_info = [0] * (n + 1) # 해당 마을에 갈 때 트럭에 적재된 박스 개수

for _ in range(m):
    s, e, box_cnt = map(int, input().split())
    box_info.append([s, e, box_cnt])

box_info.sort(key=lambda x: x[1])
result = 0

for s, e, box_cnt in box_info:
    delivery_box_cnt = box_cnt # 배송할 박스 개수
    for i in range(s, e):
        if c - capacity_info[i] < delivery_box_cnt:
            delivery_box_cnt = c - capacity_info[i]
    for i in range(s, e):
        capacity_info[i] += delivery_box_cnt 
    result += delivery_box_cnt 

print(result)