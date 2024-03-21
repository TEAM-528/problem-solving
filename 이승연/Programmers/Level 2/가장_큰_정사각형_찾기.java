public class 가장_큰_정사각형_찾기 {
    class Solution {
        public int solution(int [][]board) {
            int answer = Integer.MIN_VALUE;
            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[0].length; j++) {
                    if (board[i][j]!=0 && i>=1 && j>=1) {
                        board[i][j] = Math.min(board[i-1][j], board[i][j-1]);
                        board[i][j] = Math.min(board[i][j], board[i-1][j-1]);
                        board[i][j]++;
                    }
                }
            }

            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[0].length; j++) {
                    answer = Math.max(answer, board[i][j]);
                }
            }
            return answer*answer;
        }


    }
}
