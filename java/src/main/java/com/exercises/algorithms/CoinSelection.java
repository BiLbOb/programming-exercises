import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

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

    private static int findBfsMinimumCoinCount(
        int total,
        NavigableSet<Integer> descendingDenominations
    ) {
        //  Start from highest denomination,
        int highestDenom = descendingDenominations.first();
        int maxCoinsForHighestDenom = total / highestDenom;
        int remainderForMaxCoins =
            total - maxCoinsForHighestDenom * highestDenom;
        // If the biggest denom fits exactly, smaller demominations must require more coins in total
        if (remainderForMaxCoins == 0) {
            return maxCoinsForHighestDenom;
        }
        // Apply the same algorithm to the remainder, with the remaining denominations
        NavigableSet<Integer> remainingDenominations =
            descendingDenominations.tailSet(highestDenom, false);
        if (remainingDenominations.isEmpty()) {
            return -1;
        }

        // Repeat with 1 less coin of this denomination each time, finding the best result
        //  (Descending because biggest-first will commonly work best)
        int nCoinsMin = -1;
        for (int n = maxCoinsForHighestDenom; n >= 0; n--) {
            int remainderForNCoinsOfDenom = total - n * highestDenom;
            int coinsForSmallerDenominatons = findBfsMinimumCoinCount(
                remainderForNCoinsOfDenom,
                remainingDenominations
            );
            if (coinsForSmallerDenominatons < 0) {
                continue;
            }
            int totalCoinsThisTime = n + coinsForSmallerDenominatons;
            if (nCoinsMin < 0 || totalCoinsThisTime < nCoinsMin) {
                nCoinsMin = totalCoinsThisTime;
            }
        }
        return nCoinsMin;
    }

    public static int coinChange(int total, List<Integer> coins) {
        if (total == 0) {
            return 0;
        }
        if (coins.isEmpty()) {
            return -1;
        }
        TreeSet<Integer> denominations = new TreeSet<>(coins);
        return findBfsMinimumCoinCount(total, denominations.descendingSet());
    }
}
