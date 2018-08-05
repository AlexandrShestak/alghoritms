import java.util.*;

public class Main2 {

    public static String[] testData = {"tea", "tan", "ate", "nat", "bat", "eat" , "tra", "atr"};


    public static List<List<String>> groupAnagrams(String[] items) {
// TODO
        Map<String, List<String>> sortedItemWithOriginal = new HashMap<>();

        for (String item: items) {
            char[] itemCharacters = item.toCharArray();
            Arrays.sort(itemCharacters);
            String sortedItem = new String(itemCharacters);
            if (!sortedItemWithOriginal.containsKey(sortedItem)) {
                sortedItemWithOriginal.put(sortedItem, new ArrayList<>());
            }
            sortedItemWithOriginal.get(sortedItem).add(item);
        }

        ArrayList<List<String>> groupedItems = new ArrayList<>(sortedItemWithOriginal.values());
        groupedItems.sort((o1, o2) -> o2.size() - o1.size());

        List<List<String>> result = new ArrayList<>();
        for (List<String> itemsInGroup : groupedItems) {
            Collections.sort(itemsInGroup);
            result.add(itemsInGroup);
        }

        return result;
    }

    public static void main(String[] args) {
        for (List<String> strings : groupAnagrams(testData)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : strings) {
                stringBuilder.append(string);
                stringBuilder.append(" ");
            }
            System.out.println(stringBuilder.toString());
        }


        String str = new String("2222");

        str.contains("kfl");
    }
}