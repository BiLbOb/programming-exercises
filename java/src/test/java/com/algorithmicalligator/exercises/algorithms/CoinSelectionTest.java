package com.algorithmicalligator.exercises.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CoinSelectionTest {
    
    @Test
    void testCoinChange() {
        assertEquals(2, CoinSelection.coinChange(4, List.of(1, 2, 3)));
        assertEquals(1, CoinSelection.coinChange(5, List.of(1, 2, 5)));
        assertEquals(2, CoinSelection.coinChange(10, List.of(2, 5, 3, 6)));
        assertEquals(1, CoinSelection.coinChange(3, List.of(1, 2, 3)));
        assertEquals(-1, CoinSelection.coinChange(7, List.of(2, 6)));
    }
}