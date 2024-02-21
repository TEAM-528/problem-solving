package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ¡÷ªÁ¿ß_±º∏Æ±‚ {
    public static int N, M, x, y, K;
    public static int[][] map;
    public static int[] dice = new int[7];
    public static int[] dx = new int[]{0,0,0,-1,1};
    public static int[] dy = new int[]{0,1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        x = Integer.parseInt(info[2]);
        y = Integer.parseInt(info[3]);
        K = Integer.parseInt(info[4]);

        map = new int[N][M];
        for (int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j=0; j<line.length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        String[] move = br.readLine().split(" ");
        for(int i=0; i<move.length; i++) {
            int m = Integer.parseInt(move[i]);
            if (roll(m)) {
                System.out.println(dice[3]);
            }

        }
    }

    public static boolean roll(int dir) {
        int newX = x+dx[dir];
        int newY = y+dy[dir];

        if (newX<0 || newX>=N || newY<0 || newY>=M) return false;

        if (dir == 1) { // µø
            int temp = dice[3];
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        } else if (dir == 2) { // º≠
            int temp = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if (dir == 3) { // ∫œ
            int temp = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = temp;
        } else if (dir == 4) { // ≥≤
            int temp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } 

        x = newX;
        y = newY;

        if (map[x][y]==0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }

        return true;
    }
}
