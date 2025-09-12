package com.exercises.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalanNumbersTest {
    
    @Test
    void testCatalanNumbers() {
        assertEquals(5, CatalanNumbers.catalanNumber(3));
        assertEquals(14, CatalanNumbers.catalanNumber(4));
        assertEquals(132, CatalanNumbers.catalanNumber(6));
        assertEquals(42, CatalanNumbers.catalanNumber(5));
        assertEquals(16796, CatalanNumbers.catalanNumber(10));
    }
}