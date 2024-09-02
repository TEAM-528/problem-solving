package problem;

import java.util.*;

public class 베스트앨범 {
	public static void main(String[] args) {
		class Solution {
			public int[] solution(String[] genres, int[] plays) {
				int[] answer = {};
				HashMap<String, Integer> map1 = new HashMap<>();
				HashMap<String, List<Integer>> map2 = new HashMap<>();

				for(int i=0; i<genres.length; i++) {
					Integer n = map1.getOrDefault(genres[i], 0);
					n += plays[i];
					map1.put(genres[i], n);

					List<Integer> l = map2.getOrDefault(genres[i], new ArrayList<>());
					l.add(i);
					map2.put(genres[i], l);
				}

				List<String> genre = new ArrayList<>();
				Iterator iter = map1.keySet().iterator();
				while(iter.hasNext()) {
					String g = (String) iter.next();
					genre.add(g);
				}

				Collections.sort(genre, (String s1, String s2) -> {
					return map1.get(s2)-map1.get(s1);
				});

				Iterator iter2 = map2.keySet().iterator();
				while(iter2.hasNext()) {
					String g = (String) iter2.next();
					List<Integer> l = map2.get(g);
					Collections.sort(l, (Integer n1, Integer n2) -> {
						return plays[n2]-plays[n1];
					});
					map2.put(g, l);
				}

				List<Integer> arr = new ArrayList<>();
				for(int i=0; i<genre.size(); i++) {
					List<Integer> l = map2.get(genre.get(i));
					for(int j=0; j<2; j++) {
						if (l.size() > j) {
							arr.add(l.get(j));
						}
					}
				}

				answer = new int[arr.size()];
				for(int i=0; i<arr.size(); i++) {
					answer[i] = arr.get(i);
				}
				return answer;
			}
		}
	}
}
