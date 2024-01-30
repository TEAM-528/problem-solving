import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class 최단경로 {
    public static int V, E;
    public static HashMap<Integer, List<int[]>> graph = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        V = Integer.parseInt(info[0]);
        E = Integer.parseInt(info[1]);
        int K = Integer.parseInt(br.readLine());

        for (int i=0; i<E; i++) {
            info = br.readLine().split(" ");
            int u = Integer.parseInt(info[0]);
            int v = Integer.parseInt(info[1]);
            int w = Integer.parseInt(info[2]);

            List<int[]> g = graph.getOrDefault(u, new ArrayList<>());
            g.add(new int[]{v, w});
            graph.put(u, g);
        }

        dijkstra(K);
    }

    public static void dijkstra(int k) {
        int[] distance = new int[V+1];
        for(int i=0; i<distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[V+1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> {
            return o1[1] - o2[1];
        });

        distance[k] = 0;
        pq.add(new int[]{k, 0});

        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            visited[p[0]] = true;

            List<int[]> next = graph.get(p[0]);
            if (next == null) continue;
            for(int i=0; i<next.size(); i++) {
                int[] n = next.get(i);
                if (!visited[n[0]] && (distance[n[0]] > p[1]+n[1])) {
                    distance[n[0]] = p[1]+n[1];
                    pq.add(new int[]{n[0], p[1]+n[1]});
                }
            }
        }

        for(int i=1; i<distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(distance[i]);
        }
    }
}
