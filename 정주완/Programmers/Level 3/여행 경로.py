# 09 04

import sys
import copy
sys.setrecursionlimit(5000)

answer = []
    
def dfs(graph,airport,path,n):
    path.append(airport)
    
    answer.append(airport)
    graph[airport].sort()
    
    for i in range(len(graph[airport])):
        도착지 = graph[airport][i]
        if 도착지 != '':
            graph[airport][i] = ''
            tmp = dfs(graph,도착지,path,n)  
            if tmp and len(tmp)==n:
                return tmp
            else:
                path.pop()
                graph[airport][i] = 도착지
                
    else:
        return path
            

def solution(tickets):
    graph = dict()
    
    for ticket in tickets:
        start, end = ticket
        if start in graph:
            graph[start].append(end)
        else:
            graph[start] = [end]            
        if end not in graph:
            graph[end] = []            
    
    return dfs(graph,"ICN",[],len(tickets)+1)