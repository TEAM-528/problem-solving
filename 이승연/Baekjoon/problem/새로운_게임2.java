package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 새로운_게임2 {
	public static int N, K;
	public static int[][] map;
	public static int[] dx = {0,0,0,-1,1};
	public static int[] dy = {0,1,-1,0,0};
	public static Queue<Player> queue = new LinkedList<>();
	public static HashMap<Integer, Queue> mapInfo = new HashMap<>();
	public static boolean isEnd = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);
		map = new int[N][N];

		for(int i=0; i<N; i++) {
			String[] l = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(l[j]);
				mapInfo.put(getKey(i, j), new LinkedList());
			}
		}

		for(int i=0; i<K; i++) {
			String[] inp = br.readLine().split(" ");
			int x = Integer.parseInt(inp[0])-1;
			int y = Integer.parseInt(inp[1])-1;
			int dir = Integer.parseInt(inp[2]);

			Player player = new Player(dir, i + 1, x, y);
			queue.add(player);
			Queue q = mapInfo.get(getKey(x, y));
			q.add(player);
			mapInfo.put(getKey(x, y), q);
		}


		int turn = 1;
		while(true) {
			if (turn >= 1000) {
				turn = -1;
				break;
			}
			for(int i=0; i<K; i++) {
				Player player = queue.poll();
				player = movePlayer(player);
				if (isEnd) {
					break;
				}
				queue.add(player);
			}
			if (isEnd) {
				break;
			}
			turn++;
		}

		System.out.println(turn);
	}

	public static Player movePlayer(Player player) {
		int x = player.x+dx[player.dir];
		int y = player.y+dy[player.dir];


		if (x>=N || x<0 || y>=N || y<0 || map[x][y]==2) { // 파란색
			player.dir = changeDir(player.dir); // 방향 바꿈
			x = player.x+dx[player.dir];
			y = player.y+dy[player.dir];

			if (x>=N || x<0 || y>=N || y<0 || map[x][y]==2) { // 그래도 못가면
				return player;
			}

			return movePlayer(player);
		} else if (map[x][y]==1) { // 빨간색
			Queue from = mapInfo.get(getKey(player.x, player.y));
			Queue to = mapInfo.get(getKey(x, y));

			Stack<Player> temp = new Stack<>();
			boolean flag = false;
			int size = from.size();
			for(int i=0; i< size; i++) {
				Player p = (Player)from.poll();
				if (p.num == player.num) {
					flag = true;
				}
				if (flag) {
					p.x = x;
					p.y = y;
					temp.add(p);
				} else {
					from.add(p);
				}
			}

			while (!temp.isEmpty()) {
				Player p = temp.pop();
				to.add(p);
			}

			mapInfo.put(getKey(player.x, player.y), from);
			mapInfo.put(getKey(x, y), to);

			// player 위치 갱신
			player.x = x;
			player.y = y;

			// 말 4개 이상인지
			if (to.size() >= 4) {
				isEnd = true;
			}
		} else if (map[x][y]==0) { // 흰색
			Queue from = mapInfo.get(getKey(player.x, player.y));
			Queue to = mapInfo.get(getKey(x, y));

			boolean flag = false;
			int size = from.size();
			for(int i=0; i<size; i++) {
				Player p = (Player)from.poll();
				if (p.num == player.num) {
					flag = true;
				}
				if (flag) {
					p.x = x;
					p.y = y;
					to.add(p);
				} else {
					from.add(p);
				}
			}

			mapInfo.put(getKey(player.x, player.y), from);
			mapInfo.put(getKey(x, y), to);

			// player 위치 갱신
			player.x = x;
			player.y = y;

			// 말 4개 이상인지
			if (to.size() >= 4) {
				isEnd = true;
			}
		}
		return player;
	}

	public static int getKey(int x, int y) {
		return x*100+y;
	}

	public static int changeDir(int now) {
		/*
		 * public static int[] dx = {0,0,0,-1,1};
		 * public static int[] dy = {0,1,-1,0,0};
		 * */
		if (now == 1) {
			return 2;
		} else if (now == 2) {
			return 1;
		} else if (now == 3) {
			return 4;
		} else if (now == 4) {
			return 3;
		}
		return now;
	}
}
