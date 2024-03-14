public class 숫자_게임 {
    import java.util.*;

    class Solution {
        public int solution(int[] A, int[] B) {
            int answer = 0;

            PriorityQueue<Integer> pqA = new PriorityQueue<>((Integer o1, Integer o2) -> {
                return o2-o1;
            });
            PriorityQueue<Integer> pqB = new PriorityQueue<>((Integer o1, Integer o2) -> {
                return o2-o1;
            });
            PriorityQueue<Integer> pqBmin = new PriorityQueue<>();
            for(int i=0; i<A.length; i++) {
                pqA.add(A[i]);
                pqB.add(B[i]);
                pqBmin.add(B[i]);
            }

            while(!pqA.isEmpty()) {
                int a = pqA.poll();
                int b = pqB.poll();
                if (a < b) {
                    answer++;
                    continue;
                }
                pqB.add(b);
                pqB.remove(pqBmin.poll());
            }
            return answer;
        }
    }
}