import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class 줄_세우기 {
    static int N;
    static int[] children;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        children = new int[N+1];
        String[] parsed = br.readLine().split(" ");
        for(int i=0; i<parsed.length; i++) {
            children[Integer.parseInt(parsed[i])] = i;
        }

        int answer = Integer.MIN_VALUE;
        int count = 1;
        for(int i=1; i<N; i++) {
            if (children[i]<children[i+1]) {
                count++;
            } else {
                answer = Math.max(answer, count);
                count = 1;
            }
        }

        if (answer == Integer.MIN_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(N-answer);
    }
}
