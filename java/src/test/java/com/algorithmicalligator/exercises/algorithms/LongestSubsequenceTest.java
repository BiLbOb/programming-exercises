package com.algorithmicalligator.exercises.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubsequenceTest {
    
    @Test
    void shouldFindWholeSubstring_whenAtStartOfLongerString() {
        assertEquals(4, LongestSubsequence.longestCommonSubsequence("ACDFG", "ACDF"));
    }
    
    @Test
    void shouldFindSubsubstring_whenAtEndOfLongerString() {
        assertEquals(6, LongestSubsequence.longestCommonSubsequence("ABCDEFG", "BCDEFGH"));
    }
    
    @Test
    void shouldFindWholeSubstring_whenStringsMatch() {
        assertEquals(3, LongestSubsequence.longestCommonSubsequence("XYZ", "XYZ"));
    }
    
    @Test
    void shouldFindSubsequence_whenBothStringsContainExtraCharacters() {
        assertEquals(4, LongestSubsequence.longestCommonSubsequence("ABCBDAB", "BDCAB"));
    }
    
    @Test
    void shouldFindSubsequence_whenBestMatchFollowsShorterSubsequence() {
        assertEquals(5, LongestSubsequence.longestCommonSubsequence("ABCBDABBDCAB", "BDCAB"));
    }
    
    @Test
    void shouldFindSubsequence_whenShorterSubsequenceFollowsBestMatch() {
        assertEquals(5, LongestSubsequence.longestCommonSubsequence("BDCABABCBDAB", "BDCAB"));
    }
}