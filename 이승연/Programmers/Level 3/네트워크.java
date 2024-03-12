public class 네트워크 {
    class Solution {
        public boolean[] visited;
        public int[][] COMPUTERS;
        public int solution(int n, int[][] computers) {
            int answer = 0;
            visited = new boolean[n];
            COMPUTERS = computers;

            for(int i=0; i<computers.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    answer++;
                    dfs(i);
                }
            }

            return answer;
        }

        public void dfs(int computer) {
            for(int i=0; i<COMPUTERS[computer].length; i++) {
                if (COMPUTERS[computer][i]==1 && !visited[i]) {
                    visited[i] = true;
                    dfs(i);
                }
            }
        }
    }
}