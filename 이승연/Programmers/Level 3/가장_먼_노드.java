import java.util.*;

class Solution {
    public Integer[] distance;
    public boolean[][] connected;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        distance = new Integer[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        connected = new boolean[n+1][n+1];
        for(int i=0; i<edge.length; i++) {
            connected[edge[i][0]][edge[i][1]] = true;
            connected[edge[i][1]][edge[i][0]] = true;
        }
        bfs();
        
        Arrays.sort(distance, (Integer o1, Integer o2) -> {
            return o2-o1;
        });
        
        int maxNode = distance[1];
        answer = 1;
        for(int i=2; i<distance.length; i++) {
            if (distance[i] == maxNode) answer++;
        }
        
        return answer;
    }
    
    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            for(int i=1; i<connected.length; i++) {
                if (connected[node][i] && distance[i]==Integer.MAX_VALUE) {
                    distance[i] = distance[node]+1;
                    queue.add(i);
                }
            }
        }
    }
}