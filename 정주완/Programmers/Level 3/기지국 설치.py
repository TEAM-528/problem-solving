# 49m

def solution(n, stations, w):
    answer = 0
    radio_range = []
    for station in stations:
        radio_range.append((max(1, station - w), min(n, station + w)))
    radio_range.append((n+1,n+1))
    
    cur_locate = 1
    for r in radio_range:
        if cur_locate < r[0]:
            unreachable_range = r[0] - cur_locate
            answer += (r[0] - cur_locate) // (w * 2 + 1)
            if unreachable_range % (w * 2 + 1) > 0:
                answer += 1
        cur_locate = r[1] + 1  

    return answer