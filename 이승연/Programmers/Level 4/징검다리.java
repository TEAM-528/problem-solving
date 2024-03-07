import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int left = 1;
        int right = distance+1;

        while (left<right) {
            int middle = (left+right)/2;
            if(check(rocks, middle, n, distance)) {
                left = middle+1;
                answer = middle;
                continue;
            }
            right = middle;

        }
        return answer;
    }

    public boolean check(int[] rocks, int distance, int n, int destination) {
        int position = 0;
        int destroyed = 0;
        for(int i=0; i<rocks.length; i++) {
            if (rocks[i]-position < distance) {
                destroyed++;
                continue;
            }
            position = rocks[i];
        }
        if (destination - position < distance) {
            destroyed++;
        }
        if (destroyed > n) return false;
        return true;
    }
}