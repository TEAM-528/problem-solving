public class 택배_배달과_수거하기 {
    import java.util.*;

    class Solution {
        Stack<Integer> go;
        Stack<Integer> comeback;
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0;
            go = new Stack<>();
            comeback = new Stack<>();

            for(int i=0; i<n; i++) {
                for(int j=0; j<deliveries[i]; j++) {
                    go.push(i+1);
                }
            }
            for(int i=0; i<n; i++) {
                for(int j=0; j<pickups[i]; j++) {
                    comeback.push(i+1);
                }
            }

            while(!go.isEmpty() || !comeback.isEmpty()) {
                int distance = 0;
                if (!go.isEmpty()) distance = Math.max(distance, go.peek());
                if (!comeback.isEmpty()) distance = Math.max(distance, comeback.peek());
                answer += (long) distance;

                for(int i=0; i<cap; i++) {
                    if (!go.isEmpty()) go.pop();
                    if (!comeback.isEmpty()) comeback.pop();
                }
            }

            //if (!go.isEmpty()) answer += (long) go.pop();
            //if (!comeback.isEmpty()) answer += (long) comeback.pop();
            return answer*2;
        }
    }
}