package com.chess;

public enum PieceType {
    GENERAL("General"),
    GUARD("Guard"),
    ROOK("Rook"),
    HORSE("Horse"),
    CANNON("Cannon"),
    SOLDIER("Soldier"),
    ELEPHANT("Elephant");
    
    private final String displayName;
    
    PieceType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static PieceType fromString(String pieceTypeStr) {
        for (PieceType pieceType : PieceType.values()) {
            if (pieceType.displayName.equals(pieceTypeStr)) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException("Invalid piece type: " + pieceTypeStr);
    }
} 