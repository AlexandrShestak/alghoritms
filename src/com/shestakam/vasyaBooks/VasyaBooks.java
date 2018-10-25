import java.io.PrintWriter;
import java.util.*;

/**
 * http://codeforces.com/contest/1073/problem/B
 */
public class VasyaBooks {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] aiPositions = new int[n];

        scanner.nextLine();
        String[] aiSplit = scanner.nextLine().split("\\s");
        for (int i = 0; i < n; i++) {
            aiPositions[Integer.valueOf(aiSplit[i])-1] = i;

        }

        String[] biSplit = scanner.nextLine().split("\\s");
        int maxTakenIndex = -1;
        for (int i = 0; i < n; i++) {
            int bi = Integer.valueOf(biSplit[i]);
            int index = aiPositions[bi - 1];
            if (index > maxTakenIndex) {
                out.println(index - maxTakenIndex);
                maxTakenIndex = index;
            } else {
                out.println(0);
            }
        }
        out.close();
    }
}