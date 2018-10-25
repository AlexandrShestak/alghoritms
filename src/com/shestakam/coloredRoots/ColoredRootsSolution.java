import java.util.*;

/**
 * http://codeforces.com/contest/1068/problem/C
 */
public class ColoredRootsSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Map<Integer, List<Coordinates> > result = new HashMap<>();
        int maxUnusedPosition = 1;
        for (int i = 0; i < m; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            result.computeIfAbsent(first, k -> new ArrayList<>());
            result.computeIfAbsent(second, k -> new ArrayList<>());

            result.get(first).add(new Coordinates(maxUnusedPosition, first));
            result.get(second).add(new Coordinates(maxUnusedPosition, second));
            maxUnusedPosition++;
        }

        maxUnusedPosition = Math.max(n+1, maxUnusedPosition);
        for (int i = 0; i < n; i++) {
            List<Coordinates> coordinates = result.get(i + 1);
            if (coordinates == null) {
                System.out.println(1);
                System.out.println("" + maxUnusedPosition + " " + maxUnusedPosition);
                maxUnusedPosition++;
            } else {
                System.out.println(coordinates.size());
                for (Coordinates coordinate : coordinates) {
                    System.out.println(coordinate.x + " " + coordinate.y);
                }
            }

        }
    }

    static class Coordinates {
        int x, y;

        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}