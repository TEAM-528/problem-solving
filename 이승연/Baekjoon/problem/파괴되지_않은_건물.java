package Baekjoon.problem;

public class 파괴되지_않은_건물 {
    class Solution {
        public int solution(int[][] board, int[][] skill) {
            int answer = 0;
            int[][] sum = new int[board.length+1][board[0].length+1];
            for(int i=0; i<skill.length; i++) {
                if (skill[i][0]==1) {
                    skill[i][5] *= -1;
                }
                sum[skill[i][1]][skill[i][2]] += skill[i][5];
                sum[skill[i][1]][skill[i][4]+1] -= skill[i][5];
                sum[skill[i][3]+1][skill[i][2]] -= skill[i][5];
                sum[skill[i][3]+1][skill[i][4]+1] += skill[i][5];
            }

            for(int i=0; i<sum.length; i++) {
                for(int j=1; j<sum[0].length; j++) {
                    sum[i][j] += sum[i][j-1];
                }
            }
            for(int i=0; i<sum[0].length; i++) {
                for(int j=1; j<sum.length; j++) {
                    sum[j][i] += sum[j-1][i];
                }
            }

            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[0].length; j++) {
                    if (board[i][j]+sum[i][j]>0) {
                        answer++;
                    }
                }
            }
            return answer;
        }
    }
}
