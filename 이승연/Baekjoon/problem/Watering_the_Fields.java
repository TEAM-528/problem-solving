package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Watering_the_Fields {
	public static int N, C;
	public static int[][] points;
	public static PriorityQueue<Point> pq = new PriorityQueue<>((Point p1, Point p2) -> {
		return p1.distance-p2.distance;
	});
	public static int[] root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		points = new int[N][2];
		root = new int[N];
		for(int i=0; i<root.length; i++) {
			root[i] = i;
		}
		for(int i=0; i<N; i++) {
			String[] p = br.readLine().split(" ");
			points[i][0] = Integer.parseInt(p[0]);
			points[i][1] = Integer.parseInt(p[1]);
		}

		dfs(new int[2], 0, 0);

		int answer = 0;
		int count = 0;
		while (count < N-1) {
			if (pq.isEmpty()) {
				answer = -1;
				break;
			}
			Point poll = pq.poll();

			int r1 = find(poll.p1);
			int r2 = find(poll.p2);
			if (poll.distance < C || r1 == r2) {
				continue;
			}
			root[r2] = r1;
			answer += poll.distance;
			count++;;
		}
		System.out.println(answer);
	}

	public static int getDistance(int[] p1, int[] p2) {
		return (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);
	}

	public static void dfs(int[] p, int idx, int n) {
		if (idx == 2) {
			pq.add(new Point(p[0], p[1], getDistance(points[p[0]], points[p[1]])));
			return;
		}

		for(int i=n; i<N; i++) {
			int[] clone = p.clone();
			clone[idx] = i;
			dfs(clone, idx+1, i+1);
		}
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		root[y] = x;
	}

	public static int find(int n) {
		if (root[n] == n) {
			return n;
		}
		return find(root[n]);
	}
}

class Point {
	int p1;
	int p2;
	int distance;

	public Point(int p1, int p2, int distance) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = distance;
	}
}
