package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 사회망_서비스 {
    static int N;
    static ArrayList<List<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        for(int i=0; i<N+1; i++) {
            tree.add(new ArrayList<>());
        }
        for(int i=0; i<N-1; i++) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0;
        dp[number][1] = 1;

        List<Integer> child = tree.get(number);
        for(int i=0; i<child.size(); i++) {
            if (!visited[child.get(i)]) {
                dfs(child.get(i));
                dp[number][0] += dp[child.get(i)][1];
                dp[number][1] += Math.min(dp[child.get(i)][0], dp[child.get(i)][1]);
            }
        }
    }

}
