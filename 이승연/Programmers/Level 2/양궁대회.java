class Solution {
    public int[] INFO;
    public int GAP = -1;
    public int[] ANS = new int[11];
    public int[] solution(int n, int[] info) {
        INFO = info;
        back(new int[11], 0, n);
        if (GAP == -1) {
            return new int[]{-1};
        }
        return ANS;
    }
    
    public void back(int[] answer, int target, int n) {
        if (n == 0) { // 더 이상 화살 없음
            int gap = calGap(answer);
            
            if (gap <= 0) {
                return;
            }
            
            if (gap > GAP) {
                GAP = gap;
                ANS = answer;
            } else if (gap == GAP) {
                for(int i=0; i<answer.length; i++) {
                    if (answer[10-i]<ANS[10-i]) {
                        break;
                    }
                    if (answer[10-i]!=0&&answer[10-i]==ANS[10-i]) {
                        break;
                    }
                    if (answer[10-i] > ANS[10-i]) {
                        ANS = answer;
                        return;
                    }
                }
            }
            return;
        }
        
        if (target == 11) {
            return;
        }
        
        for(int i=0; i<=n; i++) {
            if (INFO[target]+1 < i || n-i<0) {
                break;
            }
            
            int[] newAnswer = new int[11];
            for(int j=0; j<answer.length; j++) {
                newAnswer[j] = answer[j];
            }
            newAnswer[target] = i;
            back(newAnswer, target+1, n-i);
        }
    }
    
    public int calGap(int[] score) {
        int lion = 0;
        int apeach = 0;
        for(int i=0; i<score.length; i++) {
            if (score[i]==0 && INFO[i]==0) {
                continue;
            }
            if (score[i] > INFO[i]) {
                lion += (10-i); 
            } else {
                apeach += (10-i);
            }
        }
        
        return lion - apeach;
    }
}
