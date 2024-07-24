import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 궁금한_민호 {
  public static int N;
  public static int[][] map;
  public static int[][] arr;
  public static int answer = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    arr = new int[N][N];
    for(int i=0; i<N; i++) {
      String[] s = br.readLine().split(" ");
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(s[j]);
      }
    }

    for(int k=0; k<N; k++) {
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          if (map[i][j]>map[i][k]+map[k][j]) {
            System.out.println(-1);
            return;
          }
          if (i==k || k==j) continue;
          if (map[i][j] == map[i][k]+map[k][j]) {
            arr[i][j] = -1;
          }
        }
      }
    }

    for(int i=0; i<N; i++) {
      for(int j=i; j<N; j++) {
        if(arr[i][j] != -1) {
          answer += map[i][j];
        }
      }
    }
    System.out.println(answer);
  }
}
