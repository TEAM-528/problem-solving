from collections import deque

def has_zero_around(arr, x, y):
    for dy in range(-1, 2):
        for dx in range(-1, 2):
            if arr[x + dx][y + dy] == 0:
                return True
    return False

def bfs(graph,start,target):    
    sx,sy = start
    queue = deque([(sx,sy,0)])    
    graph[sx][sy]=0
    
    directions=[[1,0],[-1,0],[0,-1],[0,1]]    
    while queue:
        x, y, l = queue.popleft()        
        
        if [x,y] == target:
            return l/2
        
        for direction in directions:
            dx, dy = direction
            nx = x+dx
            ny = y+dy
            
            if graph[nx][ny] == 1:   
                queue.append([nx,ny,l+1])
                graph[nx][ny]=0

def solution(rectangle, characterX, characterY, itemX, itemY):
    fill=[[0 for _ in range(105)]for _ in range(105)]
    graph=[[0 for _ in range(105)]for _ in range(105)]
    
    for rect in rectangle:
        sx,sy,ex,ey=rect
        for x in range(sx*2,ex*2+1):
            for y in range(sy*2,ey*2+1):
                fill[x][y]=1   
        
    for y in range(0,101):
        for x in range(0,101):
            if fill[x][y] == 1 and has_zero_around(fill, x, y):
                graph[x][y]=1
                
    answer = bfs(graph,[characterX*2, characterY*2], [itemX*2, itemY*2])
    
    return answer