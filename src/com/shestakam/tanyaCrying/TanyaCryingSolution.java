import java.util.Scanner;

public class TanyaCryingSolution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long k = sc.nextInt();
        long A = sc.nextInt();
        long B = sc.nextInt();

        if (k == 1) {
            System.out.println((n - 1)*A);
        } else {
            long spent = 0;
            while (n != 1) {
                if (n < k) {
                    spent += (n-1)*A;
                    n = 1;
                } else if (n % k != 0) {
                    spent += (n % k)*A;
                    n -= n % k;
                } else {
                    spent += Math.min(B, (n - n / k)*A);
                    n /= k;
                }
            }
            System.out.println(spent);
        }
    }
}
