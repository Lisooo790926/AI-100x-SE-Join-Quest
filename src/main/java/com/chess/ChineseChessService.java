package com.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.chess.ChessConstants.*;

public class ChineseChessService {
    
    // 棋盤表示：key = Position, value = Piece
    private Map<Position, Piece> board;
    private boolean gameOver;
    private Color winner;
    
    public ChineseChessService() {
        this.board = new HashMap<>();
        this.gameOver = false;
        this.winner = null;
    }
    
    public void initializeBoard() {
        board.clear();
        this.gameOver = false;
        this.winner = null;
    }
    
    public void placePiece(Color color, PieceType pieceType, int row, int col) {
        Objects.requireNonNull(color, "Color cannot be null");
        Objects.requireNonNull(pieceType, "PieceType cannot be null");
        
        Position position = Position.of(row, col);
        Piece piece = Piece.of(color, pieceType);
        board.put(position, piece);
    }
    
    // Legacy method for backward compatibility
    public void placePiece(String color, String pieceType, int row, int col) {
        Color colorEnum = Color.fromString(color);
        PieceType pieceTypeEnum = PieceType.fromString(pieceType);
        placePiece(colorEnum, pieceTypeEnum, row, col);
    }
    
    public boolean moveGeneral(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidGeneralMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.GENERAL));
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveGuard(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidGuardMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.GUARD));
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveRook(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidRookMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.ROOK));
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveHorse(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidHorseMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.HORSE));
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveCannon(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidCannonMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.CANNON));
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveSoldier(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidSoldierMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.SOLDIER));
        
        checkGameOver(targetPiece);
        return true;
    }

    public boolean moveElephant(String color, int fromRow, int fromCol, int toRow, int toCol) {
        Color colorEnum = Color.fromString(color);
        Position fromPos = Position.of(fromRow, fromCol);
        Position toPos = Position.of(toRow, toCol);
        
        if (!isValidElephantMove(colorEnum, fromPos, toPos)) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        if (targetPiece != null && targetPiece.color() == colorEnum) {
            return false;
        }
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(colorEnum, PieceType.ELEPHANT));
        
        checkGameOver(targetPiece);
        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner != null ? winner.getDisplayName() : null;
    }
    
    /**
     * 檢查位置是否在宮內
     * 紅方宮：行 1-3，列 4-6
     * 黑方宮：行 8-10，列 4-6
     */
    private boolean isInPalace(Color color, Position position) {
        int row = position.row();
        int col = position.col();
        
        // 列必須在 4-6 之間
        if (col < PALACE_MIN_COL || col > PALACE_MAX_COL) {
            return false;
        }
        
        if (Color.RED == color) {
            // 紅方宮：行 1-3
            return row >= RED_PALACE_MIN_ROW && row <= RED_PALACE_MAX_ROW;
        } else if (Color.BLACK == color) {
            // 黑方宮：行 8-10
            return row >= BLACK_PALACE_MIN_ROW && row <= BLACK_PALACE_MAX_ROW;
        }
        
        return false;
    }
    
    /**
     * 檢查兩個將軍是否面對面
     * 兩將面對面的條件：在同一列且中間沒有其他棋子
     */
    private boolean areGeneralsFacing() {
        // 找到紅將和黑將的位置
        Position redGeneralPos = null;
        Position blackGeneralPos = null;
        
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            
            if (piece.color() == Color.RED && piece.type() == PieceType.GENERAL) {
                redGeneralPos = position;
            } else if (piece.color() == Color.BLACK && piece.type() == PieceType.GENERAL) {
                blackGeneralPos = position;
            }
        }
        
        // 如果找不到其中一個將軍，則不面對面
        if (redGeneralPos == null || blackGeneralPos == null) {
            return false;
        }
        
        // 檢查是否在同一列
        if (redGeneralPos.col() != blackGeneralPos.col()) {
            return false;
        }
        
        // 檢查兩將之間是否有其他棋子
        int minRow = Math.min(redGeneralPos.row(), blackGeneralPos.row());
        int maxRow = Math.max(redGeneralPos.row(), blackGeneralPos.row());
        
        for (int row = minRow + 1; row < maxRow; row++) {
            Position checkPos = Position.of(row, redGeneralPos.col());
            if (board.containsKey(checkPos)) {
                return false; // 中間有棋子，不面對面
            }
        }
        
        return true; // 兩將面對面
    }
    
    /**
     * 檢查從起始位置到目標位置的路徑是否清晰
     * 適用於直線移動（橫向或縱向）
     */
    private boolean isPathClear(Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();
        
        // 計算移動方向
        int rowStep = 0;
        int colStep = 0;
        
        if (fromRow != toRow) {
            rowStep = (toRow > fromRow) ? 1 : -1;
        }
        if (fromCol != toCol) {
            colStep = (toCol > fromCol) ? 1 : -1;
        }
        
        // 檢查路徑上的每一格（不包括起始和目標位置）
        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;
        
        while (currentRow != toRow || currentCol != toCol) {
            Position checkPos = Position.of(currentRow, currentCol);
            if (board.containsKey(checkPos)) {
                return false; // 路徑被阻擋
            }
            
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        return true; // 路徑清晰
    }
    
    /**
     * 檢查馬腳是否被堵（蹩腿）
     * 馬走日字時，必須檢查相鄰的直線方向是否有棋子阻擋
     */
    private boolean isHorseBlocked(Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();
        
        // 計算移動差距
        int rowDiff = toRow - fromRow;
        int colDiff = toCol - fromCol;
        
        // 確定馬腳位置（相鄰的直線方向）
        int legRow, legCol;
        
        if (Math.abs(rowDiff) == HORSE_L_SHAPE_LONG) {
            // 縱向移動2格，馬腳在縱向相鄰位置
            legRow = fromRow + (rowDiff > 0 ? 1 : -1);
            legCol = fromCol;
        } else {
            // 橫向移動2格，馬腳在橫向相鄰位置
            legRow = fromRow;
            legCol = fromCol + (colDiff > 0 ? 1 : -1);
        }
        
        // 檢查馬腳位置是否有棋子
        Position legPosition = Position.of(legRow, legCol);
        return board.containsKey(legPosition);
    }
    
    /**
     * 檢查炮的攻擊是否有效
     * 炮攻擊時必須跳過一個棋子（炮架）
     */
    private boolean isCannonAttackValid(Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();
        
        // 計算移動方向
        int rowStep = 0;
        int colStep = 0;
        
        if (fromRow != toRow) {
            rowStep = (toRow > fromRow) ? 1 : -1;
        }
        if (fromCol != toCol) {
            colStep = (toCol > fromCol) ? 1 : -1;
        }
        
        // 計算路徑上的棋子數量（不包括起始和目標位置）
        int pieceCount = 0;
        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;
        
        while (currentRow != toRow || currentCol != toCol) {
            Position checkPos = Position.of(currentRow, currentCol);
            if (board.containsKey(checkPos)) {
                pieceCount++;
            }
            
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        // 炮攻擊時路徑上必須有且僅有一個棋子（炮架）
        return pieceCount == CANNON_REQUIRED_JUMP_PIECES;
    }

    private boolean isValidSoldierMove(Color color, Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        
        if (Color.RED == color) {
            if (fromRow <= RIVER_BOUNDARY) {
                return rowDiff == GENERAL_MOVE_DISTANCE && colDiff == 0;
            } else {
                return (rowDiff == GENERAL_MOVE_DISTANCE && colDiff == 0) || 
                       (rowDiff == 0 && colDiff == GENERAL_MOVE_DISTANCE);
            }
        } else {
            if (fromRow >= RIVER_BOUNDARY + 1) {
                return rowDiff == -GENERAL_MOVE_DISTANCE && colDiff == 0;
            } else {
                return (rowDiff == -GENERAL_MOVE_DISTANCE && colDiff == 0) || 
                       (rowDiff == 0 && colDiff == GENERAL_MOVE_DISTANCE);
            }
        }
    }

    private boolean isValidGeneralMove(Color color, Position fromPos, Position toPos) {
        if (!isInPalace(color, toPos)) {
            return false;
        }
        
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (!((rowDiff == GENERAL_MOVE_DISTANCE && colDiff == 0) || 
              (rowDiff == 0 && colDiff == GENERAL_MOVE_DISTANCE))) {
            return false;
        }
        
        Piece originalPiece = board.get(fromPos);
        Piece targetPiece = board.get(toPos);
        
        board.remove(fromPos);
        board.put(toPos, Piece.of(color, PieceType.GENERAL));
        
        boolean facing = areGeneralsFacing();
        
        board.remove(toPos);
        if (originalPiece != null) {
            board.put(fromPos, originalPiece);
        }
        if (targetPiece != null) {
            board.put(toPos, targetPiece);
        }
        
        return !facing;
    }

    private boolean isValidGuardMove(Color color, Position fromPos, Position toPos) {
        if (!isInPalace(color, toPos)) {
            return false;
        }
        
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        return rowDiff == GUARD_DIAGONAL_DISTANCE && colDiff == GUARD_DIAGONAL_DISTANCE;
    }

    private boolean isValidRookMove(Color color, Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        return isPathClear(fromPos, toPos);
    }

    private boolean isValidHorseMove(Color color, Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (!((rowDiff == HORSE_L_SHAPE_LONG && colDiff == HORSE_L_SHAPE_SHORT) || 
              (rowDiff == HORSE_L_SHAPE_SHORT && colDiff == HORSE_L_SHAPE_LONG))) {
            return false;
        }
        
        return !isHorseBlocked(fromPos, toPos);
    }

    private boolean isValidCannonMove(Color color, Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        Piece targetPiece = board.get(toPos);
        
        if (targetPiece != null) {
            return isCannonAttackValid(fromPos, toPos);
        } else {
            return isPathClear(fromPos, toPos);
        }
    }

    private boolean isValidElephantMove(Color color, Position fromPos, Position toPos) {
        int fromRow = fromPos.row();
        int fromCol = fromPos.col();
        int toRow = toPos.row();
        int toCol = toPos.col();

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (rowDiff != ELEPHANT_DIAGONAL_DISTANCE || colDiff != ELEPHANT_DIAGONAL_DISTANCE) {
            return false;
        }
        
        if (Color.RED == color && toRow > RIVER_BOUNDARY) {
            return false;
        }
        if (Color.BLACK == color && toRow < RIVER_BOUNDARY + 1) {
            return false;
        }
        
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        Position midPos = Position.of(midRow, midCol);
        return board.get(midPos) == null;
    }

    private void checkGameOver(Piece capturedPiece) {
        if (capturedPiece != null && capturedPiece.type() == PieceType.GENERAL) {
            gameOver = true;
            winner = capturedPiece.color() == Color.RED ? Color.BLACK : Color.RED;
        }
    }
} 