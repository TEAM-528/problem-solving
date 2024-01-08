class Solution {
    public int[][] USERS;
    public int[] EMOTIONS;
    public int[] answer = new int[]{0,0};
    public int[] solution(int[][] users, int[] emoticons) {
        USERS = users;
        EMOTIONS = emoticons;
        getDiscountRate(0, new int[emoticons.length]);
        return answer;
    }
    
    public void getDiscountRate(int num, int[] discountRate) {
        if (num == EMOTIONS.length) {
            int[] result = getEventResult(discountRate);
            
            if (answer[0] < result[0]) {
                answer = result;
            } else if (answer[0] == result[0] && answer[1] < result[1]) {
                answer = result;
            }
            return;
        }
        discountRate[num]=10;
        getDiscountRate(num+1, discountRate);
        
        discountRate[num]+=10;
        getDiscountRate(num+1, discountRate);
        
        discountRate[num]+=10;
        getDiscountRate(num+1, discountRate);
        
        discountRate[num]+=10;
        getDiscountRate(num+1, discountRate);
    }
    
    public int[] getEventResult(int[] discountRate) {
        int[] result = new int[]{0,0};
        
        for(int i=0; i<USERS.length; i++) {
            int amount = 0;
            for(int j=0; j<discountRate.length; j++) {
                if (discountRate[j] >= USERS[i][0]) {
                    amount += (EMOTIONS[j]*(100-discountRate[j])/100);
                }
            }
            
            if (amount >= USERS[i][1]) {
                result[0]++;
            } else {
                result[1] += amount; 
            }
        }
        
        return result;
    }
}
