class Solution {
    public int solution(String name) {
        int answer = 0;
        int[] moving = new int[26];
        
        for(int i=0; i<26; i++) {
            if (i < 13) {
                moving[i] = i;
            } else {
                moving[i] = 26-i;
            }
        }
        
        int LR = name.length()-1;
        for(int i=0; i<name.length(); i++) {
            answer += moving[name.charAt(i)-'A'];
            
            int idx = i+1;
            int length = 0;
            while(idx<name.length() && name.charAt(idx) == 'A') {
                idx++;
                length++;
            }
            
            LR = Math.min(LR, i*2 + (name.length()-(length+i+1)));
            LR = Math.min(LR, i + (name.length()-(length+i+1))*2);
    
        }
        
        return answer+LR;
    }
}
