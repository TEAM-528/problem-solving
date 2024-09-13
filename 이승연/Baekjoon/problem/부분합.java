package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int S = Integer.parseInt(s[1]);

		int[] arr = new int[N];
		String[] a = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(a[i]);
		}
		int sum = arr[0];
		int answer = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;

		while (end < N) {
			if (sum < S) {
				end++;
				if (end < N) {
					sum += arr[end];
				}
				continue;
			}
			answer = Math.min(answer, end-start+1);
			sum -= arr[start++];
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
			return;
		}
		System.out.println(answer);
	}
}
