package com.algorithmicalligator.exercises.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalanNumbersTest {
    
    @Test
    void shouldReturn5_whenAskedForThe3rdCatalanNumber() {
        assertEquals(5, CatalanNumbers.catalanNumber(3));
    }
    
    @Test
    void shouldReturn14_whenAskedForThe4thCatalanNumber() {
        assertEquals(14, CatalanNumbers.catalanNumber(4));
    }
    
    @Test
    void shouldReturn132_whenAskedForThe6thCatalanNumber() {
        assertEquals(132, CatalanNumbers.catalanNumber(6));
    }
    
    @Test
    void shouldReturn42_whenAskedForThe5thCatalanNumber() {
        // This should be cached, but checing that will require some mocking/spying
        assertEquals(42, CatalanNumbers.catalanNumber(5));
    }
    
    @Test
    void shouldReturn16796_whenAskedForThe10thCatalanNumber() {
        assertEquals(16796, CatalanNumbers.catalanNumber(10));
    }
}