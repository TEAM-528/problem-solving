import java.util.*;

public class 섬_연결하기 {
    class Solution {
        public int[] group;
        public int solution(int n, int[][] costs) {
            int answer = 0;
            Arrays.sort(costs, (int[] o1, int[] o2) -> {
                return o1[2]-o2[2];
            });

            group = new int[n+1];
            for(int i=0; i<group.length; i++) {
                group[i] = i;
            }

            for(int i=0; i<costs.length; i++) {
                if (getParent(costs[i][0]) == getParent(costs[i][1])) continue;
                answer += costs[i][2];

                union(costs[i][0], costs[i][1]);
            }
            return answer;
        }

        public void union(int a, int b) {
            a = getParent(a);
            b = getParent(b);

            if (a>b) {
                group[a] = b;
                return;
            }
            group[b] = a;
        }

        public int getParent(int n) {
            if (group[n] == n) return n;
            return getParent(group[n]);
        }
    }
}