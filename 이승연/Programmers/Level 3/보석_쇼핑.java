public class 보석_쇼핑 {
    import java.util.*;

    class Solution {
        public int[] solution(String[] gems) {
            int[] answer = {};
            HashSet<String> set = new HashSet<>(Arrays.asList(gems));
            HashSet<String> contains = new HashSet<>();
            HashMap<String, Integer> map = new HashMap<>();

            int start = 0;
            int end = 0;
            int length = Integer.MAX_VALUE;

            for(; end<gems.length; end++) {
                int num = map.getOrDefault(gems[end], 0);
                map.put(gems[end], num+1);

                while (true) {
                    int n = map.getOrDefault(gems[start], 0);
                    if (n<=1) {
                        break;
                    }
                    map.put(gems[start], n-1);
                    start++;
                }
                if (map.size()==set.size() && length>end-start) {
                    length = end-start;
                    answer = new int[]{start+1, end+1};
                }
            }
            return answer;
        }
    }
}