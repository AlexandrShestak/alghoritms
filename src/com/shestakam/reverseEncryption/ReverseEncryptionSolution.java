import java.util.Scanner;

public class ReverseEncryptionSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder text = new StringBuilder(scanner.next());
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                text = new StringBuilder(text.substring(0, i)).reverse().append(text.substring(i));
            }
        }
        System.out.println(text.reverse());
    }
}
