def solution(cookie):
    answer = 0
    
    n = len(cookie)
    
    for m in range(n-1):
        l = m
        r = m + 1
        sum_l = cookie[l]
        sum_r = cookie[r]
        
        while True:
            if sum_l == sum_r:
                answer = max(answer, sum_l)
            if sum_l <= sum_r and l > 0:
                l -= 1
                sum_l += cookie[l]
            elif sum_l >= sum_r and r < n - 1:
                r += 1
                sum_r += cookie[r]
            else:
                break
                
    return answer