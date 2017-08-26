import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();
        Integer firstPart = Integer.parseInt(number.substring(0, 3));
        Integer secondPart = Integer.parseInt(number.substring(3, 6));

        List<Integer> firstPartDigits = new ArrayList<>();
        List<Integer> secondPartDigits = new ArrayList<>();
        int firstPartDigitsSum = 0;
        int secondPartDigitsSum = 0;
        for (int i = 0; i < 3; i++) {
            firstPartDigits.add(firstPart % 10);
            firstPartDigitsSum += firstPart % 10;
            firstPart /= 10;

            secondPartDigits.add(secondPart % 10);
            secondPartDigitsSum += secondPart % 10;
            secondPart /= 10;
        }


        int digitReplacementNeeded = 0;
        while (firstPartDigitsSum != secondPartDigitsSum) {
            if (firstPartDigitsSum < secondPartDigitsSum) {
                Integer min = Collections.min(firstPartDigits);
                Integer max = Collections.max(secondPartDigits);
                if (9 - min < max) {
                    secondPartDigitsSum -= secondPartDigitsSum - firstPartDigitsSum < max? secondPartDigitsSum - firstPartDigitsSum : max;
                    secondPartDigits.remove(max);
                } else {
                    firstPartDigitsSum += secondPartDigitsSum - firstPartDigitsSum < 9 - min ? secondPartDigitsSum - firstPartDigitsSum : 9 - min;
                    firstPartDigits.remove(min);
                }
            } else {
                Integer min = Collections.min(secondPartDigits);
                Integer max = Collections.max(firstPartDigits);

                if (9 - min < max) {
                    firstPartDigitsSum -= firstPartDigitsSum - secondPartDigitsSum < max ? firstPartDigitsSum - secondPartDigitsSum : max;
                    firstPartDigits.remove(max);
                } else {
                    secondPartDigitsSum += firstPartDigitsSum - secondPartDigitsSum < 9 - min ? firstPartDigitsSum - secondPartDigitsSum : 9 - min;
                    secondPartDigits.remove(min);
                }
            }
            digitReplacementNeeded++;
        }

        System.out.println(digitReplacementNeeded);
    }
}