package com.chess;

public final class ChessConstants {
    
    // Board dimensions
    public static final int BOARD_ROWS = 10;
    public static final int BOARD_COLS = 9;
    
    // Palace boundaries
    public static final int RED_PALACE_MIN_ROW = 1;
    public static final int RED_PALACE_MAX_ROW = 3;
    public static final int BLACK_PALACE_MIN_ROW = 8;
    public static final int BLACK_PALACE_MAX_ROW = 10;
    public static final int PALACE_MIN_COL = 4;
    public static final int PALACE_MAX_COL = 6;
    
    // River boundary
    public static final int RIVER_BOUNDARY = 5;
    
    // Movement patterns
    public static final int GENERAL_MOVE_DISTANCE = 1;
    public static final int GUARD_DIAGONAL_DISTANCE = 1;
    public static final int HORSE_L_SHAPE_LONG = 2;
    public static final int HORSE_L_SHAPE_SHORT = 1;
    public static final int ELEPHANT_DIAGONAL_DISTANCE = 2;
    public static final int CANNON_REQUIRED_JUMP_PIECES = 1;
    
    private ChessConstants() {
        // Utility class - prevent instantiation
    }
} 