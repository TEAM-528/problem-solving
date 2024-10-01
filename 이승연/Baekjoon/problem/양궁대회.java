package Baekjoon.problem;

public class 양궁대회 {
	// 53분 걸림
	//테스트 8, 18 틀림

	class Solution {
		public int N;
		public int nnn = 0;
		public int[] infoG;
		public int gap = Integer.MIN_VALUE;
		public int[] answer;
		public int[] solution(int n, int[] info) {
			N = n;
			infoG = info;
			dfs(0, new int[11], 0);

			if (gap == Integer.MIN_VALUE) {
				return new int[]{-1};
			}
			return answer;
		}

		public void dfs(int n, int[] scores, int m) {
			if (n == N) {
				int g = getGap(scores);
				if (g<=0) {
					return;
				}

				if (g == gap && canChange(answer, scores) || (g > gap)) {
					gap = g;
					answer = new int[11];
					for(int i=0; i<answer.length; i++) {
						answer[i] = scores[i];
					}
				}
				return;
			}
			for(int i=m; i<=10; i++) {
				if (scores[i]>infoG[i]) continue;
				scores[i]++;
				dfs(n+1, scores, i);
				scores[i]--;
			}
		}

		public int getGap(int[] scores) {
			int apeach = 0;
			int lion = 0;

			for(int i=0; i<scores.length; i++) {
				if (scores[i]>infoG[i]) { // 라이언이 이긴 경우
					lion += (10-i);
				}
				if (scores[i]<infoG[i]) { // 어피치가 이긴 경우
					apeach += (10-i);
				}
				if (scores[i]==0 && infoG[i]==0) {
					continue;
				}
				if (scores[i]==infoG[i]) {
					apeach += (10-i);
				}
			}

			return lion - apeach;
		}

		public boolean canChange(int[] score1, int[] score2) {
			for(int i=0; i<11; i++) {
				if (score1[10-i] < score2[10-i]) return true;
				if (score1[10-i] > score2[10-i]) return false;
			}
			return false;
		}
	}
}
