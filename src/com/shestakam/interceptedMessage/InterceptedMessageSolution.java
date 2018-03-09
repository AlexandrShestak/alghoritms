import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class InterceptedMessageSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer> fistBlockLengths = new ArrayList<>(n);
        List<Integer> secondBlockLengths = new ArrayList<>(m);

        for (int i = 0; i < n; i++) {
            fistBlockLengths.add(scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            secondBlockLengths.add(scanner.nextInt());
        }

        Iterator<Integer> firstMessageIterator = fistBlockLengths.iterator();
        Iterator<Integer> secondMessageIterator = secondBlockLengths.iterator();

        Integer firstMessageTempSum = firstMessageIterator.next();
        Integer secondMessageTempSum = secondMessageIterator.next();

        int maxMessagesCount = 1;
        while (firstMessageIterator.hasNext() && secondMessageIterator.hasNext()) {
            if (firstMessageTempSum.equals(secondMessageTempSum)) {
                maxMessagesCount++;
                firstMessageTempSum = firstMessageIterator.next();
                secondMessageTempSum = secondMessageIterator.next();
            } else if (firstMessageTempSum < secondMessageTempSum) {
                firstMessageTempSum += firstMessageIterator.next();
            } else {
                secondMessageTempSum += secondMessageIterator.next();
            }
        }

        System.out.println(maxMessagesCount);
    }
}
