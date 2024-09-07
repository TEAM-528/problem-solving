package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Olympiad_Pizza {
	public static int N;
	public static Queue<Contestant> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(s[i]);
			queue.add(new Contestant(i, n));
		}

		int[] answer = new int[N];
		int count = 0;
		while(!queue.isEmpty()) {
			Contestant c = queue.poll();
			c.slicesOfPizza -= 1;
			if (c.slicesOfPizza == 0) {
				answer[c.turn] = ++count;
				continue;
			}
			queue.add(c);
			count++;
		}

		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}
}

class Contestant {
	int turn;
	int slicesOfPizza;

	public Contestant(int turn, int slicesOfPizza) {
		this.turn = turn;
		this.slicesOfPizza = slicesOfPizza;
	}
}
