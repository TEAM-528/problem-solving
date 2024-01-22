import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] numbers = new int[N][3];
        for(int i=0; i<N; i++) {
            String[] parsedNum = br.readLine().split(" ");
            numbers[i][0] = Integer.parseInt(parsedNum[0]);
            numbers[i][1] = Integer.parseInt(parsedNum[1]);
            numbers[i][2] = Integer.parseInt(parsedNum[2]);
        }

        int[][] dpMax = new int[N+1][3];
        for(int i=1; i<N+1; i++) {
            dpMax[i][0] = numbers[i-1][0] + Math.max(dpMax[i-1][0], dpMax[i-1][1]);
            dpMax[i][1] = numbers[i-1][1] + Math.max(Math.max(dpMax[i-1][0], dpMax[i-1][1]), dpMax[i-1][2]);
            dpMax[i][2] = numbers[i-1][2] + Math.max(dpMax[i-1][1], dpMax[i-1][2]);
        }

        int[][] dpMin = new int[N+1][3];
        for(int i=1; i<N+1; i++) {
            dpMin[i][0] = numbers[i-1][0] + Math.min(dpMin[i-1][0], dpMin[i-1][1]);
            dpMin[i][1] = numbers[i-1][1] + Math.min(Math.min(dpMin[i-1][0], dpMin[i-1][1]), dpMin[i-1][2]);
            dpMin[i][2] = numbers[i-1][2] + Math.min(dpMin[i-1][1], dpMin[i-1][2]);
        }

        int maxScore = Integer.MIN_VALUE;
        for(int i=0; i<3; i++) {
            maxScore = Math.max(dpMax[N][i], maxScore);
        }

        int minScore = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            minScore = Math.min(dpMin[N][i], minScore);
        }

        System.out.println(maxScore+" "+minScore);
    }
}
