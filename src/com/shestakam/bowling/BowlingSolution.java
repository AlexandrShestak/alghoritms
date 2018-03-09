import java.util.Scanner;

public class BowlingSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int l = scanner.nextInt();
        int r = scanner.nextInt();
        int a = scanner.nextInt();

        int minLR = Math.min(l, r);
        int maxLR = Math.max(l, r);
        int diff = maxLR - minLR;

        if (a == 0) {
            System.out.println(minLR);
        } else if (diff == 0) {
            System.out.println(minLR*2 + a/2 *2);
        } else {
            if (a < diff) {
                System.out.println((minLR + a)*2);
            } else {
                System.out.println(maxLR*2 + (a-diff)/2*2);
            }
        }
    }
}
