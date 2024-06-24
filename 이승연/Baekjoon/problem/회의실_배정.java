package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 회의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[][] meeting = new int[n][2];
        for(int i=0; i<n; i++) {
            String[] ab = br.readLine().split(" ");
            meeting[i][0] = Integer.parseInt(ab[0]);
            meeting[i][1] = Integer.parseInt(ab[1]);
        }

        Arrays.sort(meeting, (o1, o2) -> {
            if (o1[1]==o2[1]) {
                return o1[0]-o2[0];
            }
            return o1[1]-o2[1];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for(int i=0; i<meeting.length; i++) {
            // 들어갈 수 있는 회의실 중에 가장 늦게 비워지는 걸 해야 함
            PriorityQueue<Integer> possible = new PriorityQueue<>((o1, o2) -> {
                return o2-o1;
            });

            // pq 돌면서 들어갈 수 있는 회의실은 언제부터 비는지 구하기
            while (!pq.isEmpty() && pq.peek()<meeting[i][0]) {
                possible.add(pq.poll());
            }

            // 만약 들어갈 수 있는 회의실이 있다면
            if (!possible.isEmpty()) {
                answer++;
                pq.remove(possible.poll()); // 가장 늦게 비는 애를 pq에서 지워 줌
                while(!possible.isEmpty()) { // 나머지는 다시 pq로 이동
                    pq.add(possible.poll());
                }
                pq.add(meeting[i][1]);
                continue;
            }
            // 누가 쓰고 있는 회의실을 쓸 수 없는데 그냥 비어있던 곳은 있었다면
            if (pq.size() < k) {
                answer++;
                pq.add(meeting[i][1]);
            }
        }
        System.out.println(answer);
    }
}
