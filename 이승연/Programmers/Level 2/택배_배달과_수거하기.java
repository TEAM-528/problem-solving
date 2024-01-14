import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Stack<Integer> delivery = new Stack<>();
        Stack<Integer> pickup = new Stack<>();
        
        for(int i=0; i<deliveries.length; i++) {
            for(int j=0; j<deliveries[i]; j++) {
                delivery.push(i+1);
            }
        }
        
        for(int i=0; i<pickups.length; i++) {
            for(int j=0; j<pickups[i]; j++) {
                pickup.push(i+1);
            }
        }
        
        while(!delivery.isEmpty() || !pickup.isEmpty()) {
            int distance = 0;
            if (!delivery.isEmpty()) {
                distance = Math.max(delivery.peek(), distance);
            }
            if (!pickup.isEmpty()) {
                distance = Math.max(pickup.peek(), distance);
            }
            answer += distance*2;
            
            int go = cap;
            int back = cap;
            while(!delivery.isEmpty() && go>0) {
                go--;
                delivery.pop();
            }
    
            while(!pickup.isEmpty() && back>0) {
                back--;
                pickup.pop();
            }
        }
        return answer;
    }
}
