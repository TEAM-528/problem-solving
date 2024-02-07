package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 단지번호붙이기 {
    public static int[][] map;
    public static ArrayList<Integer> arr = new ArrayList<>();
    public static int[] dx = new int[]{0,0,1,-1};
    public static int[] dy = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    arr.add(dfs(i, j, 1));   
                }
            }
        }
        
        Collections.sort(arr);
        System.out.println(arr.size());
        for(int i=0; i<arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }

    public static int dfs(int x, int y, int size) {
        for(int i=0; i<4; i++) {
            int newX = x+dx[i];
            int newY = y+dy[i];
            if (newX>=0 && newX<map.length && newY>=0 && newY<map[0].length && map[newX][newY]==1) {
                map[newX][newY] = 0;
                size = dfs(newX, newY, size+1);
            }
        }
        return size;
    }
}
