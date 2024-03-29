public class 양과_늑대 {
    import java.util.*;
    class Solution {
        public int S = 1;
        public int W = 0;
        public int[] infoG;
        public boolean[][] edgesG;
        public int solution(int[] info, int[][] edges) {
            infoG = info;
            edgesG = new boolean[info.length][info.length];
            for(int i=0; i<edges.length; i++) {
                int p = edges[i][0];
                int c = edges[i][1];
                edgesG[p][c] = true;
            }

            ArrayList<Integer> c = new ArrayList<>();
            for(int i=0; i<edgesG[0].length; i++) {
                if (edgesG[0][i]) c.add(i);
            }


            dfs(0, 1, 0, c);

            return S;
        }

        public void dfs(int node, int s, int w, ArrayList<Integer> childs) {
            if (s <= w) {
                // S = Math.max(S, s); -> 한번도 안 잡아먹힐 수 있으므로 이렇게 갱신하면 안됨.
                return;
            }

            for(int i=0; i<childs.size(); i++) {
                int next = childs.get(i);
                ArrayList<Integer> lefts = new ArrayList<>();
                for(int j=0; j<childs.size(); j++) {
                    lefts.add(childs.get(j));
                }
                lefts.remove(Integer.valueOf(next));

                for(int j=0; j<edgesG[next].length; j++) {
                    if (edgesG[next][j]) lefts.add(j);
                }

                if (infoG[next]==0) {
                    S = Math.max(S, s+1);
                    dfs(next, s+1, w, lefts);
                    continue;
                }
                dfs(next, s, w+1, lefts);
            }
        }
    }
}