def solution(m, n, startX, startY, balls):
    answer = []
    for ball in balls:
        x , y = ball
        
        route1 = abs(x-startX)**2 + (y+startY)**2           # 하
        route2 = abs(x-startX)**2 + ((n-y)+(n-startY))**2   # 상
        route3 = abs(y-startY)**2 + (x+startX)**2           # 좌
        route4 = abs(y-startY)**2 + ((m-x)+(m-startX))**2   # 우
        
        if x==startX:
            if y>startY:
                answer.append(min(route1,route3,route4))
            else: 
                answer.append(min(route2,route3,route4))
        elif y==startY:
            if x>startX:
                answer.append(min(route1,route2,route3))
            else: 
                answer.append(min(route1,route2,route4))
        else:            
            answer.append(min(route1,route2,route3,route4))
    return answer