import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * http://codeforces.com/contest/1068/problem/B
 */
public class NokSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long b = scanner.nextLong();

        Set<Long> dividers = new HashSet<>();
        for (int  i = 1 ; i <= Math.sqrt(b); i++) {
            if (b % i == 0) {
                dividers.add((long) i);
                dividers.add(b / i);
            }
        }


        System.out.println(dividers.size());
    }
}
