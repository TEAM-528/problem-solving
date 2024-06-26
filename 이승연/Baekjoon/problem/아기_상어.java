package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 아기_상어 {
    static int N;
    static int[][] map;
    static int x, y;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static int size = 2;
    static int numOfFish = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i< map.length; i++) {
            String[] m = br.readLine().split(" ");
            for(int j=0; j<m.length; j++) {
                map[i][j] = Integer.parseInt(m[j]);
                if (map[i][j] == 9) {
                    x = i;
                    y = j;
                    continue;
                }
                if (map[i][j] != 0) {
                    numOfFish++;
                }
            }
        }

        int time = 0;
        int count = 0;
        while(numOfFish>0) {
            int[] next = bfs(0,x,y);
            if (next==null) {
                break;
            }
            numOfFish--;
            x = next[1];
            y = next[2];
            count++;
            if (count==size) {
                count=0;
                size++;
            }
            time += next[0];
            map[next[1]][next[2]] = 0;
        }
        System.out.println(time);
    }

    public static int[] bfs(int distance, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{distance, x, y});
        map[x][y] = 0;

        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;

        int dis = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1]==o2[1]) {
                return o1[2]-o2[2];
            }
            return o1[1]-o2[1];
        });
        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            for(int i=0; i<4; i++) {
                int newX = p[1]+dx[i];
                int newY = p[2]+dy[i];
                if (newX>=0 && newX<N && newY>=0 && newY<N) {
                    if (map[newX][newY]>0 && map[newX][newY]<size) {
                        if (dis == p[0]+1) {
                            pq.add(new int[]{dis, newX, newY});
                            continue;
                        }
                        if (dis > p[0]+1) {
                            dis = p[0]+1;
                            pq = new PriorityQueue<>((o1, o2) -> {
                                if (o1[1]==o2[1]) {
                                    return o1[2]-o2[2];
                                }
                                return o1[1]-o2[1];
                            });
                            pq.add(new int[]{dis, newX, newY});
                        }
                    }

                    if (map[newX][newY]<=size && !visited[newX][newY]) {
                        queue.add(new int[]{p[0]+1, newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }


        if (pq.isEmpty()) {
            return null;
        }
        return pq.poll();
    }
}
