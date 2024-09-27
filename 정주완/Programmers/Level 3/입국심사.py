# 20m 20s

def solution(n, times):
    answer = 0
    
    # 파라메트릭 서치로 풀이
    start = min(times)
    end = max(times) * n
    
    while start <= end:
        mid = (start + end) // 2
        
        passed_people = 0 # mid 시간동안 심사 할 수 있는 사람의 수
        for time in times:
            passed_people += mid // time
        
        if passed_people >= n:
            answer = mid
            end = mid - 1
        else:
            start = mid + 1     
    
    return answer