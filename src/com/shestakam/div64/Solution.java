import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();

        if (number.length() < 7) {
            System.out.println("no");
            return;
        }

        if (number.endsWith("000000") && number.contains("1")) {
            System.out.println("yes");
            return;
        }

        int index = number.length() - 1;
        int zeroCharacterCount = 0;
        while (index >= 0 && zeroCharacterCount < 6) {
            if (number.charAt(index) == '0') {
                zeroCharacterCount++;
            }
            index--;
        }
        if (zeroCharacterCount < 6) {
            System.out.println("no");
            return;
        } else {
            if (number.substring(0, index + 1).contains("1")) {
                System.out.println("yes");
                return;
            } else {
                System.out.println("no");
                return;
            }
        }
    }
}