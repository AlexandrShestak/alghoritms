import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RomainDigitsSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long count = 0;

//        Brute force algorithm
//        Set<Long> numbers = new HashSet<>();
//        for (int i = 0; i <= n; i++) { // #4
//            for (int j = 0; j <= n; j++) { // #9
//                for (int k = 0; k <= n; k++) {
//
//                    if (i >= 9) {
//                        continue;
//                    }
//
//                    if (i+j+k > n) {
//                        continue;
//                    }
//                    int l = n - i - j - k;
//                    Long number = Long.valueOf(i * 0 + i * 4 + j * 9 + k * 49);
//                    if (!numbers.contains(number)) {
//                        numbers.add(number);
//                        count++;
//                    }
//                }
//            }
//        }

        boolean[][] badArray = new boolean[50][50];
        for (int i = 0 ; i < 50 ; i++) {
            for (int j = 0 ; j < 50 ; j++) {
                for (int k = 0 ; k < 50 ; k++)
                    for (int l = 0; l < 50; l++) {
                        if ((i*4 + j*9 < k*4 + l*9) && ((i * 4 + j * 9) - (k*4 + l*9)) % 49 == 0) {
                            badArray[k][l] = true;
                        }
                    }
            }
        }
        for (int num4 = 0 ; num4 < 9 && num4 <= n ; num4++) {
            for (int num9 = 0 ; num4 + num9 <= n && num9 < 49; num9++) {
                if (!badArray[num4][num9]) {
                    count += n - num4 - num9 + 1;
                }
            }
        }
       System.out.println(count);
    }
}
