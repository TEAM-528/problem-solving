import java.util.*;

public class 디스크_컨트롤러 {
    class Solution {
        PriorityQueue<int[]> possibleJob = new PriorityQueue<>((int[] o1, int[] o2) -> {
            return o1[1]-o2[1];
        });

        public int solution(int[][] jobs) {
            int answer = 0;

            Arrays.sort(jobs, (int[] o1, int[] o2) -> {
                if (o1[0]==o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            });

            int timer = 0;
            int sum = 0;
            int idx = 0;
            timer = jobs[idx][0]; // 무조건 0ms부터 시작되는 게 아님. 가장 처음이 3ms 일수도
            possibleJob.add(jobs[idx++]);
            while(!possibleJob.isEmpty()) {
                int[] job = possibleJob.poll();
                timer += job[1];
                sum += (timer-job[0]);

                if (idx<jobs.length) {
                    if (timer>jobs[idx][0]) {
                        for(int i=idx; i<jobs.length; i++) {
                            if (timer<jobs[i][0]) break;
                            possibleJob.add(jobs[idx++]);
                        }
                    } else {
                        if (possibleJob.isEmpty()) {
                            timer = jobs[idx][0];
                            possibleJob.add(jobs[idx++]);
                        }
                    }

                }
            }


            answer = sum/jobs.length;
            return answer;
        }
    }
}