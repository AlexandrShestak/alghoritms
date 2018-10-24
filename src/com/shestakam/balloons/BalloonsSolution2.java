import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BalloonsSolution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(-1);
            return;
        } else if (n == 2) {
            if (scanner.nextInt() == scanner.nextInt()) {
                System.out.println(-1);
                return;
            } else {
                System.out.println(1);
                System.out.println(1);
                return;
            }
        }

        List<Integer> balloonsCounts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            balloonsCounts.add(scanner.nextInt());
        }

        int min = Integer.MAX_VALUE;
        for (Integer balloonsCount : balloonsCounts) {
            if (balloonsCount < min) {
                min = balloonsCount;
            }
        }

        System.out.println(1);
        System.out.println(balloonsCounts.indexOf(min)+1);
    }
}
