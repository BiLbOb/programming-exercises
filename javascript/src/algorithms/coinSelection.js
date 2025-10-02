/**
 * Given a set of coin denominations `coins` and a total amount of money `total`, 
 * compute the fewest number of coins needed to make up that amount.
 * Return the minimum number of coins needed to make up the given amount. 
 * If it's not possible, return -1.
 * 
 * https://app.programiz.pro/community-challenges/challenge/javascript-coin-change-problem/info#
 */
function coinChange(coins, amount) {
    // Shortcut the easy answer
    if (amount == 0) {
        return 0;
    }
    // The best solution is likely to use more of the higher denominations, so sort descending
    const denominations = coins.sort((a, b) => b - a);
    const partialSolutions = [{
        coinsSoFar: 0,
        totalRequired: amount,
        denominations,
    }];
    // First we try to use the maximum number of each denomination, then we try less of the highest denomination
    var bestSoFar = Number.MAX_SAFE_INTEGER;
    while (partialSolutions.length > 0) {
        const partial = partialSolutions.shift();
        
        const bestGuess = partial
            .denominations
            .reduce( 
                (acc, denom) => {
                    if(acc) {
                        const maxCoinsForDenom = Math.floor(acc.totalRequired / denom);
                        const remainderForMaxCoins = acc.totalRequired - maxCoinsForDenom * denom;
                        const nextCount = acc.coinsSoFar + maxCoinsForDenom;
                        // No point continuing this path if it's already worse than the best so far
                        return  (nextCount > bestSoFar) ? null :{  coinsSoFar: nextCount, totalRequired: remainderForMaxCoins  };        
                    } else { return acc; }
                }, 
                {
                    coinsSoFar: partial.coinsSoFar,
                    totalRequired: partial.totalRequired,
                }
            );

        if (!bestGuess) {
            // This path is already worse than the best so far
            continue;
        }

        if (bestGuess.totalRequired == 0 && (bestGuess.coinsSoFar < bestSoFar)) {
            bestSoFar = bestGuess.coinsSoFar;
            // If the maximum number of the biggest denominations gives a solution, 
            // this is the best possible solution of this partial - I think!!
            continue;
        }
        
        // Otherwise add new partial solutions for less of the highest denomination
        if( partial.denominations.length > 1 ) {
            const remainingDenominations = partial.denominations.slice(1).filter( d => d <= partial.totalRequired );
            if( remainingDenominations.length > 0) {
                const denom = partial.denominations[0];
                const maxCoinsForDenom = Math.floor(partial.totalRequired / denom);
                for (let i = 0; i < maxCoinsForDenom; i++) {
                    const remainderForICoins = partial.totalRequired - i * denom;
                    const coinsSoFar = partial.coinsSoFar + i; 
                    partialSolutions.unshift({
                        coinsSoFar: coinsSoFar,
                        totalRequired: remainderForICoins,
                        denominations: remainingDenominations,
                    });
                }
            }
        }
    }
    return bestSoFar == Number.MAX_SAFE_INTEGER? -1 : bestSoFar;
}

module.exports = { coinChange };