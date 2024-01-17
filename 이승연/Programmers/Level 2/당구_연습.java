class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i=0; i<balls.length; i++) {
            answer[i] = getMinDistance(balls[i], startX, startY, m, n);
        }
        return answer;
    }
    
    public int getMinDistance(int[] ball, int x, int y, int m, int n) {
        int result = Integer.MAX_VALUE;     
        if (ball[0]==x && ball[1]==y) {
            return 0;
        }
        
        // y=n에 부딪히는 경우
        if ((ball[0]!=x) || (x==ball[0] && y>ball[1])) { // 원쿠션인 경우만
            int newX = x;
            int newY = 2*n-y;
            result = Math.min(AtoB(newX, newY, ball[0], ball[1]), result);
        } 
        
        // y=0에 부딪히는 경우
        if ((ball[0]!=x) || (x==ball[0] && y<ball[1])) {
            int newX = x;
            int newY = -y;
            result = Math.min(AtoB(newX, newY, ball[0], ball[1]), result);
        }
        
        // x=m에 부딪히는 경우
        if ((ball[1]!=y) || (x>ball[0] && y==ball[1])) {
            int newX = 2*m-x;
            int newY = y;
            result = Math.min(AtoB(newX, newY, ball[0], ball[1]), result);
        }
        
        // x=0에 부딪히는 경우
        if ((ball[1]!=y) || (x<ball[0] && y==ball[1])) {
            int newX = -x;
            int newY = y;
            result = Math.min(AtoB(newX, newY, ball[0], ball[1]), result);
        }
        
        
        
        return result;
    }
    
    public int AtoB(int x1, int y1, int x2, int y2) {
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
}
