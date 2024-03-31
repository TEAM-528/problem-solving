public class 징검다리_건너기 {
    class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;

            int start = 1;
            int end = 200000000;

            while(start<=end) {
                int middle = (start+end)/2;
                if (check(middle, stones, k)) {
                    answer = Math.max(middle, answer);
                    start = middle+1;
                } else {
                    end = middle-1;
                }
            }

            return answer;
        }

        public boolean check(int friends, int[] stones, int k) {
            int step = 0;
            for(int i=0; i<stones.length; i++) {
                if (stones[i]<friends) {
                    step++;
                } else {
                    step = 0;
                }

                if (step==k) return false;
            }

            return true;
        }
    }
}