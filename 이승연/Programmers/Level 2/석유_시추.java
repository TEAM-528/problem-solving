import java.util.*;
class Solution {
    public int[][] oil;
    public int[][] Land;
    public HashMap<Integer, Integer> size = new HashMap<>();
    public int[] dx = new int[]{0,0,1,-1};
    public int[] dy = new int[]{1,-1,0,0};
    public int solution(int[][] land) {
        int answer = 0;
        oil = new int[land.length][land[0].length];
        Land = land;
        
        int num = 2;
        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land[0].length; j++) {
                if (land[i][j] == 1) {
                    bfs(i, j, num);
                    num++;
                }
            }
        }
        
        for(int i=0; i<land[0].length; i++) {
            int totalOil = 0;
            boolean[] visited = new boolean[num+1];
            
            for(int j=0; j<land.length; j++) {
                if (land[j][i]!=0) {
                    int value = size.getOrDefault(land[j][i], 0);
                    
                    if (value != 0 && !visited[land[j][i]]) {
                        visited[land[j][i]] = true;
                        totalOil += value;
                    }
                }
                
            }
            answer = Math.max(totalOil, answer);
        }
        
        return answer;
    }
    
    public void bfs(int x, int y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        Land[x][y] = num;
        queue.add(new int[]{x,y});
        int area = 1;
        
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            
            for(int i=0; i<4; i++) {
                int newX = p[0]+dx[i];
                int newY = p[1]+dy[i];
                
                if (newX>=0 && newX<Land.length && newY>=0 && newY<Land[0].length && Land[newX][newY]==1) {
                    Land[newX][newY] = num;
                    area++;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        
        size.put(num, area);
    }
}
