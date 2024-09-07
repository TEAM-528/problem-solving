package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 배열_복원하기 {
	public static int H,W,X,Y;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		H = Integer.parseInt(s[0]);
		W = Integer.parseInt(s[1]);
		X = Integer.parseInt(s[2]);
		Y = Integer.parseInt(s[3]);
		arr = new int[H+X][W+Y];

		for(int i=0; i<H+X; i++) {
			String[] inp = br.readLine().split(" ");
			for(int j=0; j<inp.length; j++) {
				arr[i][j] = Integer.parseInt(inp[j]);
			}
		}

		int[][] answer = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if ((i < X && j<W) || (i < H && j<Y)) {
					answer[i][j] = arr[i][j];
					continue;
				}

				answer[i][j] = arr[i][j] - answer[i-X][j-Y];
			}
		}

		for(int i=0; i< answer.length; i++) {
			for(int j=0; j<answer[0].length; j++) {
				System.out.print(answer[i][j]+" ");
			}
			System.out.println();
		}
	}
}
