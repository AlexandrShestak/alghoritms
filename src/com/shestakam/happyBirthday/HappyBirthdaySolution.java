import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class HappyBirthdaySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal n = new BigDecimal(scanner.next());
        BigDecimal m = new BigDecimal(scanner.next());
        BigDecimal k = new BigDecimal(scanner.next());
        BigDecimal l = new BigDecimal(scanner.next());

        if (m.compareTo(n) > 0 || l.compareTo(n) > 0) {
            System.out.println(-1);
            return;
        }

        BigDecimal result = l.add(k).divide(m, RoundingMode.UP);
        if (result.multiply(m).compareTo(n) > 0) {
            System.out.println(-1);
        } else {
            System.out.println(result.toBigInteger());
        }
    }
}
