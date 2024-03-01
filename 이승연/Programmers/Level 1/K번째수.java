import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0; i<commands.length; i++) {
            answer[i] = getAns(array, commands[i]);
        }
        return answer;
    }
    
    public int getAns(int[] array, int[] command) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {
            return o1-o2;
        }); 
        
        for(int i=command[0]-1; i<command[1]; i++) {
            pq.add(array[i]);
        }
        
        for(int i=0; i<command[2]-1; i++) {
            pq.poll();
        }
        return pq.poll();
    }
}