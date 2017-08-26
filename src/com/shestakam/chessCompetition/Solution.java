package com.shestakam.chessCompetition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        List<Integer> rating = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < 2 * n; i++) {
            rating.add(sc.nextInt());
        }

        Collections.sort(rating);
        System.out.println(rating.get(n) > rating.get(n-1) ? "YES" : "NO");
    }
}
