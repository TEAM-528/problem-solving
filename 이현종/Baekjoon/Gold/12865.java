/**
 * [Baekjoon] 12865. 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 *
 * 접근 방식
 * 1) 순차적으로 product를 순회하면서 해당 product를 담을 경우와 담지 않았을 때의 경우의 수를 모두 고려하기 위해 다음과 같이 진행한다.
 *    - 행은 순차적으로 탐색한 product의 index를 의미
 *    - 열은 가방에 담을 수 있는 최대 무게
 * 2) dp[N][K] = Math.max(dp[N-1][K], dp[N-1][K - `해당 product 무게`] + `해당 product 가치`) 점화식을 바탕으로 dp 배열을 채워나간다.
 * 3) dp[N][limit]을 출력한다.
 */

import java.util.*;
import java.io.*;

class Main {

    static class Product {
        int weight, value;

        public Product(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static int N, limit;
    static int[][] dp;
    static List<Product> products;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][limit + 1];
        products = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            products.add(new Product(weight, value));
        }
    }

    public static void main (String[] args) throws IOException {
        input();
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=limit; j++) {
                Product product = products.get(i - 1);
                if (j - product.weight < 0) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - product.weight] + product.value);
            }
        }
        System.out.println(dp[N][limit]);
    }
}