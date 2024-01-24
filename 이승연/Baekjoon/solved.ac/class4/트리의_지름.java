import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class 트리의_지름{
    public static boolean[] visited;
    public static int distance = Integer.MIN_VALUE;
    public static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
    public static int maxNode = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        visited = new boolean[V+1];

        for(int i=0; i<V; i++) {
            String[] info = br.readLine().split(" ");
            int from = Integer.parseInt(info[0]);
            for(int j=1; j<info.length; j++) {
                if (Integer.parseInt(info[j]) == -1) break;
                int to = Integer.parseInt(info[j]);
                int distance = Integer.parseInt(info[j+1]);
                ArrayList<int[]> vList = graph.getOrDefault(from, new ArrayList<>());
                vList.add(new int[]{to, distance});
                graph.put(from, vList);
                j++;
            }
        }

        
        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;
        
        visited[maxNode] = true;
        distance = Integer.MIN_VALUE;
        dfs(maxNode, 0);
        System.out.println(distance);
    }

    public static void dfs (int v, int d) {
        if (d>distance) {
            distance = d;
            maxNode = v;
        }
        ArrayList<int[]> arr = graph.get(v);
        if (arr == null) return;
        
        for(int i=0; i<arr.size(); i++) {
            int[] to = arr.get(i);
            if (!visited[to[0]]) {
                visited[to[0]] = true;
                dfs(to[0], d+to[1]);
                visited[to[0]] = false;
            }
        }
    }
}