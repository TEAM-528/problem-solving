import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {return o2-o1;});

        for(int i=0; i<works.length; i++) {
            pq.add(works[i]);
        }

        while (n>0) {
            if (pq.isEmpty()) return 0;
            int p = pq.poll();
            if (p-1>0) {
                pq.add(p-1);
            }
            n--;
        }

        while(!pq.isEmpty()) {
            int p = pq.poll();
            answer += (long)p*(long)p;
        }

        return answer;
    }
}
public class 야근_지수 {
    class Solution {
        public long solution(int n, int[] works) {
            long answer = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {return o2-o1;});

            for(int i=0; i<works.length; i++) {
                pq.add(works[i]);
            }

            while (n>0) {
                if (pq.isEmpty()) return 0;
                int p = pq.poll();
                if (p-1>0) {
                    pq.add(p-1);
                }
                n--;
            }

            while(!pq.isEmpty()) {
                int p = pq.poll();
                answer += (long)p*(long)p;
            }

            return answer;
        }
    }
}