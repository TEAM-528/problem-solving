public class 단어_변환 {
    import java.util.*;
    class Solution {
        public int TARGET = -1;
        public boolean[][] graph;
        public boolean[] visited;
        public Integer answer = Integer.MAX_VALUE;
        public int solution(String begin, String target, String[] words) {
            graph = new boolean[words.length+1][words.length+1];
            for(int i=0; i<words.length+1; i++) {
                String word1 = begin;
                if (i>=1 && i<words.length+1) {
                    word1 = words[i-1];
                    if (word1.equals(target)) TARGET = i;
                }

                for(int j=0; j<words.length+1; j++) {
                    String word2 = begin;
                    if (j>=1 && j<words.length+1) {
                        word2 = words[j-1];
                    }
                    if (i!=j && isClose(word1, word2)) {
                        graph[i][j] = true;
                    }
                }
            }

            if (TARGET == -1) return 0;
            visited = new boolean[words.length+1];
            visited[0] = true;
            dfs(0,0);
            return answer;
        }

        public boolean isClose(String s1, String s2) {
            int difference = 0;
            for(int i=0; i<s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    difference++;
                    if (difference > 1) return false;
                }
            }
            return true;
        }

        public void dfs(int position, int change) {
            if (position == TARGET) {
                answer = Math.min(answer, change);
                return;
            }

            for (int i=0; i<graph.length; i++) {
                if (graph[position][i] && !visited[i]) {
                    visited[i] = true;
                    dfs(i, change+1);
                    visited[i] = false;
                }
            }
        }
    }
}