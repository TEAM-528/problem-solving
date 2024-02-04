package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.function.Function;

public class 고속철도_설계하기 {
    public static int N;
    public static int C = 0;
    public static int M = 0;

    public static ArrayList<Node> arr = new ArrayList<>();
    public static ArrayList<int[]> cities = new ArrayList<>();
    public static int[] group;

    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Function<String,Integer> stoi = Integer::parseInt;
        N  = stoi.apply(st.nextToken());
        map = new int[N+1][N+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<N ; j++){
                int cost = stoi.apply(st.nextToken());
                if (i != j) {
                    arr.add(new Node(cost, i+1, j+1));
                    map[i+1][j+1] = cost;
                }
            }
        }
        arr.add(new Node(0, 0, 0));

        Collections.sort(arr, (Node o1, Node o2) -> {
            return o1.getCost()-o2.getCost();
        });

        group = new int[N+1];
        for(int i=1; i<group.length; i++) {
            group[i] = i;
        }

        kruskal();
        System.out.println(C+" "+M);
        for(int i=0; i<cities.size(); i++) {
            int[] c = cities.get(i);
            System.out.println(c[0]+" "+c[1]);
        }
    }

    public static void kruskal() {
        for(int i=0; i<arr.size(); i++) {
            Node next = arr.get(i);
            int[] node = next.getNode();
            if (next.getCost() == 0) {
                C /= 2;
                continue;
            }

            int group1 = getGroup(node[0]);
            int group2 = getGroup(node[1]);

            if (next.getCost() < 0) {
                C += Math.abs(next.getCost());
            }
            if (group1 == group2) {
                continue;
            }
            if (group1 > group2) {
                group[group1] = group2;
            } else {
                group[group2] = group1;
            }
        
            if (next.getCost() > 0) {
                C += next.getCost();
                M++;
                cities.add(new int[]{node[0], node[1]});
            };
        }
    }

    public static int getGroup(int x) {
        if (group[x] == x) {
            return x;
        }
        return getGroup(group[x]);
    }

    public static void prim() {
        int curNode = 1;
        int count = N;
        int CforMinus = 0;
        while (count != 0) {
            int nextNode = 0;
            int minCost = Integer.MAX_VALUE;
            for(int i=1; i<map[curNode].length; i++)  {
                if (map[curNode][i] == 0) continue;
                if (i!=curNode && map[curNode][i]<0) {
                    CforMinus -= map[curNode][i];
                }
                if (minCost > Math.abs(map[curNode][i])) {
                    nextNode = i;
                    minCost = Math.abs(map[curNode][i]);
                }
            }

            if (map[curNode][nextNode] > 0) {
                M++;
                cities.add(new int[]{curNode, nextNode});
                C += map[curNode][nextNode];
            } 
            curNode = nextNode;
            count--;
        }

        C += (CforMinus/2);
    }

    public static class Node {
        private int cost;
        private int node1;
        private int node2;

        public Node(int cost, int node1, int node2) {
            this.cost = cost;
            this.node1 = node1;
            this.node2 = node2;
        }

        public int getCost() {
            return cost;
        }

        public int[] getNode() {
            return new int[]{node1, node2};
        }
    }

}