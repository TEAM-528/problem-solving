# 45m

def is_possible(string):
    if len(string) not in [1,3,7,15,31,63]:
        return is_possible('0' + string)

    root_node = len(string)//2

    # 루트 노드가 더미 노드(0)인 경우
    if string[root_node] == '0':
        # 자식들도 모두 더미(0)인 경우는 True
        if int(string) == 0:
            return True
        # 자식 중 더미 노드가 아닌 노드(1)가 있으면 False
        else:
            return False

    # 루트 노드가 더미가 아니고, 더 나눌 수 없는 경우는 True
    if len(string) == 1 or len(string) == 3:
        return True      

    # 왼쪽 서브트리와 오른쪽 서브트리에 대해 검사한 결과를 합친다.
    return is_possible(string[0:root_node]) and is_possible(string[root_node + 1:])


def solution(numbers):
    answer = []    
        
    for number in numbers:
        binary_number_string = str(format(number,'b'))
        if is_possible(binary_number_string):
            answer.append(1)
        else:            
            answer.append(0)
    return answer