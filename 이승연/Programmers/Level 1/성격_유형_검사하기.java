import java.util.*;

public class 성격_유형_검사하기 {
    class Solution {
        public String[][] character = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        public HashMap<String, Integer> map = new HashMap<>();
        public String solution(String[] survey, int[] choices) {
            String answer = "";
            for(int i=0; i<character.length; i++) {
                map.put(character[i][0], 0);
                map.put(character[i][1], 0);
            }

            for(int i=0; i<survey.length; i++) {
                String negative = String.valueOf(survey[i].charAt(0));
                String positive = String.valueOf(survey[i].charAt(1));
                if (choices[i]>4) { // positive
                    Integer score = map.get(positive);
                    map.put(positive, score+(choices[i]-4));
                }
                if (choices[i]<4) { // negative
                    Integer score = map.get(negative);
                    map.put(negative, score+(4-choices[i]));
                }
            }

            for(int i=0; i<character.length; i++) {
                String a = character[i][0];
                String b = character[i][1];

                if (map.get(a)<map.get(b)) {
                    answer += b;
                    continue;
                }
                answer += a;
            }
            return answer;
        }
    }
}