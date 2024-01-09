import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int size = (int)(end-begin)+1;
        int[] answer = new int[size];
        // 1이면 0
        // 소수면 1
        // 소수 아니라면 약수 중에 제일 큰거
        
        for(int i=0; i<answer.length; i++) {
            long idx = begin+i;
            answer[i] = calNumber(idx);
        }
        return answer;
    }
    
    public int calNumber(long num) {
        if (num == 1) {
            return 0;
        }
        int value = 1;
        for(int i=2; i<=Math.sqrt(num); i++) {
            if (num%i == 0) {
                if ((int)(num/i)<=10000000 & value<(int)(num/i)) {
                    value = (int)(num/i);
                } else {
                    if (value < i) {
                        value = i;   
                    }
                }
            }
        }
        return value;
    }
}
