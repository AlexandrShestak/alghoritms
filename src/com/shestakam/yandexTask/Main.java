import java.util.*;

class CharactersWithOriginalItem{
    Map<Character, Integer> characterCounts;
    String originalItem;
}

public class Main {

    public static String[] testData = {"eat", "tea", "tan", "ate", "nat", "bat"};


    public static List<List<String>> groupAnagrams(String[] items) {
// TODO
        List<CharactersWithOriginalItem> itemLettersCount = new ArrayList<>();

        for (String item: items) {
            Map<Character, Integer> itemMap = new HashMap<>();

            CharactersWithOriginalItem charactersWithOriginalItem = new CharactersWithOriginalItem();
            charactersWithOriginalItem.originalItem = item;
            charactersWithOriginalItem.characterCounts = itemMap;

            for (Character itemChar: item.toCharArray()) {
                if (!itemMap.containsKey(itemChar)) {
                    itemMap.put(itemChar, 1);
                } else {
                    itemMap.put(itemChar, itemMap.get(itemChar) + 1);
                }
            }
            itemLettersCount.add(charactersWithOriginalItem);
        }

        List<List<CharactersWithOriginalItem>> groupedByOriginalItem = new ArrayList<>();
        List<Integer> ignoreIndexes = new ArrayList<>();

        int indexToCheck = 0;
        while (ignoreIndexes.size() != items.length) {

            Map<Character, Integer> checkOnCurrentIteration = itemLettersCount.get(indexToCheck).characterCounts;
            List<CharactersWithOriginalItem> gropedItemsOnCurrentStep = new ArrayList<>();

            int counter = 0;
            for (int i = indexToCheck ; i < items.length ; i++) {
                if (!ignoreIndexes.contains(i)){
                    CharactersWithOriginalItem charactersWithOriginalItem = itemLettersCount.get(i);
                    if (charactersWithOriginalItem.characterCounts.equals(checkOnCurrentIteration)) {
                        gropedItemsOnCurrentStep.add(charactersWithOriginalItem);
                        ignoreIndexes.add(counter);
                    }
                }
                counter++;
            }
            groupedByOriginalItem.add(gropedItemsOnCurrentStep);

            //find new item to check
            boolean newIndexFounded = false;
            while (indexToCheck < items.length && !newIndexFounded) {
                if (ignoreIndexes.contains(indexToCheck)) {
                    indexToCheck++;
                } else {
                    newIndexFounded = true;
                }
            }
        }

        Collections.sort(groupedByOriginalItem, (Comparator<List>) (o1, o2) -> o2.size() - o1.size());


        List<List<String>> result = new ArrayList<>();
        for (List<CharactersWithOriginalItem> resultList: groupedByOriginalItem) {
            List<String> resultItem = new ArrayList<>();
            Collections.sort(resultList, Comparator.comparing(o -> o.originalItem));
            for (CharactersWithOriginalItem charactersWithOriginalItem: resultList) {
                resultItem.add(charactersWithOriginalItem.originalItem);
            }
            result.add(resultItem);
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
    }
}
