# 24.03.22 50m

# 해당 블록 위에 다른 블록이 있는지 확인 (검은 블록이 내려올 수 있게)
def is_not_zero_upper(board,i,j):
    while(i>0):
        i-=1
        if board[i][j]!=0:
            return False
    return True

# 해당 블록의 맨 위 블럭을 기준으로, 블럭 모양을 확인 후 없앨 수 있는 블럭인지 파악
def find_pos_black_block(board,i,j):
    block_num = board[i][j]
    
    pos_b1 = i+1<len(board) and j-2>=0 and board[i+1][j-2]==block_num and is_not_zero_upper(board,i+1,j-2)
    pos_b2 = i+1<len(board) and j-1>=0 and board[i+1][j-1]==block_num and is_not_zero_upper(board,i+1,j-1)
    pos_b3 = i+1<len(board) and board[i+1][j]==block_num
    pos_b4 = i+1<len(board) and j+1<len(board[0]) and board[i+1][j+1]==block_num and is_not_zero_upper(board,i+1,j+1)
    pos_b5 = i+1<len(board) and j+2<len(board[0]) and board[i+1][j+2]==block_num and is_not_zero_upper(board,i+1,j+2)
    
    pos_b6 = i+2<len(board) and j-1>=0 and board[i+2][j-1]==block_num and is_not_zero_upper(board,i+2,j-1)
    pos_b7 = i+2<len(board) and board[i+2][j]==block_num
    pos_b8 = i+2<len(board) and j+1<len(board[0]) and board[i+2][j+1]==block_num and is_not_zero_upper(board,i+2,j+1)
    
    # 없앨 수 있는 블럭이면 블럭을 0으로 비운 후, 1 반환
    if (pos_b1 and pos_b2 and pos_b3):
        board[i][j] = 0
        board[i+1][j-2] = 0
        board[i+1][j-1] = 0
        board[i+1][j] = 0
        return 1        
    elif (pos_b2 and pos_b3 and pos_b4):
        board[i][j] = 0
        board[i+1][j-1] = 0
        board[i+1][j] = 0
        board[i+1][j+1] = 0
        return 1    
    elif (pos_b3 and pos_b4 and pos_b5):
        board[i][j] = 0
        board[i+1][j] = 0
        board[i+1][j+1] = 0
        board[i+1][j+2] = 0
        return 1    
    elif (pos_b3 and pos_b6 and pos_b7):
        board[i][j] = 0
        board[i+1][j] = 0
        board[i+2][j] = 0
        board[i+2][j-1] = 0
        return 1
    elif (pos_b3 and pos_b7 and pos_b8):
        board[i][j] = 0
        board[i+1][j] = 0
        board[i+2][j] = 0
        board[i+2][j+1] = 0
        return 1
    else:
        return 0
    
'''
가능한 블록
  0  0  0
000 000 000

 0 0
 0 0
00 00

  0
12345
 678
'''    

def solution(board):
    answer = 0
    tmp_answer = 1
    
    # 더이상 없앨 수 있는 블럭이 없을 때까지 반복 (다른 블럭을 없애서 새로 파괴할 수 있는 블럭이 생기기도 함)
    while tmp_answer > 0:
        tmp_answer = 0
        for i in range(len(board)):
            for j in range(len(board[0])):
                # 해당 블럭이 없앨 수 있는 블럭이면 정답 1증가
                if board[i][j]>0:
                    tmp_answer += find_pos_black_block(board,i,j)
        answer += tmp_answer
          
    return answer