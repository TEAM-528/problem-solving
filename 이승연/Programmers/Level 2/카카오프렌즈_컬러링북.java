import java.util.*;

class Solution {
    public int[][] PICTURE;
    public boolean[][] visited;
    public int[] dx = new int[]{0,0,1,-1};
    public int[] dy = new int[]{1,-1,0,0};
    public int M, N;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        PICTURE = picture;
        visited = new boolean[m][n];
        M = m;
        N = n;
        
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if (picture[i][j]!=0 && !visited[i][j]) {
                    int space = area(i, j);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(space, maxSizeOfOneArea);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int area(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int space = 1;
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            for(int i=0; i<4; i++) {
                int newX = p[0]+dx[i];
                int newY = p[1]+dy[i];
                int color = PICTURE[p[0]][p[1]];
                if (newX>=0 && newX<M && newY>=0 && newY<N && PICTURE[newX][newY]==color && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    space++;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        
        return space;
    }
}
