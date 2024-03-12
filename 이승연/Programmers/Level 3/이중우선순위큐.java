public class 이중우선순위큐 {
    import java.util.*;

    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = {};
            PriorityQueue<Integer> pqMax = new PriorityQueue<>((Integer o1, Integer o2) -> {
                return o2-o1;
            });
            PriorityQueue<Integer> pqMin = new PriorityQueue<>((Integer o1, Integer o2) -> {
                return o1-o2;
            });

            for(int i=0; i<operations.length; i++) {
                String[] info = operations[i].split(" ");
                if (operations[i].startsWith("I")) {
                    pqMax.add(Integer.parseInt(info[1]));
                    pqMin.add(Integer.parseInt(info[1]));
                    continue;
                }

                if (!pqMax.isEmpty() && info[1].equals("1")) {
                    int n = pqMax.poll();
                    pqMin.remove(n);
                } else if (!pqMin.isEmpty() && info[1].equals("-1")) {
                    int n = pqMin.poll();
                    pqMax.remove(n);
                }
            }

            answer = new int[2];
            if (!pqMax.isEmpty() && !pqMin.isEmpty()) {
                answer[0] = pqMax.poll();
                answer[1] = pqMin.poll();
            }
            return answer;
        }
    }
}