class Solution {
    public int[] numOfOne = new int[]{1,2,2,3,4};
    public int solution(int n, long l, long r) {
        int answer = 0;
        return count(n, 1, (long)Math.pow(5, n), l, r);
    }
    
    public int count(int n, long l, long r, long s, long e) {
        if (n == 0) {
            return 1;
        }
        if (r<s || l>e) {
            return 0;
        }
        if (r-l < 5) {
            if (s <= r && r < e && l <= s) {
                return numOfOne[(int)(r-s)%5];
            }
            else if (l > s && l <= e && e <= r) {
                return numOfOne[(int)(e-l)%5];
            }
            else if (l <= s && r >= e) {
                int[] numberList = new int[]{1,1,0,1,1};
                int num = 0;
                for(int i=(int)(s-l)%5; i<e%5; i++) {
                    if (numberList[i] == 1) {
                        num++;
                    }
                }
                return num;
            }
        }
        
        
        long length = (long)Math.pow(5, n-1);
        
        return count(n-1, l, l+length*1-1, s, e)+count(n-1, l+length*1, l+length*2-1,  s, e)+count(n-1, l+length*3, l+length*4-1, s, e)+count(n-1, l+length*4, l+length*5-1,  s, e);
    }
}
