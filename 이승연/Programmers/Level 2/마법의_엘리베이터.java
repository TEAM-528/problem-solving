import java.util.*;
public class 마법의_엘리베이터 {
    class Solution {
        public int solution(int storey) {
            int answer = 0;
            int digit = 10;
            while(storey>0) {
                int n = storey%digit;
                storey /= digit;
                if (n>=0 && n<5) {
                    answer += n;
                }
                if (n>5) {
                    answer += (10-n);
                    storey++;
                }
                if (n==5) {
                    int next = storey%10;
                    if (next >= 5) {
                        answer += (10-n);
                        storey++;
                    } else {
                        answer += n;
                    }
                }
            }
            return answer;
        }
    }
}
