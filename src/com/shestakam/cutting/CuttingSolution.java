import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CuttingSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }
        int B = scanner.nextInt();

        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sequence.add(scanner.nextInt());
        }


        int index = 0;
        List<Integer> possibleCuttings = new ArrayList<>();
        int oddNumbersCount = 0; // нечётные
        while (index < sequence.size() - 2) {

            if (sequence.get(index) % 2 != 0) {
                oddNumbersCount++;
            }
            if (sequence.get(index+1) % 2 != 0) {
                oddNumbersCount++;
            }
            index+=2;

            if (oddNumbersCount*2 == index) {
                possibleCuttings.add(Math.abs(sequence.get(index)-sequence.get(index-1)));
            }
        }

        possibleCuttings = possibleCuttings.stream().filter(cutting -> cutting <= B).collect(Collectors.toList());


        if (possibleCuttings.size() == 0 || Collections.min(possibleCuttings) > B) {
            System.out.println(0);
            return;
        }


        Collections.sort(possibleCuttings);
        int maxCutCount = 0;
        for (int i = 1 ; i <= possibleCuttings.size() ; i++) {
            int sum = 0;
            for (int j = 0 ; j < i ; j++) {
                sum += possibleCuttings.get(j);
            }
            if (sum <= B) {
                maxCutCount = i;
            } else  {
                break;
            }

        }
        System.out.println(maxCutCount);
    }
}
