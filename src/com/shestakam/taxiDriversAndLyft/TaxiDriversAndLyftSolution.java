import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/*http://codeforces.com/contest/1075/problem/B*/

public class TaxiDriversAndLyftSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Long> coordinates = new ArrayList<>();
        for (int i = 0; i < n+m ; i++) {
            coordinates.add(scanner.nextLong());
        }
        if (m == 1) {
            System.out.println(n);
            return;
        }

        List<Long> types = new ArrayList<>();
        for (int i = 0; i < n+m ; i++) {
            types.add(scanner.nextLong());
        }

        List<Long> results = new ArrayList<>();
        for (int  i = 0 ; i < m ; i++) {
            results.add(0L);
        }

        int firstTaxiIndex = 0;
        while (types.get(firstTaxiIndex) == 0) {
            firstTaxiIndex++;
        }

        int lastTaxiIndex = n+m-1;
        while (types.get(lastTaxiIndex) == 0) {
            lastTaxiIndex--;
        }

        results.set(0, (long) (firstTaxiIndex));
        results.set(m-1, (long) (n+m - (lastTaxiIndex+1)));

        int currentTaxiIndex = firstTaxiIndex;
        int currentTaxiNumber = 0;
        for (int  i = firstTaxiIndex ; i < n+m ;) {
            int nextTaxiIndex;
            for (nextTaxiIndex = i+1 ; i != lastTaxiIndex && nextTaxiIndex < lastTaxiIndex && types.get(nextTaxiIndex) != 1; nextTaxiIndex++) {
            }
            if (nextTaxiIndex <= lastTaxiIndex) {
                for (int j = i + 1; j < nextTaxiIndex; j++) {
                    if (coordinates.get(j) - coordinates.get(currentTaxiIndex) <= coordinates.get(nextTaxiIndex) - coordinates.get(j)) {
                        results.set(currentTaxiNumber, results.get(currentTaxiNumber) + 1);
                    } else {
                        results.set(currentTaxiNumber + 1, results.get(currentTaxiNumber + 1) + 1);
                    }
                }
                currentTaxiNumber++;
            }
            i = nextTaxiIndex;
            currentTaxiIndex = nextTaxiIndex;
        }

        String result = results.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}