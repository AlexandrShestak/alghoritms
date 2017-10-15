import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cacheSize = scanner.nextInt();
        int associativity = scanner.nextInt();
        int lineSize = scanner.nextInt();
        int n = scanner.nextInt();

        List<Integer> addresses = new ArrayList<>(n);
        for (int i = 0 ; i < n ; i++) {
            addresses.add(scanner.nextInt());
        }

        Cache cache = new Cache(cacheSize, associativity, lineSize);
        for (Integer address : addresses) {
            cache.find(address);
        }

        System.out.println(cache.getCacheHitCount()+ " " + cache.getCacheMissCount());
    }
}

class Cache {
    private List<CacheLine> cacheLines;
    private int cacheSize;
    private int associativity;
    private int lineSize;
    private int lineCount;
    private int cacheHitCount;
    private int cacheMissCount;
    private int countOfLineInBlock;

    public Cache(int cacheSize, int associativity, int lineSize) {
        this.cacheSize = cacheSize;
        this.associativity = associativity;
        this.lineSize = lineSize;
        this.cacheHitCount = 0;
        this.cacheMissCount = 0;
        this.lineCount = cacheSize / lineSize;
        this.countOfLineInBlock = this.lineCount/associativity;

        this.cacheLines = new ArrayList<>(this.lineCount);
        for (int i = 0 ; i < lineCount ; i++) {
            CacheLine cacheLine = new CacheLine();
            this.cacheLines.add(cacheLine);
        }
    }

    public void find(int address) {
        int lineNumberInRam = address / lineSize;

        int numberInBlock = lineNumberInRam % countOfLineInBlock;

        int attemptsCount = 0;
        for (int i = 0 ; i < associativity ; i++) {
            CacheLine cacheLine = cacheLines.get(numberInBlock + (i * countOfLineInBlock));
            if (cacheLine.isEmpty()) {
                cacheLine.setEmpty(false);
                cacheLine.setAge(0);
                cacheLine.setAddressTag(lineNumberInRam);
                cacheMissCount++;
                break;
            } else {
                if (cacheLine.getAddressTag() == lineNumberInRam) {
                    cacheHitCount++;
                    cacheLine.setAge(0);
                    break;
                }
                attemptsCount++;
            }
        }
        // hit miss
        if (attemptsCount == associativity) {
            cacheMissCount++;
            int oldestLineAge = Integer.MIN_VALUE;
            CacheLine oldestCacheLine = null;

            for (int i = 0 ; i < associativity ; i++) {
                CacheLine cacheLine = cacheLines.get(numberInBlock + (i * countOfLineInBlock));
                if (cacheLine.getAge() > oldestLineAge) {
                    oldestLineAge = cacheLine.getAge();
                    oldestCacheLine = cacheLine;
                }
            }
            oldestCacheLine.setAge(0);
            oldestCacheLine.setAddressTag(lineNumberInRam);
        }

        for (int i = 0 ; i < associativity ; i++) {
            CacheLine cacheLine = cacheLines.get(numberInBlock + (i * countOfLineInBlock));
            if (!cacheLine.isEmpty()) {
                cacheLine.setAge(cacheLine.getAge() + 1);
            }
        }
    }

    public int getCacheHitCount() {
        return cacheHitCount;
    }

    public int getCacheMissCount() {
        return cacheMissCount;
    }
}

class CacheLine {
    private int age;
    private int addressTag;
    private boolean isEmpty;

    public CacheLine() {
        this.age = 0;
        this.isEmpty = true;
    }

    public int getAge() {
        return age;
    }

    public int getAddressTag() {
        return addressTag;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddressTag(int addressTag) {
        this.addressTag = addressTag;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}


