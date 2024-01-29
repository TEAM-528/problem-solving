import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class 골목_대장_호석_효율성2 {
    public static int N, M, A, B;
    public static long C;
    public static HashMap<Integer, List<long[]>> graph = new HashMap<>();    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        A = Integer.parseInt(info[2]);
        B = Integer.parseInt(info[3]);
        C = Long.parseLong(info[4]);

        long answer = -1;
        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int from = Integer.parseInt(info[0]);
            int to = Integer.parseInt(info[1]);
            long fee = Long.parseLong(info[2]);

            List<long[]> con = graph.getOrDefault(from, new ArrayList<>());
            con.add(new long[]{to, fee});
            graph.put(from, con);

            con = graph.getOrDefault(to, new ArrayList<>());
            con.add(new long[]{from, fee});
            graph.put(to, con);

            answer = Math.max(answer, fee);
        }

        long start = 1;
        long end = answer;
        long mid = (long)Math.ceil((start+end)/2);
        answer = -1;
        while(start <= end) {
            if (dijkstra(mid)) {
                end = mid-1;
                answer = mid;
                mid = (long)Math.ceil((start+end)/2);
                continue;
            }
            start = mid+1;
            mid = (long)Math.ceil((start+end)/2);
        }

        System.out.println(answer);
    }   

    public static boolean dijkstra(long cost) {
        long[] distance = new long[N+1];
        for(int i=0; i<distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {
            if (distance[o1] > distance[o2]) return -1;
            return 1;
        });
        boolean[] visited = new boolean[N+1];

        pq.add(A);
        distance[A] = 0;
        visited[A] = true;
        
        while(!pq.isEmpty()) {
            int n = pq.poll();
            if (n == B) return true;
            visited[n] = true;

            List<long[]> possibleNext = graph.getOrDefault(n, new ArrayList<>());
            for(int i=0; i<possibleNext.size(); i++) {
                long[] next = possibleNext.get(i);
                if (distance[n]+next[1] > C) {
                    continue;
                }
                if (distance[(int)next[0]] > distance[n]+next[1] && next[1] <= cost && !visited[(int)next[0]]) {
                    distance[(int)next[0]] = distance[n]+next[1];
                    pq.add((int)next[0]);
                }
            
            }
        }

        if (distance[B] == Integer.MAX_VALUE) return false;
        return true;
        //if (distance[B] <= C) return true;
        //return false;
    }
}
