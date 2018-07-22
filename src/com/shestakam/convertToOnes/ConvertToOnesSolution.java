import java.math.BigInteger;\
import java.util.Scanner;

public class ConvertToOnesSolution {
    static int n;
    static int reverseCost;
    static int inverseCost;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        reverseCost = scanner.nextInt();
        inverseCost = scanner.nextInt();

        String text = scanner.next();

        int zeroGroupsCount = 0;
        for (String s : text.split("1")) {
            if (s.trim().length() != 0) {
                zeroGroupsCount++;
            }
        }

        if (zeroGroupsCount == 0) {
            System.out.println("0");
        } else {
            System.out.println((BigInteger.valueOf(zeroGroupsCount - 1).multiply(BigInteger.valueOf(Math.min(reverseCost, inverseCost)))
                    .add(BigInteger.valueOf(inverseCost))));
        }
    }
}