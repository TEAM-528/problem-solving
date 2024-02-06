/**
 * [Baekjoon] 1833. 고속철도 설계하기
 * https://www.acmicpc.net/problem/1833
 *
 * 접근 방식
 * 우선순위 큐와 유니온 파인드 알고리즘으로 문제를 해결했다.
 * 1) 단방향이 아닌 양방향 도로이기 때문에 input으로 주어진 행렬의 원소 중에서 열의 index가 행의 index보다 큰 원소만 고려한다.
 *    이때, 해당 원소의 값이 음수일 때 total 비용에 추가한다.
 *    음수가 아닐 때는 우선순위 큐에 (시작점, 도착점, 비용) 객체를 추가한다. 이때, 우선순위 큐는 비용을 기준으로 오름차순 정렬시킨다.
 * 2) 유니온 파인드에 사용될 arr 배열을 각 index 값으로 초기화한다.
 * 3) arr 배열을 순회하면서, 1번 index의 조상 값이 다른 index의 조상 값과 다르다면 연결이 안된 노드가 있다는 뜻이므로,
 *    우선순위 큐에서 원소를 뽑고 만약 해당 원소의 시작점과 도착점이 연결이 안된 노드라면 연결시켜주고 total 비용에 추가한다.
 * 4) 모든 노드가 연결될 때까지 3번을 반복한다.
 */

import java.util.*;
import java.io.*;

class Main {

    static class Route {
        int src, dest, price;

        public Route(int src, int dest, int price) {
            this.src = src;
            this.dest = dest;
            this.price = price;
        }
    }

    static int N, total;
    static int[] arr;
    static PriorityQueue<Route> pq;

    private static void input() throws IOException {
        total = 0;
        pq = new PriorityQueue<>((o1, o2) -> o1.price - o2.price);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i=0; i<=N; i++) {
            arr[i] = i;
        }
        StringTokenizer st;
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                int price = Integer.parseInt(st.nextToken());
                if (i >= j) {
                    continue;
                }
                if (price < 0) {
                    total += Math.abs(price);
                    union(i, j);
                } else {
                    pq.add(new Route(i, j, price));
                }
            }
        }
    }

    private static void union(int a, int b) {
        int v1 = find(a);
        int v2 = find(b);
        if (v1 <= v2) {
            arr[v2] = v1;
        } else {
            arr[v1] = v2;
        }
    }

    private static int find(int a) {
        if (a == arr[a]) {
            return a;
        }
        return arr[a] = find(arr[a]);
    }

    private static boolean isAllConnected() {
        int value = find(1);
        for (int i=2; i<=N; i++) {
            if (find(i) != value) {
                return false;
            }
        }
        return true;
    }

    public static void main (String[] args) throws IOException {
        input();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!isAllConnected()) {
            Route route;
            do {
                route = pq.remove();
            } while (find(route.src) == find(route.dest));
            union(route.src, route.dest);
            total += route.price;
            sb.append(route.src).append(" ").append(route.dest).append("\n");
            count++;
        }
        System.out.println(total + " " + count);
        System.out.print(sb);
    }
}