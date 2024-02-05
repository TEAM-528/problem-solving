package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 색종이_붙이기 {
    public static int[][] map = new int[10][10];
    public static boolean[][] visited = new boolean[10][10];
    public static int[] numOfPaper = new int[]{0,5,5,5,5,5};
    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<10; i++) {
            String[] inp = br.readLine().split(" ");
            for (int j=0; j<10; j++) {
                map[i][j] = Integer.parseInt(inp[j]);
            }
        }

        DFS(0, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }   

    public static void changePaper(int size, int x, int y, boolean status) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                visited[i][j] = status;
            }
        }
    }

    public static boolean isAttach(int size, int x, int y) {
        if (x+size>10 || y+size>10) return false;
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if (map[i][j] == 0 || visited[i][j]) return false;
            }
        }
        return true;
    }

    public static void DFS(int x, int y, int cnt) {
        if (x>9 && y==0) {
            answer = Math.min(cnt, answer);
            return;
        }
        
        if (answer < cnt) return;

        if (visited[x][y] || map[x][y]==0) {
            if (y==9) {
                DFS(x+1, 0, cnt);
            } else {
                DFS(x, y+1, cnt);
            }
            return;
        }
        for(int i=5; i>0; i--) {
            if (numOfPaper[i]>0) {
                if(isAttach(i, x, y)) {
                    numOfPaper[i]--;
                    changePaper(i, x, y, true);
                    if (y==9) {
                        DFS(x+1, 0, cnt+1);
                    } else {
                        DFS(x, y+1, cnt+1);
                    }
                    numOfPaper[i]++;
                    changePaper(i, x, y, false);
                }
            }
        }
    }
}
