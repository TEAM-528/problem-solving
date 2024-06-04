package Baekjoon.problem;
import java.util.*;

public class 합승_택시_요금 {
    class Solution {
        public ArrayList<ArrayList<int[]>> graph;
        public int N;
        public int answer = Integer.MAX_VALUE;
        public int solution(int n, int s, int a, int b, int[][] fares) {
            graph = new ArrayList<>();
            for(int i = 0 ; i <= n ; i ++){
                graph.add(new ArrayList<>());
            }

            for(int[] i : fares){
                graph.get(i[0]).add(new int[]{i[1], i[2]});
                graph.get(i[1]).add(new int[]{i[0], i[2]});
            }


            N = n;
            int[] toMiddle = dijkstra(s);
            int[] toA = dijkstra(a);
            int[] toB = dijkstra(b);
            for(int i=1; i<=n; i++) {
                answer = Math.min(toMiddle[i]+toA[i]+toB[i], answer);
            }
            return answer;
        }

        public int[] dijkstra(int start) {
            int[] distance = new int[N+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[start] = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>((Integer o1, Integer o2) -> {
                return distance[o1]-distance[o2];
            });
            queue.add(start);

            while(!queue.isEmpty()) {
                int node = queue.poll();
                ArrayList<int[]> edges = graph.get(node);

                for(int i=0; i<edges.size(); i++) {
                    int[] e = edges.get(i);
                    if (distance[node]+e[1] < distance[e[0]]) {
                        queue.add(e[0]);
                        distance[e[0]] = distance[node]+e[1];
                    }
                }
            }

            return distance;
        }
    }
}
