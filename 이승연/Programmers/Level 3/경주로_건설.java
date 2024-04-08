public class 경주로_건설 {
    class Solution {
        int[][] boardG;
        int[][][] visited;
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};
        int N;

        public int solution(int[][] board) {
            boardG = board;
            visited = new int[board.length][board[0].length][4];
            for(int i=0; i<visited.length; i++) {
                for(int j=0; j<visited[0].length; j++) {
                    for(int k=0; k<4; k++) {
                        visited[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }
            N = board.length;

            for(int i=0; i<4; i++) {
                visited[0][0][i] = 0;
                dfs(0,0,0,0,i);
            }

            int answer = Integer.MAX_VALUE;
            for(int i=0; i<4; i++) {
                answer = Math.min(answer, visited[N-1][N-1][i]);
            }
            return answer;
        }

        public void dfs(int x, int y, int straight, int coner, int dir) {
            if (x==N-1 && y==N-1) {
                return;
            }

            for(int i=0; i<4; i++) {
                int newX = x+dx[i];
                int newY = y+dy[i];

                if (newX>=0 && newX<N && newY>=0 && newY<N) {
                    if (boardG[newX][newY]==0) {
                        if (i==dir) {
                            int cost = visited[x][y][dir]+100;
                            if (visited[newX][newY][i]>cost) {
                                visited[newX][newY][i] = cost;
                                dfs(newX, newY, straight+1, coner, i);
                            }
                        } else {
                            int cost = visited[x][y][dir]+100+500;
                            if (visited[newX][newY][i]>cost) {
                                visited[newX][newY][i] = cost;
                                dfs(newX, newY, straight+1, coner+1, i);
                            }
                        }
                    }
                }
            }
        }
    }
}