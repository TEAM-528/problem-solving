package problem;

import java.util.*;

public class 기능개발 {
	class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			int[] answer = {};
			Queue<Integer> queue = new LinkedList<>();

			for(int i=0; i<progresses.length; i++) {
				int day = (100-progresses[i])/speeds[i];
				if ((100-progresses[i])%speeds[i] != 0) {
					day+=1;
				}
				queue.add(day);
			}

			ArrayList<Integer> arr = new ArrayList<>();
			int num = 0;
			int day = -1;
			while(!queue.isEmpty()) {
				int n = queue.poll();
				if (day == -1) {
					num=1;
					day = n;
					continue;
				}

				if (n<=day) {
					num++;
					continue;
				}
				day = n;
				arr.add(num);
				num = 1;

			}
			arr.add(num);

			answer = new int[arr.size()];
			for(int i=0; i<arr.size(); i++) {
				answer[i] = arr.get(i);
			}
			return answer;
		}
	}
}
