package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 캐슬_디펜스 {
	public static int N, M, D;
	public static int[][] map;
	public static Queue<Enemy> enemies = new LinkedList<>();
	public static int answer = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmd = br.readLine().split(" ");
		N = Integer.parseInt(nmd[0]);
		M = Integer.parseInt(nmd[1]);
		D = Integer.parseInt(nmd[2]);
		map = new int[N+1][M];
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 1) enemies.add(new Enemy(i, j));
			}
		}
		for(int i=0; i<M; i++) {
			map[N][i] = 2;
		}

		dfs(0, new int[]{0,0,0}, 0);

		if (answer == Integer.MIN_VALUE) {
			System.out.println(0);
			return;
		}
		System.out.println(answer);
	}

	public static void dfs(int index, int[] archer, int num) {
		if (num == 3) {
			playGame(archer);
			return;
		}

		if (index >= M) {
			return;
		}

		dfs(index+1, archer, num);
		archer[num] = index;
		dfs(index+1, archer, num+1);
	}

	public static void playGame(int[] archer) {
		Queue<Enemy> e = clone(enemies);

		int killed = 0;
		while (!e.isEmpty()) {
			int n = e.size();
			killed += shoot(archer, e);

			moveEnemy(e);
		}
		answer = Math.max(killed, answer);
	}

	public static Queue<Enemy> clone(Queue<Enemy> queue) {
		Queue<Enemy> copied = new LinkedList<>();
		int size = queue.size();
		for(int i=0; i<size; i++) {
			Enemy e = queue.poll();
			copied.add(e.copy());
			queue.add(e);
		}
		return copied;
	}

	public static void moveEnemy(Queue<Enemy> queue) {
		int size = queue.size();
		for(int i=0; i<size; i++) {
			Enemy e = queue.poll();
			e.x += 1;

			if (e.x >= N) continue;
			queue.add(e);
		}
	}

	public static int shoot(int[] archer, Queue<Enemy> queue) {
		ArrayList<Enemy> enemies = new ArrayList<>();
		int killed = queue.size();
		for(int i=0; i<archer.length; i++) {
			int x = N;
			int y = archer[i]; // archer 위치
			PriorityQueue<Enemy> pq = new PriorityQueue<Enemy>((e1, e2) -> {
				int dis1 = getDistance(e1.x, e1.y, x, y);
				int dis2 = getDistance(e2.x, e2.y, x, y);
				if (dis1 == dis2) return e1.y-e2.y;
				return dis1-dis2;
			});

			for(int j=0; j<queue.size(); j++) {
				Enemy e = queue.poll();
				pq.add(e);
				queue.add(e);
			}
			Enemy target = pq.poll();
			if (getDistance(target.x, target.y, x, y) <= D) {
				enemies.add(target);
			}
		}

		for(int i=0; i<enemies.size(); i++) {
			Enemy target = enemies.get(i);
			queue.remove(target);
		}

		return killed - queue.size();
	}

	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
}

class Enemy {
	int x;
	int y;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Enemy copy() {
		return new Enemy(this.x, this.y);
	}
}