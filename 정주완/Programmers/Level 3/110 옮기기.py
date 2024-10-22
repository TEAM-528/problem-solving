# 주어진 문자열에서 110을 제거
def remove_110(string):
    stack = []
    for char in string:
        stack.append(char)
        # 스택에 쌓인 개수가 3개 이상이고, 마지막 3개가 '110'이면
        if len(stack) >= 3 and ''.join(stack[-3:]) == '110':
            stack.pop()
            stack.pop()
            stack.pop()
    return ''.join(stack)


def solution(s):
    answer = []
    for string in s:
        str_remove_110 = remove_110(string)
        # 제거된 110의 개수 계산
        count_110 = (len(string) - len(str_remove_110)) // 3
        idx = str_remove_110.rfind('0')
        if idx == -1:
            # '0'이 없으면 문자열의 맨 앞에 110 모두 삽입
            answer.append('110' * count_110 + str_remove_110)
        else:
            # '0'이 있으면 그 뒤에 110 모두 삽입
            answer.append(str_remove_110[:idx + 1] + '110' * count_110 + str_remove_110[idx + 1:])

    return answer