package com.chess;

public enum Color {
    RED("Red"),
    BLACK("Black");
    
    private final String displayName;
    
    Color(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static Color fromString(String colorStr) {
        for (Color color : Color.values()) {
            if (color.displayName.equals(colorStr)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Invalid color: " + colorStr);
    }
} 