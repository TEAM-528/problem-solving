public class 입국심사 {
    class Solution {
        public long solution(int n, int[] times) {
            Arrays.sort(times);
            long left = 1;
            long right = (long)n*times[times.length-1];
            long answer = right;

            while(left<=right) {
                long middle = (left+right)/2;
                if (check(middle, n, times)) {
                    right = middle-1;
                    answer = Math.min(middle, answer);
                    continue;
                }
                left = middle+1;
            }
            return answer;
        }

        public boolean check(long time, long n, int[] times) {
            long count = 0;

            for(int i=0; i<times.length; i++) {
                count += time/times[i];
            }

            if (count>=n) return true;
            return false;
        }
    }
}