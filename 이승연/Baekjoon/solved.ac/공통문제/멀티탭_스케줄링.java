import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 멀티탭_스케줄링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        String[] nstring = br.readLine().split(" ");
        int[] n = new int[K];
        for(int i=0; i<nstring.length; i++) {
            n[i] = Integer.parseInt(nstring[i]);
        }
        boolean[] connected = new boolean[K+1];
        int left = N;
        int answer = 0;
        for(int i=0; i<n.length; i++) {
            if (left!=0 && !connected[n[i]]) {
                connected[n[i]] = true;
                left--;
                continue;
            }
            if (connected[n[i]]) continue;

            int[] count = new int[K+1];
            for(int j=i+1; j<n.length; j++) {
                if (connected[n[j]]) {
                    count[n[j]]++;
                }
            }

            answer++;
            boolean isBreak = false;
            for(int j=1; j<connected.length; j++) {
                if (connected[j] && count[j]==0) {
                    connected[j] = false;
                    connected[n[i]] = true;
                    isBreak = true;
                    break;
                }
            }

            if (isBreak) continue;

            int idx = 0;
            boolean[] visited = new boolean[K+1];
            for(int j=i+1; j<n.length; j++) {
                if (connected[n[j]] && !visited[n[j]]) {
                    idx = n[j];
                    visited[n[j]] = true;
                }
            }
            connected[idx] = false;
            connected[n[i]] = true;
        }


        System.out.println(answer);
    }
}
