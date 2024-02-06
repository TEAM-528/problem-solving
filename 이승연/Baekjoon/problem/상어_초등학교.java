package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 상어_초등학교 {
    public static int N;
    public static int[][] seat;
    public static int[] dx = new int[]{0,0,1,-1};
    public static int[] dy = new int[]{1,-1,0,0};
    public static boolean[][] like;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seat = new int[N][N];

        like = new boolean[N*N+1][N*N+1];
        ArrayList<Integer> students = new ArrayList<>();
        for(int i=0; i<N*N; i++) {
            String[] s = br.readLine().split(" ");
            int student = Integer.parseInt(s[0]);
            like[student][Integer.parseInt(s[1])] = true;
            like[student][Integer.parseInt(s[2])] = true;
            like[student][Integer.parseInt(s[3])] = true;
            like[student][Integer.parseInt(s[4])] = true;
            students.add(student);
        }

        for(int i=0; i<students.size(); i++) {
            int[] position = getPosition(students.get(i));
            seat[position[0]][position[1]] = students.get(i);
        }

        int answer = 0;
        for(int i=0; i<seat.length; i++) {
            for(int j=0; j<seat[0].length; j++) {
                answer += getScore(i, j);
            }
        }
        System.out.println(answer);
    }

    public static int[] getPosition(int student) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2)->{
            if (o2[2]==o1[2]) {
                if (o2[3]==o1[3]) {
                    if (o1[0]==o2[0]) {
                        return o1[1]-o2[1];
                    }
                    return o1[0]-o2[0];
                }
                return o2[3]-o1[3];
            }
            return o2[2] - o1[2];
        });

        for(int i=0; i<seat.length; i++) {
            for(int j=0; j<seat[0].length; j++) {
                if (seat[i][j] == 0) {
                    int likeStudent = 0;
                    int emptyPlace = 0;
                    for(int k=0; k<4; k++) {
                        int newX = i+dx[k];
                        int newY = j+dy[k];
                        if (newX>=0 && newX<N && newY>=0 && newY<N) {
                            if (seat[newX][newY]!=0 && like[student][seat[newX][newY]]) {
                                likeStudent++;
                            }
                            if (seat[newX][newY]==0) emptyPlace++;
                        }
                    }
                    pq.add(new int[]{i,j,likeStudent,emptyPlace});
                }
            }
        }   

        int[] poll = pq.poll();
        return new int[]{poll[0], poll[1]};
    }

    public static int getScore(int x, int y) {
        int result = 0;
        for(int i=0; i<4; i++) {
            int newX = x+dx[i];
            int newY = y+dy[i];
            if (newX>=0 && newX<N && newY>=0 && newY<N && like[seat[x][y]][seat[newX][newY]]) {
                result++;
            }
        }
        return (int)Math.pow(10, result-1);
    }
}
