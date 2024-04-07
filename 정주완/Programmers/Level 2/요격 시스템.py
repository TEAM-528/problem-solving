# 24.03.06 어렵다. 복습 필요

def solution(targets):
    answer = 0

    targets.sort()

    x = 0
    for target in targets:
        if x > target[0]:
            x = min(x,target[1]-0.1)
        else:
            x = target[1]-0.1
            answer += 1

    return answer