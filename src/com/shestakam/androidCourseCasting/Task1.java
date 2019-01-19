import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long s = scanner.nextLong();
        long l1 = scanner.nextLong();
        long r1 = scanner.nextLong();
        long l2 = scanner.nextLong();
        long r2 = scanner.nextLong();

        if (r1 + r2 < s || l1 + l2 > s) {
            System.out.println(-1);
            return;
        }

        //l1 <= x1 <= r1
        //l2 <= x2 <= r2
        //x1+x2 = s

        //x1 = s-x2 -> min => x2 -> max

        //l1 <= s-x2 <= r1
        //l2 <= x2 <= r2


        //-r1 <= x2 <= s-l1
        //l2 <= x2 <= r2       x2 -> max  =>   x2 = min(r2, s-l1)

        long x2 = Math.min(r2, s-l1);
        System.out.println("" + (s-x2) + " " +x2);
    }
}