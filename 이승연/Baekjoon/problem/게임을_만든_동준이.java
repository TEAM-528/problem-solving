package Baekjoon.problem;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 게임을_만든_동준이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] level = new int[N];
        int maxLevel = 0;
        for(int i=0; i<N; i++) {
            level[i] = Integer.parseInt(br.readLine());
            maxLevel = level[i];
        }

        int ans = 0;
        for(int i=N-2; i>=0; i--) {
            if (level[i]-maxLevel > 0) {
                ans += (level[i]-maxLevel);
                ans++;
                maxLevel--;
                continue;
            } else if (level[i]-maxLevel == 0) {
                ans++;
                maxLevel--;
            } else if (level[i]-maxLevel < 0) {
                maxLevel = level[i];
            }
        }

        System.out.println(ans);

    }
}
