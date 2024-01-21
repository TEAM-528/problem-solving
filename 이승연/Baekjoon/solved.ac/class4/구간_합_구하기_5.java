import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 구간_합_구하기_5 {
    static int N, M;
    static int[][] arr, dp;
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            String[] values = br.readLine().split(" ");
            for(int j=1; j<N+1; j++) {
                arr[i][j] = Integer.parseInt(values[j-1]);
            }
        }

        dp[1][1] = arr[1][1];
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+arr[i][j];
            }
        }

        for(int i=0; i<M; i++) {
            str = br.readLine().split(" ");
            int x1 = Integer.parseInt(str[0]);
            int y1 = Integer.parseInt(str[1]);
            int x2 = Integer.parseInt(str[2]);
            int y2 = Integer.parseInt(str[3]);

            System.out.println(dp[x2][y2]-dp[x2][y1-1]-dp[x1-1][y2]+dp[x1-1][y1-1]);
        }
    }
}
