package Baekjoon.problem;

import java.util.*;

public class 메뉴_리뉴얼 {
	class Solution {
		public HashMap<String, Integer> map = new HashMap<>();
		public HashMap<Integer, List<String>> map2 = new HashMap<>();
		public String[] solution(String[] orders, int[] course) {
			String[] answer = {};

			for(int i=0; i<orders.length; i++) {
				orders[i] = sort(orders[i]);
				pick(0, "", orders[i]);
			}


			map.forEach((key, value)->{
				if (value>=2) {
					List<String> arr = map2.getOrDefault(key.length(), new ArrayList<>());
					arr.add(key);
					map2.put(key.length(), arr);
				}
			});

			ArrayList<String> ans = new ArrayList<>();
			for(int i=0; i<course.length; i++) {
				List<String> arr = map2.getOrDefault(course[i], new ArrayList<>());
				if (arr.size() == 0) continue;
				Collections.sort(arr, (s1, s2) -> {
					return map.get(s2) - map.get(s1);
				});

				for(int j=0; j<arr.size(); j++) {
					if (map.get(arr.get(j)) == map.get(arr.get(0))) {
						ans.add(arr.get(j));
					}
				}
			}

			answer = new String[ans.size()];
			for(int i=0; i<ans.size(); i++) {
				answer[i] = ans.get(i);
			}

			Arrays.sort(answer);
			return answer;
		}

		public String sort(String s) {
			String[] parsed = s.split("");
			Arrays.sort(parsed);
			String result = "";
			for(int i=0; i<parsed.length; i++) {
				result += parsed[i];
			}
			return result;
		}

		public void pick(int idx, String course, String order) {
			if (idx == order.length()) {
				if (course.length()<2) return;
				int n = map.getOrDefault(course, 0);
				map.put(course, n+1);
				return;
			}

			pick(idx+1, course, order);
			pick(idx+1, course+order.charAt(idx), order);
		}
	}

	class Order {
		String course;
		int num;

		public Order(String course, int num) {
			this.course = course;
			this.num = num;
		}
	}
}
