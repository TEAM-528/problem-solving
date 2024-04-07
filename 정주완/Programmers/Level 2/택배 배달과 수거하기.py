from collections import deque

def solution(cap, n, deliveries, pickups):
    answer = 0
    
    d_queue = deque([])
    p_queue = deque([])
    
    for i in range(n-1,-1,-1):
        for _ in range(deliveries[i]):
            d_queue.append(i+1)
    for i in range(n-1,-1,-1):
        for _ in range(pickups[i]):
            p_queue.append(i+1)
            
    
    while len(d_queue)+len(p_queue):
        car=[]
        while len(car)<cap and d_queue:
            car.append(d_queue.popleft())
            
        if car:
            delivery_distance = car[0]
        else:
            delivery_distance = 0
                        
        car=[]
        while len(car)<cap and p_queue:
            car.append(p_queue.popleft())
            
        if car:
            pickup_distance = car[0]
        else:
            pickup_distance = 0
        
        answer += max(delivery_distance, pickup_distance) * 2
        
    return answer