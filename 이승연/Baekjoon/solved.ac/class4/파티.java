import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ÆÄÆ¼ {
    public static int N, M, X;
    public static int[][] timeToGo;
    public static int[][] timeToGoBack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmx = br.readLine().split(" ");
        N = Integer.parseInt(nmx[0]);
        M = Integer.parseInt(nmx[1]);
        X = Integer.parseInt(nmx[2]);

        timeToGo = new int[N+1][N+1];
        timeToGoBack = new int[N+1][N+1];
        for(int i=0; i<M; i++) {
            String[] info = br.readLine().split(" ");
            int to = Integer.parseInt(info[0]);
            int from = Integer.parseInt(info[1]);
            int time = Integer.parseInt(info[2]);
            timeToGoBack[to][from] = time;
            timeToGo[from][to] = time;
        }


        int[] go = dijkstra(X, timeToGo);
        int[] back = dijkstra(X, timeToGoBack);

        int answer = Integer.MIN_VALUE;
        for(int i=1; i<go.length; i++) {
            answer = Math.max(answer, go[i]+back[i]);
        }

        System.out.println(answer);
    }   
    
    public static int[] dijkstra(int v, int[][] times) {
        int[] result = new int[N+1];   
        boolean[] visited = new boolean[N+1];    
        int point = v;
        int count = N-1;
        visited[v] = true;

        while (count != 0) {
            for(int i=1; i<times[point].length; i++) {
                if (times[point][i] != 0 && !visited[i]) {
                    if (result[i] == 0) {
                        result[i] = result[point]+times[point][i];
                    } else {
                        result[i] = Math.min(result[i], result[point]+times[point][i]);
                    }
                }
            }

            int time = Integer.MAX_VALUE;
            for(int i=1; i<result.length; i++) {
                if (!visited[i] && time>result[i] && result[i]!=0) {
                    time = result[i];
                    point = i;
                }
            }
        
            visited[point] = true;
            count--;
        }


        return result;
    }
}