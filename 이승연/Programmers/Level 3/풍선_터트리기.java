public class 풍선_터트리기 {
    class Solution {
        public int solution(int[] a) {
            int answer = 0;

            int[] left = new int[a.length];
            int[] right = new int[a.length];

            left[0] = Integer.MAX_VALUE;
            for(int i=1; i<a.length; i++) {
                left[i] = Math.min(left[i-1], a[i-1]);
            }

            right[a.length-1] = Integer.MAX_VALUE;
            for(int i=a.length-1; i>0; i--) {
                right[i-1] = Math.min(right[i], a[i]);
            }

            if (a.length == 1) return 1;

            answer = 2;
            for(int i=1; i<a.length-1; i++) {
                if (left[i]<a[i] && right[i]<a[i]) continue;
                answer++;
            }

            return answer;
        }
    }
}