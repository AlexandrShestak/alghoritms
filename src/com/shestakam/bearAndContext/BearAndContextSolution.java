import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BearAndContextSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<Integer> tasks = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int nextTaskRate = scanner.nextInt();
            if (tasks.isEmpty() && nextTaskRate <= k) {
                result++;
            } else {
                tasks.add(nextTaskRate);
            }
        }
        for (int i = tasks.size() - 1 ; i > 0 ; i--) {
            if (tasks.get(i) <= k) {
                result++;
            } else {
               break;
            }
        }
        System.out.println(result);
    }
}
