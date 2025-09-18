package com.algorithmicalligator.exercises.algorithms;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

public class LongestSubsequence {

    private record Result(int length, int from) {}

    private static int[][] findMatches(String shortest, String longest) {
        // Find all possible positions for each character in the shorter string
        ArrayList<List<Integer>> characterPositions = new ArrayList<>(
            shortest.length()
        );
        for (int i = 0; i < shortest.length(); i++) {
            char c = shortest.charAt(i);
            int index = longest.indexOf(c);
            // We could cache these in a map to reduce searching
            LinkedList<Integer> indexes = new LinkedList<>();
            while (index >= 0) {
                indexes.add(index);
                index = longest.indexOf(c, index + 1);
            }
            characterPositions.add(indexes);
        }
        int[][] result = new int[shortest.length()][];  
        for (int i = 0; i < characterPositions.size(); i++) {
            List<Integer> indexes = characterPositions.get(i);
            result[i] = indexes.stream().mapToInt(Integer::intValue).toArray();
        }
        return result;
    }

    private static Result findLongestFrom(
        int[][] characterPositions,
        int start
    ) {
        // Go through the indexes, finding the next highest index for each character
        int previousIndex = start;
        int subsequenceLength = 0;
        int from = -1;
        for (int m = 0; m < characterPositions.length; m++) {
            int[] matches = characterPositions[m];
            final int previous = previousIndex;
            OptionalInt nextIndex = Arrays.stream(matches)
                .filter(i -> i > previous)
                .findFirst();
            if (nextIndex.isPresent()) {
                int next = nextIndex.getAsInt();
                from = from >= 0 ? from : next;
                previousIndex = next;
                subsequenceLength++;
            }
        }
        return new Result(subsequenceLength, from);
    }

    public static int longestCommonSubsequence(String X, String Y) {
        // The longest subsequence cannot be longer than the shorter of the 2 strings
        boolean xShorter = X.length() < Y.length();
        String shortest = xShorter ? X : Y;
        String longest = xShorter ? Y : X;

        // Assuming the string is ASCII (hence not multibyte characters)
        //  we need to use code-points for full Unicode support
        if (shortest.length() != shortest.getBytes().length) {
            throw new IllegalArgumentException("Unsupported multibyte string found in "+shortest);
        }
        if (longest.length() != longest.getBytes().length) {
            throw new IllegalArgumentException("Unsupported multibyte string found in "+longest);
        }

        // The simplest solution is that the full substring requires no deletions
        if (longest.contains(shortest)) {
            return shortest.length();
        }

        // Find all possible positions for each character in the shorter string
        int[][] characterPositions = findMatches(shortest, longest);

        // Try again, excluding the first character each time, 
        //  or moving to the next character if no match found
        int previousIndex = -1;
        int longestSubsequenceLength = 0;
        int[][] remainingMatches = characterPositions;
        // If there are not enough characters left to beat the longest found so far, stop
        while (remainingMatches.length > longestSubsequenceLength) {
            Result result = findLongestFrom(characterPositions, previousIndex);
            if (result.length() > 0) {
                previousIndex = result.from();
                if (result.length() > longestSubsequenceLength) {
                    longestSubsequenceLength = result.length();
                }
            } else {
                remainingMatches = Arrays.copyOfRange(remainingMatches, 1, remainingMatches.length);
            }
        }
        return longestSubsequenceLength;
    }

}
