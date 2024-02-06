package Baekjoon.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 시험_감독 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N+1];
        String[] info = br.readLine().split(" ");
        for(int i=1; i<num.length; i++) {
            num[i] = Integer.parseInt(info[i-1]);
        }
        String[] bc = br.readLine().split(" ");
        int B = Integer.parseInt(bc[0]);
        int C = Integer.parseInt(bc[1]);
        long answer = 0;
        for(int i=1; i<num.length; i++) {
            num[i] -= B;
            answer++;
        }
        for(int i=1; i<num.length; i++) {
            if (num[i]>0) {
                answer += Math.ceil((double)num[i]/(double)C);
            }
        }
        System.out.println(answer);
    }
}
