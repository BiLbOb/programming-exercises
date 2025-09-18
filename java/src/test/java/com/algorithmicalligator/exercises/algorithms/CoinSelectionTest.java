package com.algorithmicalligator.exercises.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CoinSelectionTest {
    
    @Test
    void shouldUse2Coins_whenMaking4FromOnesTwosAndThrees() {
        assertEquals(2, CoinSelection.coinChange(4, List.of(1, 2, 3)));
    }
    
    @Test
    void shouldUse1Coin_whenTotalMatchesHighestDenomination() {
        assertEquals(1, CoinSelection.coinChange(5, List.of(1, 2, 5)));
   }
    
    @Test
    void shouldUseTwoFives_whenMaking10FromTwosThreesFivesAndSixes() {
        assertEquals(2, CoinSelection.coinChange(10, List.of(2, 5, 3, 6)));
    }
    
    @Test
    void shouldUse1Coin_whenTotalMatchesADenomination() {
        assertEquals(1, CoinSelection.coinChange(3, List.of(1, 2, 3, 5)));
    }
    
    @Test
    void shouldReturnMinusOne_whenTotalCanNotBeMadeFromDenominations() {
        assertEquals(-1, CoinSelection.coinChange(7, List.of(2, 6)));
    }
}