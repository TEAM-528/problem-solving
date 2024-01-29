import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class 골목_대장_호석_효율성1 {
    public static int N, M, A, B, C;
    public static HashMap<Integer, List<int[]>> graph = new HashMap<>();    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        A = Integer.parseInt(info[2]);
        B = Integer.parseInt(info[3]);
        C = Integer.parseInt(info[4]);

    }

    public static boolean dijkstra(int cost) {
        int[] distance = new int[N+1];
        for(int i=0; i<distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {
            return distance[o1]-distance[o2];   
        });

        pq.add(A);
        distance[A] = 0;
        
        while(!pq.isEmpty()) {
            Integer n = pq.poll();

            List<int[]> possibleNext = graph.getOrDefault(n, new LinkedList<>());
            for(int i=0; i<possibleNext.size(); i++) {
                int[] next = possibleNext.get(i);
                if (distance[n]+next[1] > C) {
                    continue;
                }
                if (distance[next[0]] > distance[n]+next[1] && next[1] <= cost) {
                    distance[next[0]] = distance[n]+next[1];
                    pq.add(next[0]);
                }
            
            }
        }

        if (distance[B] == Integer.MAX_VALUE) return false;
        return true;
    }
}
