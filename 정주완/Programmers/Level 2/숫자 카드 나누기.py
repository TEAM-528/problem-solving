# 24.03.21 30m

def solution(arrayA, arrayB):
    answer = 0
    pos_a = [arrayA[0]]
    pos_b = [arrayB[0]]
    
    for i in range(2,int(arrayA[0]//2)+1):
        if arrayA[0]%i==0:
            pos_a.append(i)
    
    for i in range(2,int(arrayB[0]//2)+1):
        if arrayB[0]%i==0:
            pos_b.append(i)
            
    for a in pos_a:
        if a in pos_b:
            pos_a.remove(a)
            pos_b.remove(a)            
            
    for i in range(1,len(arrayA)):
        for j in range(len(pos_a) - 1, -1, -1):
            a = pos_a[j]
            if arrayA[i]%a != 0:
                pos_a.remove(a)
            elif arrayB[i]%a == 0:
                pos_a.remove(a)
        for j in range(len(pos_b) - 1, -1, -1):
            b = pos_b[j]
            if arrayB[i]%b != 0:
                pos_b.remove(b)
            elif arrayA[i]%b == 0:
                pos_b.remove(b)                
        
    pos_a.append(0)
    pos_b.append(0)
    answer = max(max(pos_a),max(pos_b))
    
    return answer