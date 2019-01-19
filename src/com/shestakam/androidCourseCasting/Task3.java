import java.text.DecimalFormat;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int ai = scanner.nextInt();
            if (ai < minValue) {
                minValue = ai;
            }
        }

        System.out.println(new DecimalFormat("#0.000000000").format(minValue));
    }
}

