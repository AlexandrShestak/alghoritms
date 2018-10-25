import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * http://codeforces.com/contest/1073/problem/A
 */
public class VariousSubstringSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = scanner.next();


        Map<Character, Integer> characterCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            characterCount.merge(c, 1, (a, b) -> a + b);
        }

        if (characterCount.size() == 1) {
            System.out.println("NO");
            return;
        } else {
            for (int i = 0 ; i < n-1 ; i++) {
                if (str.charAt(i) != str.charAt(i+1)) {
                    System.out.println("YES");
                    System.out.println(String.valueOf(str.charAt(i)) + String.valueOf(str.charAt(i+1)));
                    return;
                }
            }
        }
    }
}
