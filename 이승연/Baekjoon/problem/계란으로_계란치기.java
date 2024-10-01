package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계란으로_계란치기 {
	public static int N;
	public static int[] s;
	public static int[] w;
	public static int answer = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		s = new int[N];
		w = new int[N];

		for(int i=0; i<N; i++) {
			String[] sw = br.readLine().split(" ");
			s[i] = Integer.parseInt(sw[0]);
			w[i] = Integer.parseInt(sw[1]);
		}

		dfs(0, s, 0);
		if (answer == Integer.MIN_VALUE) {
			System.out.println(0);
			return;
		}
		System.out.println(answer);
	}

	public static void dfs(int egg, int[] eggs, int broken) {
		if (egg==eggs.length) {
			return;
		}
		for(int i=0; i<eggs.length; i++) {
			if (eggs[i] <= 0) continue;
			if (i == egg) continue;

			eggs[i] -=  w[egg];
			eggs[egg] -= w[i];
			int b = 0;
			if (eggs[i]<=0) b++;
			if (eggs[egg]<=0) b++;
			answer = Math.max(answer, broken+b);

			for(int next=egg+1; next<eggs.length; next++) { // 다음 계란 고르는 거
				if (eggs[next]>0) {
					dfs(next, eggs, broken+b);
					break;
				}
			}

			eggs[i] +=  w[egg];
			eggs[egg] += w[i];
		}
	}
}
