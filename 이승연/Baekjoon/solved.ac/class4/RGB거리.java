import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] color = new int[N][3];
        for (int i=0; i<N; i++) {
            String[] rgb = br.readLine().split(" ");
            int R = Integer.parseInt(rgb[0]);
            int G = Integer.parseInt(rgb[1]);
            int B = Integer.parseInt(rgb[2]);
            color[i][0] = R;
            color[i][1] = G;
            color[i][2] = B;
        }

        int[][] dp = new int[N][3];
        dp[0][0] = color[0][0];
        dp[0][1] = color[0][1];
        dp[0][2] = color[0][2];
        for(int i=1; i<N; i++) {
            dp[i][0] = color[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = color[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = color[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            answer = Math.min(dp[N-1][i], answer);
        }

        System.out.println(answer);
    }
}
