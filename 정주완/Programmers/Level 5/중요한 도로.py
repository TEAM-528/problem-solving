import heapq
from collections import deque

def solution(n, roads):    
    answer = []
    answer_list = [False for i in range(len(roads)+1)]
    
    graph = [[] for _ in range(n + 1)]
    
    for i in range(len(roads)):
        u, v, l, t = roads[i]
        road_num = i + 1
        graph[u].append((v, l, t, road_num))
        graph[v].append((u, l, t, road_num))
    
    path = []
    def dijkstra(start):
        path.clear()
        for _ in range (n+1):
            path.append([])
        distance = [(10**9)*2*200000] * (n + 1)
        distance[start] = 0
        
        q = []
        heapq.heappush(q,(0,start))
        while q:
            dist, cur_node = heapq.heappop(q)
            if distance[cur_node] < dist:
                continue
            else:
                for next_node, l, t, road_num in graph[cur_node]:
                    cost = dist + l + t
                    if cost < distance[next_node]:
                        path[next_node] = []
                        path[next_node].append((cur_node, road_num))                        
                        distance[next_node] = cost
                        heapq.heappush(q,(cost, next_node))
                    elif cost == distance[next_node]:
                        path[next_node].append((cur_node, road_num))  
        return distance

    start_from_n = dijkstra(n) 
    start_from_0 = dijkstra(1)
    
    # 교통량이 감소할 때 예상 소요 시간이 변하는 도로 찾기
    for i in range(1, n+1):
        for next_node, l, t, road_num in graph[i]:
            new_time = start_from_0[i] + l + start_from_n[next_node]
            if new_time < start_from_0[-1]:
                answer_list[road_num] = True
                
                
    # 교통량이 증가할 때 예상 소요 시간이 변하는 도로 찾기
    queue = deque([n])
    visited = [False for _ in range(n+1)]
    visited[n] = True
    while queue:
        queue_size = len(queue)
        for _ in range(queue_size):
            node = queue.popleft()
            for prev_node, road_num in path[node]:   
                if len(path[node]) == 1 and queue_size == 1:
                    answer_list[road_num] = True
                if visited[prev_node]:
                    continue
                visited[prev_node] = True
                queue.append(prev_node)
    
    for i in range(len(roads)+1):
        if answer_list[i]:
            answer.append(i)
            
    if len(answer) == 0:
        answer.append(-1)
    
    return answer