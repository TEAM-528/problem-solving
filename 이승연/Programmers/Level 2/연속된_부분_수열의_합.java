public class 연속된_부분_수열의_합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int L = 0;
            int R = 0;
            int sum = sequence[0];
            int[] answer = new int[]{0, 1000000};
            while (L<sequence.length && R<sequence.length && L<=R) {
                if (sum==k) {
                    if (R-L<answer[1]-answer[0]) {
                        answer[0] = L;
                        answer[1] = R;
                        sum -= sequence[L];
                        L++;
                    } else if (R-L==answer[1]-answer[0]) {
                        sum -= sequence[L];
                        L++;
                    }
                } else if (sum<k) {
                    R++;
                    if (R>=sequence.length) break;
                    sum += sequence[R];
                } else if (sum>k) {
                    sum -= sequence[L];
                    L++;
                }
            }

            return answer;
        }
    }
}
