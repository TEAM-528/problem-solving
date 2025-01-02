package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZOAC4 {
	public static int H, W, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		H = Integer.parseInt(split[0]);
		W = Integer.parseInt(split[1]);
		N = Integer.parseInt(split[2]);
		M = Integer.parseInt(split[3]);

		int answer = 1;

		if (W%(M+1) == 0) {
			answer *= W/(M+1);
		} else {
			answer *= (W/(M+1)+1);
		}

		if (H%(N+1) == 0) {
			answer *= H/(N+1);
		} else {
			answer *= (H/(N+1)+1);
		}

		System.out.println(answer);
	}
}
