# 200m
import math
from collections import deque


def drop_number(graph, route):
    queue = deque([1])
    while queue:
        cur_node = queue.popleft()
        if route[cur_node] == -1:  # 리프 노드 도착
            return cur_node
        else:
            next_node = graph[cur_node][route[cur_node]]
            # 경로 업데이트
            route[cur_node] = (route[cur_node] + 1) % len(graph[cur_node])
            queue.append(next_node)


def solution(edges, target):
    node_cnt = len(edges) + 2
    graph = [[] for _ in range(node_cnt)]

    for parent_node, child_node in edges:
        graph[parent_node].append(child_node)

    # 초기 route 설정
    route = [-1 for _ in range(node_cnt)]
    for i in range(node_cnt):
        if len(graph[i]) > 0:
            graph[i].sort()
            route[i] = 0

    drop_info = [[] for _ in range(len(target))]
    answer_length = 0
    while True:
        sufficient_drops = True
        for i in range(len(target)):
            cur_max_drop = target[i]
            cur_min_drop = math.ceil(target[i] / 3)
            if len(drop_info[i]) < cur_min_drop:
                sufficient_drops = False
            if len(drop_info[i]) > cur_max_drop:
                return [-1]

        if sufficient_drops:
            break

        cur_leaf_node = drop_number(graph, route) - 1
        drop_info[cur_leaf_node].append(answer_length + 1)
        answer_length += 1

    answer = [0] * answer_length
    for i in range(len(drop_info)):
        if len(drop_info[i]) > 0:
            for j in range(len(drop_info[i]) - 1, -1, -1):
                drop_order = drop_info[i][j]
                if (target[i] - 3) >= j:
                    selected_num = 3
                elif (target[i] - 2) >= j:
                    selected_num = 2
                elif (target[i] - 1) >= j:
                    selected_num = 1
                else:
                    return [-1]

                drop_info[i][j] = (drop_order, selected_num)
                target[i] -= selected_num
                answer[drop_order - 1] = selected_num

    return answer