public class 단속카메라 {
    import java.util.*;

    class Solution {
        public int solution(int[][] routes) {
            int answer = 0;

            Arrays.sort(routes, (int[] o1, int[] o2) -> {
                if (o1[0] == o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            });
            int start = routes[0][0];
            int end = routes[0][1];
            answer++;

            for(int i=1; i<routes.length; i++) {
                if (end < routes[i][0]) {
                    answer++;
                    start = routes[i][0];
                    end = routes[i][1];
                }
                if (start > routes[i][0]) {
                    start = routes[i][0];
                }
                if (end > routes[i][1]) {
                    end = routes[i][1];
                }
            }
            return answer;
        }
    }
}