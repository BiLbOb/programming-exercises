package com.algorithmicalligator.exercises.games;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class NoughtsAndCrossesTest {
    
    private NoughtsAndCrosses game;
    
    @BeforeEach
    void setUp() {
        game = NoughtsAndCrosses.emptyBoard();
    }
    
    @Test
    void testEmptyBoardCreation() {
        assertEquals(3, game.getSize());
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals(NoughtsAndCrosses.EMPTY, game.get(row, col));
            }
        }
    }
    
    @Test
    void testValidMove() throws NoughtsAndCrosses.InvalidMoveException {
        game.makeMove(1, 1, NoughtsAndCrosses.CROSS);
        assertEquals(NoughtsAndCrosses.CROSS, game.get(1, 1));
    }
    
    @Test
    void testInvalidMoveOutOfBounds() {
        assertThrows(NoughtsAndCrosses.InvalidMoveException.class, () -> {
            game.makeMove(3, 3, NoughtsAndCrosses.CROSS);
        });
    }
    
    @Test
    void testInvalidMoveOccupiedCell() throws NoughtsAndCrosses.InvalidMoveException {
        game.makeMove(1, 1, NoughtsAndCrosses.CROSS);
        assertThrows(NoughtsAndCrosses.InvalidMoveException.class, () -> {
            game.makeMove(1, 1, NoughtsAndCrosses.NOUGHT);
        });
    }
    
    @Test
    void testWinnerDetectionRow() throws NoughtsAndCrosses.InvalidMoveException {
        // Create winning row for X
        game.makeMove(0, 0, NoughtsAndCrosses.CROSS);
        game.makeMove(0, 1, NoughtsAndCrosses.CROSS);
        var winner = game.makeMove(0, 2, NoughtsAndCrosses.CROSS);
        
        assertTrue(winner.isPresent());
        assertEquals(NoughtsAndCrosses.CROSS, winner.get());
    }
    
    @Test
    void testWinnerDetectionColumn() throws NoughtsAndCrosses.InvalidMoveException {
        // Create winning column for O
        game.makeMove(0, 0, NoughtsAndCrosses.NOUGHT);
        game.makeMove(1, 0, NoughtsAndCrosses.NOUGHT);
        var winner = game.makeMove(2, 0, NoughtsAndCrosses.NOUGHT);
        
        assertTrue(winner.isPresent());
        assertEquals(NoughtsAndCrosses.NOUGHT, winner.get());
    }
    
    @Test
    void testWinnerDetectionDiagonal() throws NoughtsAndCrosses.InvalidMoveException {
        // Create winning diagonal for X
        game.makeMove(0, 0, NoughtsAndCrosses.CROSS);
        game.makeMove(1, 1, NoughtsAndCrosses.CROSS);
        var winner = game.makeMove(2, 2, NoughtsAndCrosses.CROSS);
        
        assertTrue(winner.isPresent());
        assertEquals(NoughtsAndCrosses.CROSS, winner.get());
    }
    
    @Test
    void testNoWinnerYet() throws NoughtsAndCrosses.InvalidMoveException {
        game.makeMove(0, 0, NoughtsAndCrosses.CROSS);
        var winner = game.makeMove(1, 1, NoughtsAndCrosses.NOUGHT);
        
        assertFalse(winner.isPresent());
    }
    
    @Test
    void testBoardFull() throws NoughtsAndCrosses.InvalidMoveException {
        // Fill the board
        game.makeMove(0, 0, NoughtsAndCrosses.CROSS);
        game.makeMove(0, 1, NoughtsAndCrosses.NOUGHT);
        game.makeMove(0, 2, NoughtsAndCrosses.CROSS);
        game.makeMove(1, 0, NoughtsAndCrosses.NOUGHT);
        game.makeMove(1, 1, NoughtsAndCrosses.CROSS);
        game.makeMove(1, 2, NoughtsAndCrosses.NOUGHT);
        game.makeMove(2, 0, NoughtsAndCrosses.NOUGHT);
        game.makeMove(2, 1, NoughtsAndCrosses.CROSS);
        game.makeMove(2, 2, NoughtsAndCrosses.NOUGHT);
        
        assertTrue(game.isFull());
    }
    
    @Test
    void testAvailableMoves() throws NoughtsAndCrosses.InvalidMoveException {
        assertEquals(9, game.availableMoves().size());
        
        game.makeMove(1, 1, NoughtsAndCrosses.CROSS);
        assertEquals(8, game.availableMoves().size());
    }
}