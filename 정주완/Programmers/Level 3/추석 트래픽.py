#58m

def solution(lines):
    answer = 0    
    updated_lines = [] # 로그의 시작시간과 끝시간 쌍을 저장할 배열
    time_points = [] # 모든 로그의 시작시간, 끝시간을 저장할 배열
    
    # 입력 데이터 전처리
    for line in lines:
        _, end_time_str, T_str = line.split()
        
        # 응답완료시간(=끝시간)을 밀리초로 변환 (날짜는 고려하지 않음)
        h, m, s = end_time_str.split(":")        
        end_time = int(h)*60*60*1000 + int(m)*60*1000 + int(float(s)*1000)
        
        # 처리시간을 밀리초로 변환
        T = int(float(T_str[:-1])*1000)
        
        # 시작시간을 계산 (처리시간은 시작시간과 끝시간을 포함)
        start_time = end_time - T + 1
        
        updated_lines.append([start_time, end_time])
        time_points.append(start_time)
        time_points.append(end_time)        
    
    # 모든 시작시간, 끝시간에 대해 1초 구간(윈도우)을 만들어 처리 초당 최대 처리량 계산
    for time in time_points:
        window_start = time
        window_end = window_start + 999
        count = 0
        for start, end in updated_lines:
            if start <= window_end and end >= window_start:
                count += 1
        answer = max(count,answer)           
        
        
    return answer