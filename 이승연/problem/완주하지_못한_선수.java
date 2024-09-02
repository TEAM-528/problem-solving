package problem;

import java.util.*;

public class 완주하지_못한_선수 {
	public static void main(String[] args) {
		class Solution {
			public String solution(String[] participant, String[] completion) {
				String answer = "";
				HashMap<String, Integer> map = new HashMap<>();
				for(int i=0; i<completion.length; i++) {
					Integer n = map.getOrDefault(completion[i], 0);
					map.put(completion[i], n+1);
				}

				for(int i=0; i<participant.length; i++) {
					int n = map.getOrDefault(participant[i], 0);
					if (n == 0) {
						return participant[i];
					}
					map.put(participant[i], n-1);
				}
				return answer;
			}
		}
	}
}
