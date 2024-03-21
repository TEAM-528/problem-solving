public class 덧칠하기 {
    class Solution {
        public int solution(int n, int m, int[] section) {
            int answer = 0;
            int idx = section[0]+m-1;
            answer = 1;
            for(int i=1; i<section.length; i++) {
                if (section[i] <= idx) {
                    continue;
                }
                idx = section[i]+m-1;
                answer++;
            }
            return answer;
        }
    }
}