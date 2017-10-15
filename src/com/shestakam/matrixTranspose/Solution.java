import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = bi.readLine();
        String[] firstLineStrings = firstLine.split("\\s");
        Integer n =  Integer.parseInt(firstLineStrings[0]);
        Integer seed = Integer.parseInt(firstLineStrings[1]);
        Integer k = Integer.parseInt(bi.readLine());

        int[] vector = generateInput(n, seed);
        for (int i = 0 ; i < k ; i++) {
            String[] params = bi.readLine().split("\\s");
            Integer imin = Integer.parseInt(params[0]);
            Integer jmin = Integer.parseInt(params[1]);
            Integer side = Integer.parseInt(params[2]);

            transpose(vector, imin, jmin, side, n);
        }
        System.out.println(getHash(vector));
    }

    static int[] transpose(int[] matrix, int imin, int jmin, int side, int n) {
        int blockSize = 15;
        for (int i = imin ; i < imin + side ; i += blockSize) {
            for (int j = jmin + (i - imin) ; j < jmin + side ; j += blockSize) {
                for (int ib = 0; ib < blockSize && i + ib < imin + side ; ib++) {
                    // буду транспонировать только если jb выше главной диагонали
                    int jbStart;
                    if (j - jmin > (ib + i) - imin) { // j правее главной диагонали => начинаем с нуля идти
                        jbStart = 0;
                    } else { // идём от ib + 1 , чтобы не делать лишних итераций
                        jbStart = ib + 1;
                    }
                    for (int jb = jbStart ; jb < blockSize && j + jb < jmin + side ; jb++) {
                        int index1 = (i+ib)*n + (j+jb);
                        //надо перевести в систему координат с началом в imin, jmin
                        // транспонировать координаты и перевести в систему кординат с наччалом в 0, 0
                        int index2 = (((j+jb)-jmin)+imin)*n + (((i+ib)-imin) + jmin);

                        int temp = matrix[index1];
                        matrix[index1] = matrix[index2];
                        matrix[index2] = temp;
                    }
                }
            }
        }
        return matrix;
    }

    static int[] generateInput(int n, int seed) {
        int[] vector = new int[n*n];
        for (int i = 0 ; i < n * n  ; i++) {
            vector[i] = seed;
            seed = (int) (((long) seed * 197 + 2017) & 987654);
        }

        return vector;
    }

    static Long getHash(int[] vector) {
        long MOD = 987654321054321L;
        long MUL = 179L;

        long resultValue = 0L;
        for (int i = 0 ; i < vector.length ; i++) {
            resultValue = (resultValue * MUL + vector[i]) & MOD;
        }

        return resultValue;
    }
}