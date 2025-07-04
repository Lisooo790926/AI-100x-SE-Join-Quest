package com.chess;

public record Piece(Color color, PieceType type) {
    
    public static Piece of(Color color, PieceType type) {
        return new Piece(color, type);
    }
    
    public String toDisplayString() {
        return color.getDisplayName() + " " + type.getDisplayName();
    }
    
    public static Piece fromDisplayString(String displayString) {
        String[] parts = displayString.split(" ", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid piece display string: " + displayString);
        }
        
        Color color = Color.fromString(parts[0]);
        PieceType type = PieceType.fromString(parts[1]);
        return new Piece(color, type);
    }
    
    public boolean isOpponentOf(Piece other) {
        return this.color != other.color;
    }
    
    public boolean isSameColorAs(Piece other) {
        return this.color == other.color;
    }
} 