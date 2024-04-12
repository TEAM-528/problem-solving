public class 연속_펄스_부분_수열의_합 {
    class Solution {
        public long solution(int[] sequence) {
            long answer1 = 0;
            long answer2 = 0;
            long answer = 0;
            for(int i=0; i<sequence.length; i++) {
                if (i%2 == 0) {
                    answer1 += sequence[i];
                    answer2 -= sequence[i];
                } else {
                    answer1 -= sequence[i];
                    answer2 += sequence[i];
                }

                answer = Math.max(answer1, answer);
                answer = Math.max(answer2, answer);

                if (answer1<0) {
                    answer1 = 0;
                }
                if (answer2<0) {
                    answer2 = 0;
                }
            }

            return answer;
        }
    }
}