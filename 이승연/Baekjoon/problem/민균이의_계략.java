package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 민균이의_계략 {
	public static int N;
	public static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}

		int[] dp = new int[N];
		for(int i=0; i<dp.length; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}

		int answer = 1;
		for(int i=0; i<dp.length; i++) {
			answer = Math.max(dp[i], answer);
		}
		System.out.println(answer);
	}

	/*
	public static void dfs(int length, int idx, int max) {
		if (idx == N) {
			answer = Math.max(length, answer);
			return;
		}

		dfs(length, idx+1, max);
		if (arr[idx] > max) {
			dfs(length+1, idx+1, arr[idx]);
		}
	}
	 */
}
