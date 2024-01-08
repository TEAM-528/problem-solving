class Solution {
    public int answer = 0;
    public int NUM;
    public int[] dx = new int[]{0,0,1,-1,1,1,-1,-1};
    public int[] dy = new int[]{1,-1,0,0,1,-1,1,-1};
    public boolean[][] map;
    public int solution(int n) {
        NUM = n;
        map = new boolean[n][n];
        solve(0);
        return answer;
    }
    
    public void solve(int num) {
        if (num == NUM) {
            answer++;
            return;
        }
        
        for(int j=0; j<NUM; j++) {
            if (!map[num][j]) {
                if (isAvailable(num, j)) {
                    map[num][j] = true;
                    solve(num+1);
                    map[num][j] = false;
                }
            }        
        }
    }
    
    public boolean isAvailable(int x, int y) {
        for(int i=0; i<8; i++) {
            for(int j=0; j<NUM; j++) {
                int newX = x+dx[i]*j;
                int newY = y+dy[i]*j;
                if (newX>=0 && newX<NUM && newY>=0 && newY<NUM && map[newX][newY]) {
                    return false;
                }
            }
        }
    
        return true;
    }
}
