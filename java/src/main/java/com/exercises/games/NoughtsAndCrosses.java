package com.exercises.games;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

public class NoughtsAndCrosses {
    public static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }
    
    public final static Integer EMPTY = Integer.valueOf((char)'.');
    public final static Integer NOUGHT = Integer.valueOf((char)'O');
    public final static Integer CROSS = Integer.valueOf((char)'X');

    private static boolean traceMoves = false;

    private static record Position(int row, int col) {
        public String toString() {
            return "("+row+","+col+")";
        }
    }
    private static record Turn(String positionId, Position move) {
        public String toString() {
            return positionId + " -> " + move;
        }
    }

    /**
     * This is a simplistic implementation of MENACE (Machine Educable Noughts And Crosses Engine)
     *  https://en.wikipedia.org/wiki/Matchbox_Educable_Noughts_and_Crosses_Engine   
     * It doesn't use reflection/rotation to reduce the number of boxes, and it doesn't persist the boxes between runs. 
     * Essentially we map each board position to a box containing a list of possible moves.
     * Then punish/reward the machine by removing/adding moves to the appropriate boxes, baseed on the winner.
     * Also - no tests, as I just hed to get it out of my head after a poor performance in a job quiz!
     */
    private static int MENACE_BOX_SIZE = 50;
    private static HashMap<String, LinkedList<Position>> boxes = new HashMap<>();

    private final int size;
    private final Integer[][] board;

    public NoughtsAndCrosses() {
        this(3);
    }

    public NoughtsAndCrosses(int size) {
        this.size = size;
        board = new Integer[size][size];
    }

    public void set(int row, int col, Integer value) {
        board[row][col] = value;
    }

    public Integer get(int row, int col) {
        return board[row][col];
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sb.append((char)(int)board[row][col]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String idString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sb.append((char)(int)board[row][col]);
            }
        }
        return sb.toString();
    }

    public int hashCode() {
        return idString().hashCode();
    }   

    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !(obj instanceof NoughtsAndCrosses) ) return false;
        NoughtsAndCrosses other = (NoughtsAndCrosses)obj;
        return other.idString().equals(this.idString());
    }

    public boolean isFull() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if( board[row][col] == EMPTY ) {
                    return false;
                }
            }
        }
        return true;
    }

    public Set<Position> availableMoves() {
        Set<Position> moves = new HashSet<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if( board[row][col] == EMPTY ) {
                    moves.add(new Position(row, col));
                }
            }
        }
        return moves;
    }

    public Optional<Integer> findWinner() {
        boolean rowAllNoughts = true;
        boolean rowAllCrosses = true;
        boolean colAllNoughts = true;  
        boolean colAllCrosses = true;
        boolean diagonalNwseAllNoughts = true;
        boolean diagonalNwseAllCrosses = true;
        boolean diagonalNeswAllNoughts = true;
        boolean diagonalNeswAllCrosses = true;

        for(int i = 0; i < size; i++) {
            // Check this rows/columns
            for(int j = 0; j < size; j++) {
                rowAllNoughts &= (board[i][j] == NOUGHT);
                rowAllCrosses &= (board[i][j] == CROSS);
                colAllNoughts &= (board[j][i] == NOUGHT);
                colAllCrosses &= (board[j][i] == CROSS);
            }
            // Check for winning row/column
            if( rowAllNoughts ) return Optional.of(NOUGHT);
            if( rowAllCrosses ) return Optional.of(CROSS);
            if( colAllNoughts ) return Optional.of(NOUGHT);
            if( colAllCrosses ) return Optional.of(CROSS);
            // Reset for next row/column
            rowAllNoughts = true;
            rowAllCrosses = true;
            colAllNoughts = true;  
            colAllCrosses = true;
            // Check this row and column on the diagonals
            diagonalNwseAllNoughts &= (board[i][i] == NOUGHT);
            diagonalNwseAllCrosses &= (board[i][i] == CROSS);   
            diagonalNeswAllNoughts &= (board[i][size-1-i] == NOUGHT);
            diagonalNeswAllCrosses &= (board[i][size-1-i] == CROSS);
        }
        // Check for winning diagonals
        if( diagonalNwseAllNoughts ) return Optional.of(NOUGHT);
        if( diagonalNwseAllCrosses ) return Optional.of(CROSS);
        if( diagonalNeswAllNoughts ) return Optional.of(NOUGHT);
        if( diagonalNeswAllCrosses ) return Optional.of(CROSS);

        return Optional.empty();
    }

    public Optional<Integer> makeMove(int row, int col, Integer value) throws InvalidMoveException {
        if(traceMoves) {
            System.out.println(this);
            System.out.println("Making move " + (char)(int)value + " to (" + row + "," + col + ")");
        }
        if(row < 0 || row >= size || col < 0 || col >= size) {
            throw new InvalidMoveException("Cell (" + row + "," + col + ") is out of bounds");
        } else if( board[row][col] != EMPTY ) {
            throw new InvalidMoveException("Cell (" + row + "," + col + ") is already occupied");   
        }
        board[row][col] = value;
        return findWinner();
    }

    private Position randomNextMove() {
        Set<Position> possibilities = availableMoves();
        return possibilities.toArray(new Position[0])[(int)(Math.random()*possibilities.size())];
    }
    
    private Position menaceNextMove() {
        String positionId = idString();
        if(! boxes.containsKey(positionId) ) {
            //System.out.println("Adding new box for position " + positionId);
            boxes = addUniformBoxesToMenace(MENACE_BOX_SIZE);
        }
        LinkedList<Position> possibilities = boxes.get(idString());
        if( possibilities.size() == 0 ) {
            //System.out.println("No possibilities in box for position " + positionId + " - choosing random move");
            return randomNextMove();
        } else {
            return possibilities.remove((int)(Math.random()*possibilities.size()));
        }
    }

    private HashMap<String, LinkedList<Position>> addUniformBoxesToMenace(int minBoxSize) {
        String positionId = idString();
        Set<Position> possibilities = availableMoves();
        LinkedList<Position> box = new LinkedList<>();
        while( box.size() < minBoxSize ) {
            box.addAll(possibilities);
        }
        boxes.put(positionId, box);

        return boxes;
    }

    public static NoughtsAndCrosses emptyBoard() { return emptyBoard(3);}

    public static NoughtsAndCrosses emptyBoard(int size) {
        NoughtsAndCrosses board = new NoughtsAndCrosses(size);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board.set(row, col, EMPTY);
            }
        }
        return board;
    }

    public static NoughtsAndCrosses randomFullBoard() { return randomFullBoard(3); }

    public static NoughtsAndCrosses randomFullBoard(int size) {
        NoughtsAndCrosses board = new NoughtsAndCrosses(size);
        int maxMoves = (size*size)/2 + 1;
        int noughtsTurns = maxMoves;
        int crossesTurns = maxMoves;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(noughtsTurns <= 0){
                    crossesTurns--;
                    board.set(row, col, CROSS);
                } else if( crossesTurns <= 0){
                    noughtsTurns--;
                    board.set(row, col, NOUGHT);
                } else {
                    if( Math.random() < 0.5) {
                        board.set(row, col, NOUGHT);
                        noughtsTurns--;
                    } else {
                        board.set(row, col, CROSS);
                        crossesTurns--;
                    }   
                }
            }
        }
        return board;
    }

    public static NoughtsAndCrosses randomFinishedGame() { return randomFinishedGame(3); }

    public static NoughtsAndCrosses randomFinishedGame(int size) {
        NoughtsAndCrosses board = emptyBoard(size);
        Optional<Integer> winner = Optional.empty();
        LinkedList<Turn> noughtTurns = new LinkedList<>();
        LinkedList<Turn> crossTurns = new LinkedList<>();
        Integer currentPlayer = NOUGHT; 
        while( !winner.isPresent() && !board.isFull() ) {
            Position move = board.menaceNextMove();
            try {
                String initialPositionId = board.idString();
                winner = board.makeMove(move.row(), move.col(), currentPlayer);

                LinkedList<Turn> turns = (currentPlayer == NOUGHT) ? noughtTurns : crossTurns;
                turns.add(new Turn(initialPositionId, move));

                if( winner.isPresent() || board.isFull()) {
                    if(winner.isPresent()) {
                        // Add 3 copies of each winning move to the appropriate boxes
                        LinkedList<Turn> winningTurns = winner.get() == NOUGHT ? noughtTurns : crossTurns;
                        //System.out.println("Rewarding: " + winningTurns);
                        for( Turn turn : winningTurns ) {
                            boxes.get(turn.positionId).add(turn.move);
                            boxes.get(turn.positionId).add(turn.move);
                            boxes.get(turn.positionId).add(turn.move);
                        }
                        // Remove 1 copy of each losing move from the appropriate boxes if present
                        LinkedList<Turn> losingTurns = winner.get() == CROSS ? noughtTurns : crossTurns;
                        //System.out.println("Punishing: " + losingTurns);
                        for( Turn turn : losingTurns ) {
                            if( boxes.get(turn.positionId).contains(turn.move) ) {
                                boxes.get(turn.positionId).remove(turn.move);
                            }
                        }
                    } else {
                        // For a draw add 1 copy of each move to the appropriate boxes
                        //System.out.println("Drawn game - bumping" + noughtTurns + " and " + crossTurns);
                        for( Turn turn : noughtTurns ) {
                            boxes.get(turn.positionId).add(turn.move);
                        }
                        for( Turn turn : crossTurns ) {
                            boxes.get(turn.positionId).add(turn.move);
                        }
                    }
                }
                currentPlayer = (currentPlayer == NOUGHT) ? CROSS : NOUGHT;
            } catch( InvalidMoveException e ) {
                System.out.print(".");
            }
        }
        return board;
    }

    public static void main(String[] args) {
        for (int game = 0; game < 40000; game++) {
            randomFinishedGame();
        }
        traceMoves = true;
        for (int game = 0; game < 10; game++) {
            NoughtsAndCrosses board = randomFinishedGame();
            System.out.println("\n" + board + "\n Winner: " + (char)board.findWinner().orElse(EMPTY).intValue()+"\n");
        }
        LinkedList<Position> firstBox = boxes.get(".........");
        HashMap<Position, Integer> positionCounts = new HashMap<>();
        for( Position pos : firstBox ) {
            positionCounts.put(pos, positionCounts.getOrDefault(pos, 0) + 1);
        }  
        System.out.println("Menace box for 1st move:-"+positionCounts); 
    }
}