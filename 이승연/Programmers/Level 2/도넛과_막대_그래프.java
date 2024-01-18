import java.util.*;

class Solution {
    public int[][] Graphs;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<edges.length; i++) {
            set.add(edges[i][0]);
            set.add(edges[i][1]);
        }
        
        int[] inbound = new int[set.size()+1];
        int[] outbound = new int[set.size()+1];
        for(int i=0; i<edges.length; i++) {
            outbound[edges[i][0]]++;
            inbound[edges[i][1]]++;
        }
        
        for(int i=1; i<inbound.length; i++) {
            if (inbound[i]==0 && outbound[i]>=2) {
                answer[0] = i;
            } else if ((inbound[i]>=1 && outbound[i]==0) || (inbound[i]==0 && outbound[i]==0)) {
                answer[2]++;
            } else if (inbound[i]>=2 && outbound[i]>=2) {
                answer[3]++;
            }
        }
        answer[1] = outbound[answer[0]]-answer[2]-answer[3];
        
        return answer;
    }
}
