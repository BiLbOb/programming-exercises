package com.exercises.algorithms;

import java.util.*;

public class CatalanNumbers {
    /**
     * From https://app.programiz.pro/community-challenges/challenge/java-catalan-numbers/info
     * See this for description, or
     *  https://en.wikipedia.org/wiki/Catalan_number
     */
    private static LinkedList<Integer> resultTable = new LinkedList<>(
        Arrays.asList(1, 1)
    );

    public static int catalanNumber(int n) {
        /**
         * Return the nth Catalan number.
         * The nth Catalan number, denoted by C(n), can be calculated using the formula
         * C(n) = sum of C(i)*C(n-i-1) for i from 0 to n-1
         */
        System.out.println("Finding " + n + "th Catalan number");
        // C(0) = 1
        // C(1) = 1
        // C(2) = C(0) * C(1) + C(1) * C(0)
        // C(3) = C(0) * C(2) + C(1) * C(1) + C(2) * C(0)
        // C(4) = C(0) * C(3) + C(1) * C(2) + C(2) * C(1) + C(3) * C(0)
        //  ... so we're multiplying the first and last element
        //           then the second and penultimate elements, etc
        //           and adding those products
        while (resultTable.size() <= n) {
            int nextN = resultTable.size();
            System.out.println("Calculating C(" + nextN + ")");

            //  But we can reduce the number of calculations
            //  as they are symetrical (A*B == B*A so the first and last are the same, etc)
            //  Also - multiplying by C(0) and C(1) is unnecessary as they are 1
            //  but for now we'll do those in the hope that the compiler will handle it
            int sum = 0;
            int halfN = nextN / 2;
            for (int i = 0; i < halfN; i++) {
                sum += resultTable.get(i) * resultTable.get(nextN - i - 1);
            }
            sum *= 2;
            // But if odd, add the square of the middle one too
            if (nextN % 2 == 1) {
                int mid = ((nextN + 1) / 2) - 1;
                int value = resultTable.get(mid);
                sum += value * value;
            }
            System.out.println("   got " + sum);
            resultTable.add(sum);
        }
        return resultTable.get(n);
    }
}
