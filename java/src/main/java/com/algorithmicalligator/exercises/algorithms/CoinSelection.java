package com.algorithmicalligator.exercises.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Optional;
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
    private record PartialSolution(Integer coinsSoFar, Integer totalRequired,  NavigableSet<Integer> denominations) {}

    public static int coinChange(int total, List<Integer> coins) {
        if (total == 0) {
            return 0;
        }
        LinkedList<PartialSolution> queue = new LinkedList<>();
        queue.add(new PartialSolution(0, total, new TreeSet<>(coins).descendingSet()));
        Optional<Integer> bestSoFar = Optional.empty();  
        while (!queue.isEmpty()) {
            //  Start from highest denomination,
            PartialSolution partial = queue.removeFirst();
            Optional<PartialSolution> bestGuess = Optional.of(new PartialSolution(partial.coinsSoFar, partial.totalRequired, partial.denominations()));
            for (Integer denom : partial.denominations) {
                int totalRequired = bestGuess.get().totalRequired();
                int maxCoinsForThisDenom = totalRequired / denom;
                int remainderForMaxCoins = totalRequired - maxCoinsForThisDenom * denom;
                int nextCount = bestGuess.get().coinsSoFar() + maxCoinsForThisDenom;
                // No point continuing this path if it's already worse than the best so far
                if (bestSoFar.isPresent() && nextCount >= bestSoFar.get()) {
                    bestGuess = Optional.empty();
                    break;
                }
                bestGuess = Optional.of(new PartialSolution(nextCount, remainderForMaxCoins, partial.denominations()));
                if (remainderForMaxCoins == 0) {
                    break;
                }
            }
            if( !bestGuess.isPresent() ) {
                // This path is already worse than the best so far
                continue;
            }
            PartialSolution guess = bestGuess.get();
            if (guess.totalRequired() == 0 && (bestSoFar.isEmpty() || guess.coinsSoFar() < bestSoFar.get())) {
                bestSoFar = Optional.of(guess.coinsSoFar());
                // If the maximum number of the biggest denominations gives a solution, 
                // this is the best possible solution of this partial - I think!!
                continue;
            }
            
            // Otherwise add new partial solutions for less of the highest denomination
            if (partial.denominations.size() > 1) {
                NavigableSet<Integer> remainingDenominations = partial
                    .denominations
                    .tailSet(partial.denominations.first(), false)
                    .stream()
                    .filter(d -> d < partial.totalRequired())
                    .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
                
                if (remainingDenominations.size() > 0) {
                    NavigableSet<Integer> remaining = remainingDenominations.descendingSet();
                    Integer denom = partial.denominations.first();
                    int maxCoinsForDenom = partial.totalRequired() / denom;
                    for (int i = 0; i < maxCoinsForDenom; i++) {
                        int remainderForICoins = partial.totalRequired() - i * denom;
                        int coinsSoFar = partial.coinsSoFar + i;
                        queue.push(new PartialSolution(coinsSoFar, remainderForICoins, remaining));
                    }
                }
            }
        }
        return bestSoFar.isPresent() ? bestSoFar.get() : -1;
    }
}
