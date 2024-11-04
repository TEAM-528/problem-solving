# 30m

def solution(coin, cards):
    n = len(cards)
    cur_idx = n // 3

    origin_cards = set(cards[:cur_idx])
    new_cards = set()

    answer = 1
    while cur_idx + 2 <= n:
        # 카드 두장 뽑기
        for i in range(cur_idx, cur_idx + 2):
            new_cards.add(cards[i])
        cur_idx += 2

        # 카드 두 장으로 n+1 만들기
        # origin_cards로 가능한지 확인
        is_possible = False
        
        # 가장 먼저, 코인을 사용하지 않는 경우 확인
        for i in range(n + 1):
            if i in origin_cards and (n + 1) - i in origin_cards:
                origin_cards.remove(i)
                origin_cards.remove((n + 1) - i)
                is_possible = True
                break
        # 코인 사용 없이 불가능하면 코인 하나만 사용하는 경우 확인
        if not is_possible and coin >= 1:
            for i in range(n + 1):
                if i in origin_cards and (n + 1) - i in new_cards:
                    origin_cards.remove(i)
                    new_cards.remove((n + 1) - i)
                    is_possible = True
                    coin -= 1
                    break
                elif i in new_cards and (n + 1) - i in origin_cards:
                    new_cards.remove(i)
                    origin_cards.remove((n + 1) - i)
                    is_possible = True
                    coin -= 1
                    break
        # 코인 두개를 사용하는 경우 확인
        if not is_possible and coin >= 2:
            for i in range(n + 1):
                if i in new_cards and (n + 1) - i in new_cards:
                    new_cards.remove(i)
                    new_cards.remove((n + 1) - i)
                    is_possible = True
                    coin -= 2
                    break

        if not is_possible:
            break

        answer += 1

    return answer
