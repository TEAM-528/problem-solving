import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 특정한_최단_경로 {
    public static int N, E;
    public static HashMap<Integer, ArrayList<int[]>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ne = br.readLine().split(" ");
        N = Integer.parseInt(ne[0]);
        E = Integer.parseInt(ne[1]);
        graph = new HashMap<>();

        for(int i=0; i<E; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);

            
            ArrayList<int[]> list = graph.getOrDefault(a, new ArrayList<>());
            list.add(new int[]{b, c});
            graph.put(a, list);

            list = graph.getOrDefault(b, new ArrayList<>());
            list.add(new int[]{a, c});
            graph.put(b, list);
        }

        String[] v1v2 = br.readLine().split(" ");
        Integer V1 = Integer.parseInt(v1v2[0]);
        Integer V2 = Integer.parseInt(v1v2[1]);

        int middle = dijkstra(V1, V2);
        if (middle == -1) {
            System.out.println(-1);
            return;
        }
        
        int a1 = dijkstra(1, V1); 
        int a2 = dijkstra(V2, N);

        int b1 = dijkstra(1, V2); 
        int b2 = dijkstra(V1, N);
        
        if ((a1 == -1 || a2 == -1) && (b1 != -1 && b2 != -1)) {
            System.out.println(middle + b1 + b2);
            return;
        }

        if ((a1 != -1 && a2 != -1) && (b1 == -1 || b2 == -1)) {
            System.out.println(middle + a1 + a2);
            return;
        }

        if ((a1 == -1 || a2 == -1) && (b1 == -1 || b2 == -1)) {
            System.out.println(-1);
            return;
        }

        System.out.println(middle + Math.min(a1+a2, b1+b2));
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        boolean[] visited = new boolean[N+1];

        queue.add(new int[]{start, 0});
        int[] distance = new int[N+1];
        for(int i=1; i<N+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        visited[start] = true;

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            visited[p[0]] = true;

            ArrayList<int[]> nexts = graph.get(p[0]);
            if (nexts == null) continue;
            
            for(int i=0; i<nexts.size(); i++) {
                int[] n = nexts.get(i);
                if (distance[n[0]] > p[1]+n[1] && !visited[n[0]]) {
                    distance[n[0]] = p[1]+n[1];
                    queue.add(new int[]{n[0], p[1]+n[1]});
                }
            }
        }

        if (distance[end] == Integer.MAX_VALUE) return -1;
        return distance[end];
    }
}