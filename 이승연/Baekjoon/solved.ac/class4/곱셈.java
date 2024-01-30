import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class °ö¼À {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        long A = Long.parseLong(info[0]);
        long B = Long.parseLong(info[1]);
        long C = Long.parseLong(info[2]);

        System.out.println(recursive(B, A, C));

    }

    public static long recursive(long n, long a, long c) {
        if (n==1) return a%c;

        if (n%2 == 0) {
            long m = recursive(n/2, a, c)%c;
            return (m*m)%c;
        }
        return ((recursive(n-1, a, c)%c)*(recursive(1, a, c)))%c;
    }
}
