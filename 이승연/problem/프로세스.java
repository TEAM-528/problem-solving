package problem;

import java.util.*;

public class 프로세스 {
	class Solution {
		public int solution(int[] priorities, int location) {
			Queue<int[]> queue = new LinkedList<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>((Integer n1, Integer n2) -> {
				return n2-n1;
			});
			for(int i=0; i<priorities.length; i++) {
				queue.add(new int[]{priorities[i], i});
				pq.add(priorities[i]);
			}

			int turn = 0;
			while(!queue.isEmpty()) {
				int n = pq.poll();
				while(queue.peek()[0] != n) {
					queue.add(queue.poll());
				}
				int[] p = queue.poll();
				turn++;
				if (p[1]==location) {
					return turn;
				}
			}
			return turn;
		}
	}
}
