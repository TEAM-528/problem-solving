/**
 * [Baekjoon] 2294. 동전 2
 * https://www.acmicpc.net/problem/2294
 *
 * 접근 방식
 * 1) 특정 `가치 합`의 최소 동전 개수를 저장하기 위한 dp 배열을 생성한 후, 최대로 가능한 동전 개수가 100,000 이므로 `100,001`로 dp 배열을 채운다.
 * 2) dp[0] = 0으로 채운다.
 * 3) 가치가 `10`인 특정 동전을 추가했을 때의 `가치 합`이 sum이라면, dp[sum] = dp[sum - 10] + 1 이다.
 *    따라서, 동전을 순차적으로 탐색하면서 `dp[sum] = Math.min(dp[sum], dp[sum - value] + 1)`의 점화식으로 dp 배열을 갱신한다.
 * 4) dp[limit]을 반환한다.
 *    이때 dp[limit]이 최댓값(`100,001`)일 때는, 문제에서 제시한 `가치 합`을 만들 수 없다는 뜻이므로 -1을 반환한다.
 */

import java.util.*;
import java.io.*;

public class Main {

    static final int MAX_VALUE = 100_001;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[value + 1];
        Arrays.fill(dp, MAX_VALUE);

        dp[0] = 0;
        for (int i=0; i<N; i++) {
            for (int j=arr[i]; j<=value; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }

        int result = dp[value];
        if (result == MAX_VALUE) { result = -1; }

        System.out.println(result);
    }
}