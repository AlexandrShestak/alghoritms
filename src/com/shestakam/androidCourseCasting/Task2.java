import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        PriorityQueue<Integer> prq = new PriorityQueue <> ();

        int maxElementInHeap = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int ai = scanner.nextInt();
            if (prq.size() < 5) { // add first 5 numbers independently of their value
                if (ai > maxElementInHeap) {
                    maxElementInHeap = ai;
                }
                prq.add(ai);
            } else if (ai < maxElementInHeap) {
                PriorityQueue<Integer> newPrq = new PriorityQueue <> (); // priority queue without max value
                int counter = 0;
                Integer element = Integer.MIN_VALUE;
                while (counter < 4) {
                    element = prq.poll();
                    newPrq.add(element);
                    counter++;
                }
                maxElementInHeap = Math.max(element, ai);
                newPrq.add(ai);
                prq = newPrq;
            }

            PriorityQueue<Integer> prqTemp = new PriorityQueue<>(prq); // needed to print answer only
            int counter = 0;
            List<Integer> answer = new ArrayList<>(5);
            while (prqTemp.size() != 0 && counter < 5) {
                answer.add(prqTemp.poll());
                counter++;
            }

            Collections.reverse(answer);
            String result = answer.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            out.println(result);
        }

        out.close();
    }
}






