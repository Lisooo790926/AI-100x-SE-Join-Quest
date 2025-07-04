package com.chess;

public class InvalidMoveException extends RuntimeException {
    
    public InvalidMoveException(String message) {
        super(message);
    }
    
    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }
} 