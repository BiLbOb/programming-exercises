package com.algorithmicalligator.exercises.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Given a set of coin denominations `coins` and a total amount of money `total`, 
 * compute the fewest number of coins needed to make up that amount.
 * Return the minimum number of coins needed to make up the given amount. 
 * If it's not possible, return -1.
 * 
 * https://app.programiz.pro/community-challenges/challenge/java-coin-change-problem/info
 */

public class CoinSelection {

    // A BFS tree search solution would be to consider using
    //  each denomination (from the biggest: Dmax) maximum number of times, or less
    //  then considering the next biggest denom versus the remainder
    //      Total Amount Required
    //      /      |         \
    //  n*Dmax  n-1*Dmax ...  0*Dmax
    //      +       +          +
    // remainder   r2         r3
    //      |       |       /   |   \
    //     ...     ...  m*Dmax' ... 0*m*Dmax'
    //
    // If biggest denom fits exactly, that must be the best fit
    //  as using smaller coins must require more coins
    //  so can discard the rest of this sub-tree
    // Implemented recursively, for simplicity
    //  but for large number of denominations, this could cause problems,
    //  so unwinding the recusion with a queue would be better in that case

    private record PartialSolution(Integer coinsSoFar, Integer totalRequired,  NavigableSet<Integer> denominations) {}

    public static int coinChange(int total, List<Integer> coins) {
        if (total == 0) {
            return 0;
        }
        LinkedList<PartialSolution> queue = new LinkedList<>();
        queue.add(new PartialSolution(0, total, new TreeSet<>(coins).descendingSet()));
        int nCoinsMin = -1;
        while (!queue.isEmpty()) {
            //  Start from highest denomination,
            PartialSolution partial = queue.removeFirst();
            if (partial.denominations.isEmpty()) {
                continue;
            }
            int highestDenom = partial.denominations.first();
            int totalRequired = partial.totalRequired();
            int maxCoinsForHighestDenom = totalRequired / highestDenom;
            int remainderForMaxCoins =
                totalRequired - maxCoinsForHighestDenom * highestDenom;
            int coinsSoFar = partial.coinsSoFar();
            // If the biggest denom fits exactly, smaller demominations must require more coins in total
            if (remainderForMaxCoins == 0) {
                return coinsSoFar + maxCoinsForHighestDenom; 
            }
            // Add all the options for using this denomination Nmax...0 times
            NavigableSet<Integer> remainingDenominations = partial.denominations.tailSet(highestDenom, false);
            for(int i = maxCoinsForHighestDenom; i >= 0; i--) {
                queue.add(new PartialSolution(coinsSoFar + i, total - i * highestDenom, remainingDenominations));
            }
        };
        return nCoinsMin;
    }
}
