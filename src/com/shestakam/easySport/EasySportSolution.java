import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EasySportSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X2 = scanner.nextInt();

        int minX0 = Integer.MAX_VALUE;

        for (Integer primeNumbersForX2 : primeNumbersBruteForce(X2 / 2)) {
            if (X2 % primeNumbersForX2 == 0) {
                for (int X1 = X2/primeNumbersForX2 +1; X1 <= X2; X1++) {
                    for (Integer primeNumbersForX1 : primeNumbersBruteForce(X1)) {
                        if (X1 % primeNumbersForX1 == 0) {
                            if (X1/primeNumbersForX1 +1 >=3 && X1/primeNumbersForX1 +1 < minX0) {
                                minX0 = X1/primeNumbersForX1 + 1;
                            }
                        }
                    }

                }
            }
        }

        System.out.println(minX0);
    }


    private static List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        if (n >= 2) {
            primeNumbers.add(2);
        }
        for (int i = 3; i <= n; i += 2) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    private static boolean isPrimeBruteForce(int number) {
        for (int i = 2; i*i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
