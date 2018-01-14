package com.shestakam.cubeForMasha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<List<Integer>> cubeNumbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                numbers.add(sc.nextInt());
            }
            cubeNumbers.add(numbers);
        }

        int result = 1;
        while (true) {
            List<Integer> numbers = new ArrayList<>();
            int tempResult = result;
            while (tempResult > 0) {
                numbers.add(tempResult % 10);
                tempResult = (tempResult - tempResult % 10) / 10;
            }
            if (!check(numbers, cubeNumbers, new ArrayList<>(), n , 0, numbers.size())) {
                break;
            }
            result++;
        }
        System.out.println(result-1);
    }

    public static boolean check(List<Integer> numbers, List<List<Integer>> cubeNumbers, List<Integer> ignoreCubeIndexes, int totalCubeNumber,
                                int numbersFounded, int numberToCheckLength) {
        for (Integer number : numbers) {
            for (int i = 0 ; i < totalCubeNumber ; i++) {
                if (!ignoreCubeIndexes.contains(i)) {
                    if (cubeNumbers.get(i).contains(number)) {
                        numbersFounded++;
                        if (numberToCheckLength == numbersFounded) {
                            return true;
                        }
                        List<Integer> ignoreCubesNew = new ArrayList<>(ignoreCubeIndexes);
                        ignoreCubesNew.add(i);
                        if (check(numbers.subList(1, numbers.size()), cubeNumbers, ignoreCubesNew,
                                totalCubeNumber, numbersFounded, numberToCheckLength)) {
                            return true;
                        } else {
                            numbersFounded--;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }
}