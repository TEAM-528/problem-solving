# 51m

from collections import deque

def solution(stones, k):
    answer = 200000001
    
    # 현재 범위에서 유효한 값들의 인덱스를 저장할 queue
    # queue의 맨 앞 값이 현재 범위의 최댓값이 되도록 유지한다.
    queue = deque() 
    
    for i in range(len(stones)):
        # 현재 범위 밖의 값은 queue에서 제거
        if queue and queue[0] < i - k + 1:
            queue.popleft()
        
        # queue에 현재 값보다 작은 값이 있으면 제거
        while queue and stones[queue[-1]] <= stones[i]:
            queue.pop()
        
        # 현재 인덱스를 queue에 추가
        queue.append(i)
        
        # 최댓값 갱신
        if i >= k - 1:
            answer = min(answer, stones[queue[0]])
    
    return answer