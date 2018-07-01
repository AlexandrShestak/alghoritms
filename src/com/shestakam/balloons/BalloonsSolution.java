import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BalloonsSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(-1);
            return;
        }

        List<Integer> balloonsCounts = new ArrayList<>();
        int aiSum = 0;
        for (int i = 0; i < n; i++) {
            int ai = scanner.nextInt();
            balloonsCounts.add(ai);
            aiSum += ai;
        }

        for (int i = 1 ; i < Math.pow(2, n) -1 ; i++) {
            int index = 0;
            int currentSum = 0;
            List<Integer> indexes = new ArrayList<>();
            for (char c : new StringBuilder(Integer.toBinaryString(i)).reverse().toString().toCharArray()) {
                if (c == '1') {
                    indexes.add(n-index);
                    currentSum += balloonsCounts.get(n-1-index);
                }
                index++;
            }
            if (2*currentSum != aiSum) {
                System.out.println(indexes.size());
                System.out.println(indexes.stream().map(Object::toString).collect(Collectors.joining(" ")));

                return;
            }
        }
        System.out.println(-1);
        return;
    }
}
