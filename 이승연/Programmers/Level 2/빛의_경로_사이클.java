import java.util.*;

class Solution {
    public int[] dx = new int[]{-1,0,1,0};
    public int[] dy = new int[]{0,1,0,-1};
    public char[][] Grid;
    public boolean[][][] visited;
    ArrayList<Integer> ans = new ArrayList<>();
    public int[] solution(String[] grid) {
        int[] answer = {};
        Grid = new char[grid.length][grid[0].length()];
        for(int i=0; i<grid.length; i++) {
            Grid[i] = grid[i].toCharArray();
        }
        visited = new boolean[grid.length][grid[0].length()][4];
    
        for(int i=0; i<Grid.length; i++) {
            for(int j=0; j<Grid[0].length; j++) {
                for(int k=0; k<4; k++) {
                    if (!visited[i][j][k]) {
                        bfs(i,j,k);
                    }
                }
            }
        }
        
        Collections.sort(ans);
        answer = new int[ans.size()];
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    public int bfs(int x, int y, int dir) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, dir});
        int num = 0;
        visited[x][y][dir] = true;
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            visited[p[0]][p[1]][p[2]] = true;
            
            int newX = (p[0]+dx[p[2]])%Grid.length;
            int newY = (p[1]+dy[p[2]])%Grid[0].length;
            num++;
        
            if (newX == -1) {
                newX = Grid.length-1;
            }
            if (newY == -1) {
                newY = Grid[0].length-1;
            }
            if (newX == Grid.length) {
                newX = 0;
            }
            if (newY == Grid[0].length) {
                newY = 0;
            }
            
            
            
            if (Grid[newX][newY]=='S') {
                if (visited[newX][newY][p[2]]) {
                    ans.add(num);
                    continue;
                }
                queue.add(new int[]{newX, newY, p[2]});
                visited[newX][newY][p[2]] = true;
            } else if (Grid[newX][newY]=='L') {
                int nextDir = (p[2]-1)%4;
                if (nextDir == -1) {
                    nextDir = 3;
                }
                
                if (!visited[newX][newY][nextDir]) {
                    queue.add(new int[]{newX, newY, nextDir});
                    visited[newX][newY][nextDir] = true;
                    continue;
                }
                ans.add(num);
            } else if (Grid[newX][newY]=='R') {
                
                int nextDir = (p[2]+1)%4;
                if (nextDir == 4) {
                    nextDir = 0;
                }
                
                if (!visited[newX][newY][nextDir]) {
                    queue.add(new int[]{newX, newY, nextDir});
                    visited[newX][newY][nextDir] = true;
                    continue;
                }
                ans.add(num);
            } 
        }
        
        return -1;
    }
}
