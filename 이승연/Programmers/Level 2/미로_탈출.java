import java.util.*;
public class 미로_탈출 {
    class Solution {
        int sx = 0;
        int sy = 0;
        int lx = 0;
        int ly = 0;
        int ex = 0;
        int ey = 0;
        char[][] map;
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};
        public int solution(String[] maps) {
            int answer = 0;

            map = new char[maps.length][maps[0].length()];
            for(int i=0; i<maps.length; i++) {
                map[i] = maps[i].toCharArray();
                for(int j=0; j<map[i].length; j++) {
                    if (map[i][j]=='S') {
                        sx = i;
                        sy = j;
                    }
                    if (map[i][j]=='L') {
                        lx = i;
                        ly = j;
                    }
                    if (map[i][j]=='E') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            int a1 = bfs(sx, sy, lx, ly);
            int b1 = bfs(lx, ly, ex, ey);

            if (a1==-1 || b1==-1) return -1;
            return a1+b1;
        }

        public int bfs(int x1, int y1, int x2, int y2) {
            Queue<Element> queue = new LinkedList<>();
            boolean[][] visited = new boolean[map.length][map[0].length];
            visited[x1][y1] = true;
            queue.add(new Element(x1, y1, 0, visited));

            while(!queue.isEmpty()) {
                Element element = queue.poll();

                if (element.x == x2 && element.y == y2) return element.step;

                for(int i=0; i<4; i++) {
                    int newX = element.x+dx[i];
                    int newY = element.y+dy[i];
                    if (newX>=0 && newX<map.length && newY>=0 && newY<map[0].length) {
                        if (!element.visited[newX][newY] && map[newX][newY]!='X') {
                            boolean[][] v = element.visited.clone();
                            v[newX][newY] = true;
                            queue.add(new Element(newX, newY, element.step+1, v));
                        }
                    }
                }
            }

            return -1;
        }
    }

    class Element {
        int x;
        int y;
        int step;
        boolean[][] visited;

        public Element(int x, int y, int step, boolean[][] visited) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.visited = visited;
        }
    }
}
