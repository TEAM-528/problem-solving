import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (int[] o1, int[] o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] == o2[0] & o1[1] > o2[1]) {
                return 1;
            }
            return -1;
        });
        
        int start = targets[0][0];
        int end = targets[0][1];
        answer = 1;
        for(int i=1; i<targets.length; i++) {
            if (targets[i][0] < end) {
                start = targets[i][0];
                if (targets[i][1] < end) {
                    end = targets[i][1];
                }
            } else {
                answer++;
                start = targets[i][0];
                end = targets[i][1];
            }
        }
         
        return answer;
    }
}
