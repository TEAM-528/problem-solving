/**
 * [Baekjoon] 1202. 보석 도둑
 * https://www.acmicpc.net/problem/1202
 *
 * 접근 방식
 * 1) 보석 가치를 기준으로 내림차순 정렬하는 우선순위 큐를 선언한다.
 * 2) 가방에 담을 수 있는 무게가 낮은 가방 부터 순차적으로 탐색하여, 해당 가방에 담을 수 있는 보석들을 1번에 선언한 우선순위 큐에 추가한다.
 * 3) 우선순위 큐에서 보석 한개를 꺼내면 해당 가방에 넣을 수 있는 가치가 최대인 보석이기 때문에, total에 해당 보석 가치를 더한다.
 * 4) 2~3번을 반복한다.
 * 5) total을 반환한다.
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