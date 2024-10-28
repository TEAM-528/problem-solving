# 20m

def find_time(level, diffs, times):
    n = len(diffs)
    total_time = 0
    for i in range(n):
        diff = diffs[i]
        time_cur = times[i]
        time_prev = 0
        if i > 0:
            time_prev = times[i-1]
        
        if diff <= level:
            total_time += time_cur
        else:
            total_time += (diff - level) * (time_prev + time_cur) + time_cur
    return total_time
    

def solution(diffs, times, limit):
    answer = 0
    
    l = 1
    r = max(diffs)
    while l <= r:
        mid = (l + r) // 2
        solved_time = find_time(mid, diffs, times)
        if solved_time <= limit:
            r = mid - 1
            answer = mid
        else:
            l = mid + 1
    
    return answer