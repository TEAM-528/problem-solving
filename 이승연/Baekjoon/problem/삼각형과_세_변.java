package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 삼각형과_세_변 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);

			if (a==0 && b==0 && c==0) {
				break;
			}

			if (a+b<=c || b+c<=a || a+c<=b) {
				System.out.println("Invalid");
			} else if (a==b && b==c) {
				System.out.println("Equilateral");
			} else if (a==b || b==c || a==c) {
				System.out.println("Isosceles");
			} else {
				System.out.println("Scalene");
			}
		}
	}
}
