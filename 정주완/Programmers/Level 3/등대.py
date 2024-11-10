from collections import deque
import sys
sys.setrecursionlimit(10 ** 6)

def dfs(cur_node, parent, graph, visited):
    visited[cur_node] = 0    
    
    if len(graph[cur_node]) == 1 and graph[cur_node][0] == parent:
        visited[parent] = 1
    else:    
        for next_node in graph[cur_node]:
            if visited[next_node] == -1:
                dfs(next_node, cur_node, graph, visited)                
        if visited[cur_node] != 1:
            visited[parent] = 1
                

def solution(n, lighthouse):
    answer = 0
    graph = [[] for _ in range(n + 1)]
    visited = [0] + [-1 for _ in range(n)]
    for a, b in lighthouse:
        graph[a].append(b)
        graph[b].append(a)
    
    dfs(1, 0, graph, visited)  
    answer = sum(visited[1:])  
    return answer