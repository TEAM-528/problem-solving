public class 최고의_집합 {
    class Solution {
        public int[] solution(int n, int s) {
            int[] answer = new int[n];
            if (n > s) return new int[]{-1};
            int r = s%n;
            for(int i=0; i<answer.length; i++) {
                answer[i] = s/n;
            }

            for(int i=0; i<r; i++) {
                answer[answer.length-1-i] += 1;
            }
            return answer;
        }
    }
}