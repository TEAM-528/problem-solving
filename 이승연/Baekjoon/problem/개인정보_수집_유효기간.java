package Baekjoon.problem;

import java.util.*;

public class 개인정보_수집_유효기간 {
	// 푸는 시간: 25분
	class Solution {
		public HashMap<String, String> map = new HashMap<>();
		public int[] solution(String today, String[] terms, String[] privacies) {
			int[] answer = {};
			List<Integer> arr = new ArrayList<>();


			long todayL = stringToLong(today);

			for(int i=0; i<terms.length; i++) {
				String[] t = terms[i].split(" ");
				map.put(t[0], t[1]);
			}

			for(int i=0; i<privacies.length; i++) {
				String[] p = privacies[i].split(" ");
				String t = map.get(p[1]);

				long start = stringToLong(p[0]);
				long later = getNMonthLater(start, t);

				if (later<todayL) {
					arr.add(i+1);
				}
			}

			answer = new int[arr.size()];
			for(int i=0; i<arr.size(); i++) {
				answer[i] = arr.get(i);
			}
			return answer;
		}

		public long stringToLong(String date) {
			String[] parsed = date.split("[.]");

			long year = Long.parseLong(parsed[0]);
			long month = Long.parseLong(parsed[1]);
			long day = Long.parseLong(parsed[2]);

			return day+month*28+year*28*12;
		}

		public long getNMonthLater(long date, String time) {
			long t = Long.parseLong(time);
			return date+t*28-1;
		}
	}
}
