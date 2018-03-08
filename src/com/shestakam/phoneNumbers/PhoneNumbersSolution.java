import java.util.*;
import java.util.stream.Collectors;

public class PhoneNumbersSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();

        SortedSet<Character> characters = new TreeSet<>(str.chars().mapToObj(e->(char)e).collect(Collectors.toSet()));

        if (k > n) {
            StringBuilder builder = new StringBuilder(str);
            for (int i = 0 ; i < k - n ; i++) {
                builder.append(characters.first());
            }
            System.out.println(builder.toString());
        } else {
            char[] prefixChars = str.substring(0, k).toCharArray();
            int index = prefixChars.length - 1;
            while (prefixChars[index] == characters.last()) {
                prefixChars[index] = characters.first();
                index--;
            }

            Iterator<Character> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == prefixChars[index]) {
                    prefixChars[index] = iterator.next();
                }
            }
            System.out.println(new String(prefixChars));
        }
    }
}
