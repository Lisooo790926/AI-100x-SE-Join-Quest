package com.chess;

public record Position(int row, int col) {
    
    public Position {
        if (row < 1 || row > 10) {
            throw new IllegalArgumentException("Row must be between 1 and 10, got: " + row);
        }
        if (col < 1 || col > 9) {
            throw new IllegalArgumentException("Column must be between 1 and 9, got: " + col);
        }
    }
    
    public static Position of(int row, int col) {
        return new Position(row, col);
    }
    
    public String toKey() {
        return row + "," + col;
    }
    
    public static Position fromKey(String key) {
        String[] parts = key.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid position key: " + key);
        }
        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            return new Position(row, col);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid position key: " + key, e);
        }
    }
} 