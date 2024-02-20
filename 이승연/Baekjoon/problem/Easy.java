package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Easy {
    public static int[][] map;
    public static int block;
    public static int[] dx = new int[]{0,0,1,-1};
    public static int[] dy = new int[]{1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        block = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            String[] num = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(num[j]);
                block = Math.max(block, map[i][j]);
            }
        }

        bfs();
        System.out.println(block);
    }

    public static void bfs() {
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, map));

        while(!queue.isEmpty()) {
            Status status = queue.poll();

            if (status.count == 5) break;   

            for(int i=0; i<4; i++) {
                Status next = move(i, status.map, status.count+1);
                block = Math.max(block, next.block);
                queue.add(next);
            }

        }

    }

    public static Status move(int dir, int[][] map, int count) {
        int block_num = Integer.MIN_VALUE;
        int[][] newMap = new int[map.length][map[0].length];
        if (dir == 0) { // 왼쪽으로 기울임
            for(int i=0; i<map.length; i++) {
                Stack<Integer> stack = new Stack<>();
                for(int j=0; j<map[0].length; j++) {
                    if (map[i][j] != 0) stack.push(map[i][j]);
                }

                int idx = map[i].length-1;
                while(!stack.isEmpty()) {
                    int num = stack.pop();
                    if (!stack.isEmpty()){
                        if (stack.peek() == num) {
                            newMap[i][idx] = num*2;
                            stack.pop();
                        } else {
                            newMap[i][idx] = num;
                        }
                        block_num = Math.max(block_num, newMap[i][idx]);
                        idx--;
                    } else {
                        newMap[i][idx] = num;
                    }
                }
            }
        } else if (dir == 1) { // 오른쪽으로 기울임
            for(int i=0; i<map.length; i++) {
                Stack<Integer> stack = new Stack<>();
                for(int j=map[0].length-1; j>=0; j--) {
                    if (map[i][j] != 0) stack.push(map[i][j]);
                }

                int idx = 0;
                while(!stack.isEmpty()) {
                    int num = stack.pop();
                    if (!stack.isEmpty()){
                        if (stack.peek() == num) {
                            newMap[i][idx] = num*2;
                            stack.pop();
                        } else {
                            newMap[i][idx] = num;
                        }
                        block_num = Math.max(block_num, newMap[i][idx]);
                        idx++;
                    } else {
                        newMap[i][idx] = num;
                    }
                }
            }
        } else if (dir == 2) { // 아래로 기울임
            for(int i=0; i<map[0].length; i++) {
                Stack<Integer> stack = new Stack<>();
                for(int j=0; j<map.length; j++) {
                    if (map[j][i] != 0) stack.push(map[j][i]);
                }

                int idx = map[i].length-1;
                while(!stack.isEmpty()) {
                    int num = stack.pop();
                    if (!stack.isEmpty()){
                        if (stack.peek() == num) {
                            newMap[idx][i] = num*2;
                            stack.pop();
                        } else {
                            newMap[idx][i] = num;
                        }
                        block_num = Math.max(block_num, newMap[idx][i]);
                        idx--;
                    } else {
                        newMap[idx][i] = num;
                    }
                }
            }
        } else if (dir == 3) { // 위로 기울임
            for(int i=0; i<map[0].length; i++) {
                Stack<Integer> stack = new Stack<>();
                for(int j=map.length-1; j>=0; j--) {
                    if (map[j][i] != 0) stack.push(map[j][i]);
                }

                int idx = 0;
                while(!stack.isEmpty()) {
                    int num = stack.pop();
                    if (!stack.isEmpty()){
                        if (stack.peek() == num) {
                            newMap[idx][i] = num*2;
                            stack.pop();
                        } else {
                            newMap[idx][i] = num;
                        }
                        block_num = Math.max(block_num, newMap[idx][i]);
                        idx++;
                    } else {
                        newMap[idx][i] = num;
                    }
                }
            }
        }
        return new Status(count, block_num, newMap);
    }
}

class Status {
    int count;
    int block;
    int[][] map;

    public Status(int count, int block, int[][] map) {
        this.count = count;
        this.block = block;
        this.map = map;
    }

    public void print() {
        System.out.println();
        System.out.println("count: "+count);
        System.out.println("block: "+block);
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
