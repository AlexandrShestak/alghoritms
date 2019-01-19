import java.util.*;

public class RatingsAndRealityShowsSolution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int start = sc.nextInt();
        int len = sc.nextInt();

        List<TimeWithEvent> events = new ArrayList<>();
        events.add(new TimeWithEvent(-1, -1));
        for (int i = 0; i < n; i++) {
            events.add(new TimeWithEvent(sc.nextInt(), sc.nextInt()));
        }

        List<Integer> initialPrefixArray = new ArrayList<>();
        List<Integer> prefixArrayAfterRealityShow = new ArrayList<>();
        int afterReality = start;
        initialPrefixArray.add(start);
        prefixArrayAfterRealityShow.add(start);
        Iterator<TimeWithEvent> iterator = events.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            TimeWithEvent event = iterator.next();
            if (event.type == 0) {
                start -= b;
                afterReality -= d;
            } else {
                start += a;
                afterReality += c;
            }
            initialPrefixArray.add(start);
            prefixArrayAfterRealityShow.add(afterReality);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int eventIndex = 1;

        for (int i = 0; i <= n; i++) {
                if (initialPrefixArray.get(i) < 0) {
                System.out.println("-1");
                return;
            }

            if (i != 0) {
                minHeap.remove(prefixArrayAfterRealityShow.get(i));
            }

            while (eventIndex <= n && events.get(eventIndex).time - (events.get(i).time +1) < len) {
                minHeap.add(prefixArrayAfterRealityShow.get(eventIndex++));
            }

            if (minHeap.isEmpty()) {
                System.out.println(events.get(i).time + 1);
                return;
            }

            // value on which rating will be changed if go to tok-show on this step
            int difForInterval = minHeap.peek() - prefixArrayAfterRealityShow.get(i);

            if (initialPrefixArray.get(i) + difForInterval >= 0) {
                System.out.println(events.get(i).time + 1);
                return;
            }

        }
        System.out.println(-1);
    }

    public static class TimeWithEvent {
        int time;
        int type;

        TimeWithEvent(int time, int event) {
            this.time = time;
            this.type = event;
        }
    }
}
