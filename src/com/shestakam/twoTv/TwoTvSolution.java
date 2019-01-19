import java.util.*;

public class TwoTvSolution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            ranges.add(new Range(from, to));
        }
        ranges.sort(Comparator.comparing(o -> o.from));

        int firstTvStart = -1;
        int secondTvStart = -1;
        for (Range range : ranges) {
            if (firstTvStart < range.from) {
                firstTvStart = range.to;
            } else if (secondTvStart < range.from) {
                secondTvStart = range.to;
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static class Range {
        Integer from;
        Integer to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}