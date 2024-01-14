import java.util.*;

class Solution {
    public Set<int[]> set = new HashSet<>();
    public int[][] LINE;
    public int maxX, minX, maxY, minY;
    public String[] solution(int[][] line) {
        String[] answer = {};
        LINE = line;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        
        getPosition(0, new int[2], 0);
        
        char[][] ans = new char[maxY-minY+1][maxX-minX+1];
        for(int i=0; i<ans.length; i++) {
            for(int j=0; j<ans[i].length; j++) {
                ans[i][j] = '.';
            }
        }

        Iterator<int[]> iterator = set.iterator();
        while (iterator.hasNext()) {
            int[] p = iterator.next();
            ans[maxY-p[1]][p[0]-minX] = '*';
        }
        
        answer = new String[ans.length];
        for(int i=0; i<ans.length; i++) {
            String value = "";
            for(int j=0; j<ans[i].length; j++) {
                value += ans[i][j];
            }
            answer[i] = value;
        }
        return answer;
    }
    
    public void getPosition(int idx, int[] position, int n) {
        if (idx == 2) {
            int[] line1 = LINE[position[0]];
            int[] line2 = LINE[position[1]];
            
            long A = (long)line1[0];
            long B = (long)line1[1];
            long E = (long)line1[2];
            long C = (long)line2[0];
            long D = (long)line2[1];
            long F = (long)line2[2];
            
            if (A*D-B*C == 0) {
                return;
            }
            if ((B*F-E*D)%(A*D-B*C) != 0) {
                return;
            }
            if ((E*C-A*F)%(A*D-B*C) != 0) {
                return;
            }
            long x = (B*F-E*D)/(A*D-B*C);
            long y = (E*C-A*F)/(A*D-B*C);
            
            set.add(new int[]{(int)x, (int)y});
            minX = Math.min((int)x, minX);
            minY = Math.min((int)y, minY);
            maxX = Math.max((int)x, maxX);
            maxY = Math.max((int)y, maxY);
            return;
        }
        
        if (n >= LINE.length) {
            return;
        }
        
        getPosition(idx, position, n+1);
        position[idx] = n;
        getPosition(idx+1, position, n+1);
    }
}
