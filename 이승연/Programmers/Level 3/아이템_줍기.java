public class 아이템_줍기 {
    import java.util.*;

    class Solution {
        public boolean[][] map;
        public int[][] dup;

        public int[] dx = new int[]{0,0,1,-1};
        public int[] dy = new int[]{1,-1,0,0};
        public int[] dx8 = new int[]{0,0,1,-1,1,-1,1,-1};
        public int[] dy8 = new int[]{1,-1,0,0,1,-1,-1,1};

        public int total = 0;
        public int oneway = 0;

        public int ITEM_X, ITEM_Y;
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int answer = 0;
            map = new boolean[102][102];
            dup = new int[102][102];

            getDup(rectangle);

            int x = characterX*2;
            int y = characterY*2;
            ITEM_X = itemX*2;
            ITEM_Y = itemY*2;

            map[x][y] = true;
            oneway++;
            total++;
            dfs(x, y, 0);

            answer = Math.min(oneway/2, (total-oneway)/2);
            return answer;

        }

        public void getDup(int[][] rectangle) {
            for(int i=0; i<rectangle.length; i++) {
                int x1 = rectangle[i][0]*2;
                int y1 = rectangle[i][1]*2;
                int x2 = rectangle[i][2]*2;
                int y2 = rectangle[i][3]*2;

                for(int j=x1; j<=x2; j++) {
                    for(int k=y1; k<=y2; k++) {
                        dup[j][k]+=1;
                    }
                }
            }
        }

        public void dfs(int x, int y, int d) {
            if (x==ITEM_X && y==ITEM_Y) {
                oneway = d;
            };
            for(int i=0; i<4; i++) {
                int newX = x+dx[i];
                int newY = y+dy[i];
                if (newX>=0 && newX<102 && newY>=0 && newY<102) {
                    if (map[newX][newY]) continue;
                    if (!isEdge(newX, newY) || dup[newX][newY]==0) continue;
                    map[newX][newY] = true;
                    total++;
                    dfs(newX, newY, d+1);
                }
            }
        }

        public boolean isEdge(int x, int y) {
            for(int i=0; i<8; i++) {
                int newX = x+dx8[i];
                int newY = y+dy8[i];
                if (newX>=0 && newX<102 && newY>=0 && newY<102) {
                    if (dup[newX][newY]==0) return true;
                }
            }

            return false;
        }
    }
}