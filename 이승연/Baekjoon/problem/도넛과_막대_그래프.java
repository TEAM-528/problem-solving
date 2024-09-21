package Baekjoon.problem;

public class 도넛과_막대_그래프 {
	class Solution {
		public int[][] count;
		public boolean[] contain;
		public int[] solution(int[][] edges) {
			int[] answer = new int[4];
			count = new int[1000001][2];
			contain = new boolean[1000001];
			int num = Integer.MIN_VALUE;
			for(int i=0; i<edges.length; i++) {
				count[edges[i][0]][0]++;
				count[edges[i][1]][1]++;
				num = Math.max(edges[i][0], num);
				num = Math.max(edges[i][1], num);
				contain[edges[i][0]] = true;
				contain[edges[i][1]] = true;
			}

			int numOfGraph = 0;
			for(int i=1; i<=num; i++) {
				if (count[i][0]>=2 && count[i][1]==0) { // 나가기만 하는 거
					numOfGraph = count[i][0];
					answer[0] = i;
				}
			}
			System.out.println(numOfGraph);
			for(int i=0; i<edges.length; i++) {
				if (edges[i][0] == answer[0]) {
					count[edges[i][1]][1]--;

				}
			}


			for(int i=1; i<=num; i++) {
				if (count[i][0]==2 && count[i][1]==2) {
					answer[3]++;
				}

				if (count[i][0]==0 && count[i][1]==0 && contain[i]) {
					answer[2]++;
				}
				if (count[i][0]==0 && count[i][1]==1) {
					answer[2]++;
				}
			}

			answer[1] = numOfGraph-answer[2]-answer[3];

			return answer;
		}
	}
}
