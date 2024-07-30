import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {
  public static int N;
  public static int answer;
  public static int[] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N];

    dfs(0);
    System.out.println(answer);
  }

  public static void dfs(int depth) {
    if (depth == N) {
      answer++;
      return;
    }

    for(int i=0; i<N; i++) {
      if (canGo(depth, i)) {
        map[depth] = i;
        dfs(depth+1);
      }
    }
  }

  public static boolean canGo(int row, int col) {
    for(int i=0; i<row; i++) {
      //int r = i;
      //int c = map[i];
      if (row == i || col == map[i]) return false;
      if (Math.abs(row-i) == Math.abs(col-map[i])) return false;
    }
    return true;
  }
}
