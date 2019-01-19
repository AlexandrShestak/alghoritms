import java.util.*;

public class DrivingTestSoluion {

    static final Set<Integer> SPEED_EVENTS = new HashSet<>(Arrays.asList(1, 3));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int eventsCount = scanner.nextInt();
        scanner.nextInt(); // always event with number 1
        int currentSpeed = scanner.nextInt();
        int currentSpeedLimit = Integer.MAX_VALUE;
        Stack<Integer> speedLimits = new Stack<>();
        int overtakeNotAllowedCount = 0;

        int signShouldBeNoticedCount = 0;
        for (int i = 0; i < eventsCount - 1; i++) {
            int eventType = scanner.nextInt();
            int speed = 0;
            if (SPEED_EVENTS.contains(eventType)) {
                speed = scanner.nextInt();
            }

            switch (eventType) {
                case 1:  // changes the speed of his car to specified (this event comes with a positive integer number);
                    currentSpeed = speed;
                    if (currentSpeed > currentSpeedLimit) {
                        int speedLimitsFailed = 0;
                        while (!speedLimits.isEmpty() && currentSpeed > speedLimits.peek()) {
                            speedLimits.pop();
                            speedLimitsFailed++;
                        }
                        signShouldBeNoticedCount += speedLimitsFailed;
                    }
                    break;
                case 2:  //car overtakes the other car
                    if (overtakeNotAllowedCount != 0) {
                        signShouldBeNoticedCount += overtakeNotAllowedCount;
                        overtakeNotAllowedCount = 0;
                    }
                    break;
                case 3:  //car goes past the "speed limit" sign (this sign comes with a positive integer);
                    if (currentSpeed > speed) {
                        signShouldBeNoticedCount++;
                    } else {
                        currentSpeedLimit = speed;
                        speedLimits.add(speed);
                    }
                    break;
                case 4:  //car goes past the "overtake is allowed" sign;
                    overtakeNotAllowedCount = 0;
                    break;
                case 5:  //car goes past the "no speed limit";
                    currentSpeedLimit = Integer.MAX_VALUE;
                    speedLimits.clear();
                    break;
                case 6:  //car goes past the "no overtake allowed";
                    overtakeNotAllowedCount++;
                    break;
            }
        }
        System.out.println(signShouldBeNoticedCount);
    }
}
