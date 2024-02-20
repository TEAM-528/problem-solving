package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 구슬_탈출2 {
    public static int N,M;
    public static char[][] MAP;
    public static int[] dx = new int[]{0,0,1,-1};
    public static int[] dy = new int[]{1,-1,0,0};
    public static int RED_X, RED_Y, BLUE_X, BLUE_Y, FINAL_X, FINAL_Y;
    public static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        MAP = new char[N][M];
        visited = new boolean[N][M][N][M];

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                MAP[i][j] = line.charAt(j);
                if (MAP[i][j]=='B') {
                    BLUE_X = i;
                    BLUE_Y = j;
                } else if (MAP[i][j]=='R') {
                    RED_X = i;
                    RED_Y = j;
                } else if (MAP[i][j]=='O') {
                    FINAL_X = i;
                    FINAL_Y = j;
                }
            }
        }
        MAP[RED_X][RED_Y] = '.';
        MAP[BLUE_X][BLUE_Y] = '.';
        visited[RED_X][RED_Y][BLUE_X][BLUE_Y] = true;
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, new int[]{BLUE_X, BLUE_Y}, new int[]{RED_X, RED_Y}));

        int result = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Status status = queue.poll();
            int[] blue = status.getBlue();
            int[] red = status.getRed();
            

            if (status.getCount() == 10) {
                break;
            }

            for(int i=0; i<4; i++) {
                if ((blue[0]==red[0]) && (dy[i]!=0)) {
                    if ((dy[i]==1 && red[1]-blue[1]>0) || (dy[i]==-1 && red[1]-blue[1]<0)) {  // red 먼저
                        int[] newRed = move(i, red[0], red[1], blue, red);
                        int[] newBlue = move(i, blue[0], blue[1], blue, newRed);

                        if (MAP[newBlue[0]][newBlue[1]] == 'O') continue;
                        if (MAP[newRed[0]][newRed[1]] == 'O') result = Math.min(result, status.getCount()+1);

                        if (!visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]]) {
                            queue.add(new Status(status.getCount()+1, newBlue, newRed));
                            visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]] = true;
                        }
                    } 
                    if (((dy[i]==1 && red[1]-blue[1]<0) || (dy[i]==-1 && red[1]-blue[1]>0))) { // blue 먼저
                        int[] newBlue = move(i, blue[0], blue[1], blue, red);
                        int[] newRed = move(i, red[0], red[1], newBlue, red);


                        if (MAP[newBlue[0]][newBlue[1]] == 'O') continue;
                        if (MAP[newRed[0]][newRed[1]] == 'O') result = Math.min(result, status.getCount()+1);

                        if (!visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]]) {
                            queue.add(new Status(status.getCount()+1, newBlue, newRed));
                            visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]] = true;
                        }
                    }
                } else if ((blue[1]==red[1]) && (dx[i]!=0)) {
                    if ((dx[i]==1 && red[0]-blue[0]>0) || (dx[i]==-1 && red[0]-blue[0]<0)) {  // red 먼저
                        int[] newRed = move(i, red[0], red[1], blue, red);
                        int[] newBlue = move(i, blue[0], blue[1], blue, newRed);

                        if (MAP[newBlue[0]][newBlue[1]] == 'O') continue;
                        if (MAP[newRed[0]][newRed[1]] == 'O') result = Math.min(result, status.getCount()+1);

                        if (!visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]]) {
                            queue.add(new Status(status.getCount()+1, newBlue, newRed));
                            visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]] = true;
                        }
                    } 
                    if (((dx[i]==1 && red[0]-blue[0]<0) || (dx[i]==-1 && red[0]-blue[0]>0))) { // blue 먼저
                        int[] newBlue = move(i, blue[0], blue[1], blue, red);
                        int[] newRed = move(i, red[0], red[1], newBlue, red);

                        if (MAP[newBlue[0]][newBlue[1]] == 'O') continue;
                        if (MAP[newRed[0]][newRed[1]] == 'O') result = Math.min(result, status.getCount()+1);

                        if (!visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]]) {
                            queue.add(new Status(status.getCount()+1, newBlue, newRed));
                            visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]] = true;
                        }
                    }
                } else { // 상관없음
                    int[] newRed = move(i, red[0], red[1], blue, red);
                    int[] newBlue = move(i, blue[0], blue[1], blue, newRed);

                    if (MAP[newBlue[0]][newBlue[1]] == 'O') continue;
                    if (MAP[newRed[0]][newRed[1]] == 'O') result = Math.min(result, status.getCount()+1);

                    if (!visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]]) {
                        queue.add(new Status(status.getCount()+1, newBlue, newRed));
                        visited[newRed[0]][newRed[1]][newBlue[0]][newBlue[1]] = true;
                    }
                }
            }

        }

        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }


    public static int[] move(int dir, int x, int y, int[] blue, int[] red) {
        while(true) {
            int newX = x+dx[dir];
            int newY = y+dy[dir];
            if(newX<0 || newX>=N || newY<0 || newY>=M) break;
            if (MAP[newX][newY]=='#') break;
            if (red[0]==newX && red[1]==newY && MAP[newX][newY]!='O') break;
            if (blue[0]==newX && blue[1]==newY && MAP[newX][newY]!='O') break;

            x = newX;
            y = newY;
            if (MAP[x][y] == 'O') {
                break;
            }
        }

        return new int[]{x, y};
    }
}

class Status {
    int count;
    int[] blue;
    int[] red;

    public Status(int count, int[] blue, int[] red) {
        this.count = count;
        this.blue = blue;
        this.red = red;
    }

    public int getCount() {
        return this.count;
    }

    public int[] getBlue() {
        return this.blue;
    }

    public int[] getRed() {
        return this.red;
    }
}