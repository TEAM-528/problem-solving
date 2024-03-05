class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] score = new int[n+1][n+1];
        for(int i=0; i<results.length; i++) {
            score[results[i][0]][results[i][1]] = 1;
            score[results[i][1]][results[i][0]] = -1;
        }

        for(int i=1; i<score.length; i++) {
            for(int j=1; j<score.length; j++) {
                for(int k=1; k<score.length; k++) {
                    if (score[i][j] == 1 && score[j][k] == 1) {
                        score[i][k] = 1;
                        score[k][i] = -1;
                    }
                    if (score[i][j] == -1 && score[j][k] == -1) {
                        score[i][k] = -1;
                        score[k][i] = 1;
                    }
                }
            }
        }

        for(int i=1; i<score.length; i++) {
            int count = 0;
            for(int j=1; j<score.length; j++) {
                if (score[i][j] == 0) {
                    count++;
                }
            }
            if (count == 1) answer++;
        }
        return answer;
    }
}