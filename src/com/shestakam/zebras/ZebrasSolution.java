import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ZebrasSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        if (str.startsWith("1") || str.endsWith("1")) {
            System.out.println(-1);
            return;
        }

        char[] chars = str.toCharArray();

        TreeSet<Integer> zerosIndexesQueue = new TreeSet<>();
        TreeSet<Integer> onesIndexesQueue = new TreeSet<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                zerosIndexesQueue.add(i);
            } else {
                onesIndexesQueue.add(i);
            }
        }

        Integer currentIndex = zerosIndexesQueue.pollFirst();
        boolean takeZeroOnCurrentStep = false;
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> answerOnCurrentStep = new ArrayList<>();
        answerOnCurrentStep.add(currentIndex+1);
        while (!zerosIndexesQueue.isEmpty()) {
            if (takeZeroOnCurrentStep) {
                if (zerosIndexesQueue.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                currentIndex = zerosIndexesQueue.ceiling(currentIndex);
                if (currentIndex == null) {
                    System.out.println(-1);
                    return;
                }
                zerosIndexesQueue.remove(currentIndex);
            } else {
                currentIndex = onesIndexesQueue.ceiling(currentIndex);
                if (currentIndex == null) {
                    answer.add(new ArrayList<>(answerOnCurrentStep));
                    answerOnCurrentStep = new ArrayList<>();
                    currentIndex = zerosIndexesQueue.pollFirst();
                    takeZeroOnCurrentStep = true;
                } else {
                    onesIndexesQueue.remove(currentIndex);
                }
            }
            answerOnCurrentStep.add(currentIndex+1);
            takeZeroOnCurrentStep = !takeZeroOnCurrentStep;
        }

        if (takeZeroOnCurrentStep) {
            System.out.println(-1);
            return;
        } else {
            answer.add(answerOnCurrentStep);
        }

        if (!onesIndexesQueue.isEmpty()) {
            System.out.println(-1);
            return;
        }

        PrintWriter out = new PrintWriter(System.out);
        out.println(answer.size());
        for (List<Integer> integers : answer) {
            out.println(integers.size() + " " + integers.stream().map(Object::toString).collect(Collectors.joining(" ")));
        }
        out.close();
    }
}
