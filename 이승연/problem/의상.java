package problem;

import java.util.*;

public class 의상 {
	public static void main(String[] args) {
		class Solution {
			public int solution(String[][] clothes) {
				int answer = 1;
				HashMap<String, List<String>> map = new HashMap<>();

				for(int i=0; i<clothes.length; i++) {
					List<String> list = map.getOrDefault(clothes[i][1], new ArrayList<>());
					list.add(clothes[i][0]);
					map.put(clothes[i][1], list);
				}

				Iterator itr = map.keySet().iterator();
				while(itr.hasNext()) {
					String key = (String) itr.next();
					List<String> l = map.get(key);
					answer *= (l.size()+1);
				}
				return answer-1;
			}
		}
	}
}
