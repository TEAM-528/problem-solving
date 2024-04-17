import java.util.*;

public class 부대복귀 {
    class Solution {
        public HashMap<Integer, ArrayList<Integer>> connected = new HashMap<>();
        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            int[] answer = new int[sources.length];
            for(int i=0; i<roads.length; i++) {
                ArrayList<Integer> arr1 = connected.getOrDefault(roads[i][0], new ArrayList<>());
                arr1.add(roads[i][1]);
                connected.put(roads[i][0], arr1);
                ArrayList<Integer> arr2 = connected.getOrDefault(roads[i][1], new ArrayList<>());
                arr2.add(roads[i][0]);
                connected.put(roads[i][1], arr2);
            }

            for(int i=0; i<answer.length; i++) {
                answer[i] = bfs(sources[i], destination, n);
            }
            return answer;
        }

        public int bfs(int source, int destination, int n) {
            if (source == destination) return 0;

            Queue<int[]> queue = new LinkedList<>();
            boolean[] visited = new boolean[n+1];
            queue.add(new int[]{source, 0});
            visited[source] = true;

            while(!queue.isEmpty()) {
                int[] node = queue.poll();

                ArrayList<Integer> arr = connected.getOrDefault(node[0], new ArrayList<>());
                for(int i=0; i<arr.size(); i++) {
                    int next = arr.get(i);
                    if (!visited[next]) {
                        if (next==destination) {
                            return node[1]+1;
                        }
                        visited[next] = true;
                        queue.add(new int[]{next, node[1]+1});
                    }
                }
            }

            return -1;
        }
    }

    import java.util.*;

    class Solution {
        public int[] distance;
        public List<List<Integer>> connected = new ArrayList<>();
        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            int[] answer = new int[sources.length];
            distance = new int[n+1];
            for(int i=0; i<n+1; i++) {
                connected.add(new ArrayList<>());
            }
            for(int i=0; i<roads.length; i++) {
                connected.get(roads[i][0]).add(roads[i][1]);
                connected.get(roads[i][1]).add(roads[i][0]);
            }

            for(int i=0; i<distance.length; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            dijkstra(destination);
            for(int i=0; i<answer.length; i++) {
                if (distance[sources[i]] == Integer.MAX_VALUE) {
                    answer[i] = -1;
                    continue;
                }
                answer[i] = distance[sources[i]];
            }

            return answer;
        }

        public void dijkstra(int destination) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(destination);
            distance[destination] = 0;

            while(!queue.isEmpty()) {
                int node = queue.poll();
                List<Integer> next = connected.get(node);
                for(int i=0; i<next.size(); i++) {
                    if (distance[next.get(i)] > distance[node]+1) {
                        distance[next.get(i)] = distance[node]+1;
                        queue.add(next.get(i));
                    }
                }
            }
        }
    }
}