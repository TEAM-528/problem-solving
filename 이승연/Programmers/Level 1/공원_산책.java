public class 공원_산책 {
    class Solution {
        public char[][] map;
        public int[] dx = new int[]{0,0,1,-1};
        public int[] dy = new int[]{1,-1,0,0};
        public int[] solution(String[] park, String[] routes) {
            int[] answer = {};
            int x = 0;
            int y = 0;
            map = new char[park.length][park[0].length()];
            for(int i=0; i<park.length; i++) {
                map[i] = park[i].toCharArray();
                for(int j=0; j<map[i].length; j++) {
                    if (map[i][j]=='S') {
                        x = i;
                        y = j;
                    }
                }
            }

            for(int i=0; i<routes.length; i++) {
                int dir = 0;
                if (routes[i].charAt(0) == 'N') {
                    dir = 3;
                } else if (routes[i].charAt(0) == 'S') {
                    dir = 2;
                } else if (routes[i].charAt(0) == 'W') {
                    dir = 1;
                } else if (routes[i].charAt(0) == 'E') {
                    dir = 0;
                }

                int nextX=x+dx[dir]*Integer.valueOf(routes[i].charAt(2)-48);
                int nextY=y+dy[dir]*Integer.valueOf(routes[i].charAt(2)-48);

                if (canGo(x, y, nextX, nextY, dir)) {
                    x = nextX;
                    y = nextY;
                }
            }

            answer = new int[]{x, y};
            return answer;
        }

        public boolean canGo(int startX, int startY, int endX, int endY, int dir) {
            int x = startX;
            int y = startY;

            while (x>=0 && x<map.length && y>=0 && y<map[0].length && map[x][y]!='X') {
                if (x==endX && y==endY) {
                    return true;
                }
                x += dx[dir];
                y += dy[dir];
            }
            return false;
        }
    }
}