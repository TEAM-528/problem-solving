class Solution {
    public int solution(String[] board) {
        int answer = -1;
        char[][] cBoard = new char[3][3];
        for(int i=0; i<board.length; i++) {
            cBoard[i] = board[i].toCharArray();
        }
        
        int numO = 0;
        int numX = 0;
        for(char[] c : cBoard) {
            for(char cc : c) {
                if (cc == 'O') {
                    numO++;
                } else if (cc == 'X') {
                    numX++;
                }
            }
        }
        
        if (!(numO==numX || numO==numX+1)) {
            return 0;
        }
        
        if (numO==numX && checkWin(cBoard, 'O')) { // O 만으로 이길 수 있었는지 확인
            return 0;
        }
        
        if (numO==numX+1 && checkWin(cBoard, 'X')) {
            return 0;
        }
        return 1;
    }
    
    public boolean checkWin(char[][] board, char order) {
        for(int i=0; i<3; i++) {
            if (board[i][0]==order && board[i][1]==order && board[i][2]==order) {
                return true;
            }
        }
        
        for(int i=0; i<3; i++) {
            if (board[0][i]==order && board[1][i]==order && board[2][i]==order) {
                return true;
            }
        }
        
        if (board[0][0]==order && board[1][1]==order && board[2][2]==order) {
            return true;
        }
        
        if (board[2][0]==order && board[1][1]==order && board[0][2]==order) {
            return true;
        }
        return false;
    }
}
