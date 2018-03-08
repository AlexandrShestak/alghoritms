import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsOnLineSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(sc.nextInt());
        }

        Collections.sort(numbers);

        int longestSequence = Integer.MIN_VALUE;
        int longestSequenceTemp;

        if (n > 1) {
            for (int i = 0; i < n; i++) {
                longestSequenceTemp = 0;
                for (int j = i+1; j < n ; j++) {
                    if (numbers.get(j) - numbers.get(i) <= d) {
                        longestSequenceTemp++;
                    } else {
                        break;
                    }
                }
                if (longestSequenceTemp > longestSequence) {
                    longestSequence = longestSequenceTemp;
                }
            }
        } else {
            System.out.println(0);
            return;
        }

        System.out.println(n-(longestSequence+1));

    }
}
