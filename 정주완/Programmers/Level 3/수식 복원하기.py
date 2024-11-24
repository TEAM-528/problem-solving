# 58m

# 10진수를 N진수로 변환
def base10toN(number, N):
    if number == 0:
        return '0'    
    result = ''
    while number > 0:
        # number를 N으로 나눈 나머지를 구하고
        # 그 값을 문자열로 변환하여 result 앞에 추가
        result = str(number % N) + result
        number //= N
    return result
    

def solution(expressions):
    questions = []
    parsed_expressions = []
    base = [False] * 2 + [True] * 8
    
    # 문자열 파싱 및 불가능한 진법 처리
    for expression in expressions:
        A, operator, B, _ , C = expression.split()
                    
        max_num = max(max(A), max(B), max(C))
        if C == 'X':
            questions.append([A, operator, B])
            max_num = max(max(A), max(B))
        else:
            parsed_expressions.append([A, operator, B, C])
        # n 이하의 진법은 불가능
        for i in range(2, int(max_num) + 1):
            if base[i] == True:
                base[i] = False
    
    # 수식을 확인하며 불가능한 진법 확인
    for A, operator, B, C in parsed_expressions:
        for i in range(2, 10):
            if base[i] == False:
                continue            
            new_A, new_B, new_C = int(A, i), int(B, i), int(C, i)
            if operator == '+' and new_A + new_B != new_C:
                base[i] = False
            elif operator == '-' and new_A - new_B != new_C:
                base[i] = False
    
    # 가능한 진법만 pos_base에 저장
    pos_base = []
    for i in range(2, 10):
        if base[i] == True:
            pos_base.append(i)
    
    # 정답 계산
    answer = []
    for A, operator, B in questions:
        if operator == '+':
            C = base10toN(int(A, pos_base[0]) + int(B, pos_base[0]), pos_base[0])
        else:
            C = base10toN(int(A, pos_base[0]) - int(B, pos_base[0]), pos_base[0])
            
        for i in pos_base:
            if operator == '+':
                tmp_C = base10toN(int(A, i) + int(B, i), i)
            else:
                tmp_C = base10toN(int(A, i) - int(B, i), i)
            # 결과값이 다른 값이 있으면 C = '?'
            if tmp_C != C:
                C = '?'
                break
        answer.append(f'{A} {operator} {B} = {C}')
        
    return answer