from itertools import combinations
from itertools import product
from bisect import bisect_left

def solution(dice):
    answer = []    
    dice_cnt = len(dice)
    
    # 주사위 선택 조합 저장
    dice_combinations = list(combinations(range(dice_cnt), dice_cnt//2))
    # 주사위 굴려서 나오는 경우 (중복 순열) 저장
    dice_roll_results = list(product(range(6), repeat=dice_cnt//2))
    
    # 조합별로 굴려서 나온 결과의 합 저장
    score_arrays = []    
    for selected_dice in dice_combinations:
        dice_scores = []
        for roll_result in dice_roll_results:
            total_score = 1
            for i in range(dice_cnt//2):
                total_score += dice[selected_dice[i]][roll_result[i]]
            dice_scores.append(total_score)
        dice_scores.sort()
        score_arrays.append(dice_scores)
    
    # 조합별로 승리하는 횟수 저장
    win_count = [-1 for _ in range(len(dice_combinations))]
    
    # 조합별 승리 횟수 계산
    for k in range(len(dice_combinations)):  
        if win_count[k] != -1:
            continue
        
        # b는 전체 주사위에서 a를 제외한 나머지
        a_dice = dice_combinations[k]
        b_dice = []
        for i in range(dice_cnt):
            if i not in a_dice:
                b_dice.append(i)
        b_dice = tuple(b_dice)
        
        a_index = dice_combinations.index(a_dice)
        b_index = dice_combinations.index(b_dice)
        
        # a의 결과를 이진탐색으로 b 결과 배열과 비교 
        # b결과 배열에서 a결과보다 작은 값의 개수가 승리 횟수         
        a_wins = sum(bisect_left(score_arrays[b_index], score) for score in score_arrays[a_index])
        b_wins = sum(bisect_left(score_arrays[a_index], score) for score in score_arrays[b_index])
        
        win_count[a_index] = a_wins
        win_count[b_index] = b_wins
        
    for dice in dice_combinations[win_count.index(max(win_count))]:
        answer.append(dice + 1)
    
    return answer