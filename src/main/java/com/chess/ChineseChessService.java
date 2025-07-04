package com.chess;

import java.util.HashMap;
import java.util.Map;

public class ChineseChessService {
    
    private static final int BOARD_ROWS = 10;
    private static final int BOARD_COLS = 9;
    
    // 棋盤表示：key = "row,col", value = "Color PieceType"
    private Map<String, String> board;
    private boolean gameOver;
    private String winner;
    
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
    
    public void placePiece(String color, String pieceType, int row, int col) {
        String position = row + "," + col;
        String piece = color + " " + pieceType;
        board.put(position, piece);
    }
    
    public boolean moveGeneral(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidGeneralMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " General");
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveGuard(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidGuardMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Guard");
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveRook(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidRookMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Rook");
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveHorse(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidHorseMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Horse");
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveCannon(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidCannonMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Cannon");
        
        checkGameOver(targetPiece);
        return true;
    }
    
    public boolean moveSoldier(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidSoldierMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Soldier");
        
        checkGameOver(targetPiece);
        return true;
    }

    public boolean moveElephant(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidElephantMove(color, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        if (targetPiece != null && targetPiece.startsWith(color)) {
            return false;
        }
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " Elephant");
        
        checkGameOver(targetPiece);
        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }
    
    /**
     * 檢查位置是否在宮內
     * 紅方宮：行 1-3，列 4-6
     * 黑方宮：行 8-10，列 4-6
     */
    private boolean isInPalace(String color, int row, int col) {
        // 列必須在 4-6 之間
        if (col < 4 || col > 6) {
            return false;
        }
        
        if ("Red".equals(color)) {
            // 紅方宮：行 1-3
            return row >= 1 && row <= 3;
        } else if ("Black".equals(color)) {
            // 黑方宮：行 8-10
            return row >= 8 && row <= 10;
        }
        
        return false;
    }
    
    /**
     * 檢查兩個將軍是否面對面
     * 兩將面對面的條件：在同一列且中間沒有其他棋子
     */
    private boolean areGeneralsFacing() {
        // 找到紅將和黑將的位置
        String redGeneralPos = null;
        String blackGeneralPos = null;
        
        for (Map.Entry<String, String> entry : board.entrySet()) {
            String position = entry.getKey();
            String piece = entry.getValue();
            
            if ("Red General".equals(piece)) {
                redGeneralPos = position;
            } else if ("Black General".equals(piece)) {
                blackGeneralPos = position;
            }
        }
        
        // 如果找不到其中一個將軍，則不面對面
        if (redGeneralPos == null || blackGeneralPos == null) {
            return false;
        }
        
        // 解析位置
        String[] redCoords = redGeneralPos.split(",");
        String[] blackCoords = blackGeneralPos.split(",");
        
        int redRow = Integer.parseInt(redCoords[0]);
        int redCol = Integer.parseInt(redCoords[1]);
        int blackRow = Integer.parseInt(blackCoords[0]);
        int blackCol = Integer.parseInt(blackCoords[1]);
        
        // 檢查是否在同一列
        if (redCol != blackCol) {
            return false;
        }
        
        // 檢查兩將之間是否有其他棋子
        int minRow = Math.min(redRow, blackRow);
        int maxRow = Math.max(redRow, blackRow);
        
        for (int row = minRow + 1; row < maxRow; row++) {
            String checkPos = row + "," + redCol;
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
    private boolean isPathClear(int fromRow, int fromCol, int toRow, int toCol) {
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
            String checkPos = currentRow + "," + currentCol;
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
    private boolean isHorseBlocked(int fromRow, int fromCol, int toRow, int toCol) {
        // 計算移動差距
        int rowDiff = toRow - fromRow;
        int colDiff = toCol - fromCol;
        
        // 確定馬腳位置（相鄰的直線方向）
        int legRow, legCol;
        
        if (Math.abs(rowDiff) == 2) {
            // 縱向移動2格，馬腳在縱向相鄰位置
            legRow = fromRow + (rowDiff > 0 ? 1 : -1);
            legCol = fromCol;
        } else {
            // 橫向移動2格，馬腳在橫向相鄰位置
            legRow = fromRow;
            legCol = fromCol + (colDiff > 0 ? 1 : -1);
        }
        
        // 檢查馬腳位置是否有棋子
        String legPosition = legRow + "," + legCol;
        return board.containsKey(legPosition);
    }
    
    /**
     * 檢查炮的攻擊是否有效
     * 炮攻擊時必須跳過一個棋子（炮架）
     */
    private boolean isCannonAttackValid(int fromRow, int fromCol, int toRow, int toCol) {
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
            String checkPos = currentRow + "," + currentCol;
            if (board.containsKey(checkPos)) {
                pieceCount++;
            }
            
            currentRow += rowStep;
            currentCol += colStep;
        }
        
        // 炮攻擊時路徑上必須有且僅有一個棋子（炮架）
        return pieceCount == 1;
    }

    private boolean isValidSoldierMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        
        if ("Red".equals(color)) {
            if (fromRow <= 5) {
                return rowDiff == 1 && colDiff == 0;
            } else {
                return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
            }
        } else {
            if (fromRow >= 6) {
                return rowDiff == -1 && colDiff == 0;
            } else {
                return (rowDiff == -1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
            }
        }
    }

    private boolean isValidGeneralMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isInPalace(color, toRow, toCol)) {
            return false;
        }
        
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (!((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1))) {
            return false;
        }
        
        String originalPiece = board.get(fromRow + "," + fromCol);
        String targetPiece = board.get(toRow + "," + toCol);
        
        board.remove(fromRow + "," + fromCol);
        board.put(toRow + "," + toCol, color + " General");
        
        boolean facing = areGeneralsFacing();
        
        board.remove(toRow + "," + toCol);
        if (originalPiece != null) {
            board.put(fromRow + "," + fromCol, originalPiece);
        }
        if (targetPiece != null) {
            board.put(toRow + "," + toCol, targetPiece);
        }
        
        return !facing;
    }

    private boolean isValidGuardMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (!isInPalace(color, toRow, toCol)) {
            return false;
        }
        
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        return rowDiff == 1 && colDiff == 1;
    }

    private boolean isValidRookMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        return isPathClear(fromRow, fromCol, toRow, toCol);
    }

    private boolean isValidHorseMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }
        
        return !isHorseBlocked(fromRow, fromCol, toRow, toCol);
    }

    private boolean isValidCannonMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        String targetPiece = board.get(toRow + "," + toCol);
        
        if (targetPiece != null) {
            return isCannonAttackValid(fromRow, fromCol, toRow, toCol);
        } else {
            return isPathClear(fromRow, fromCol, toRow, toCol);
        }
    }

    private boolean isValidElephantMove(String color, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        if (rowDiff != 2 || colDiff != 2) {
            return false;
        }
        
        if ("Red".equals(color) && toRow > 5) {
            return false;
        }
        if ("Black".equals(color) && toRow < 6) {
            return false;
        }
        
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        return board.get(midRow + "," + midCol) == null;
    }

    private void checkGameOver(String capturedPiece) {
        if (capturedPiece != null && capturedPiece.contains("General")) {
            gameOver = true;
            winner = capturedPiece.startsWith("Red") ? "Black" : "Red";
        }
    }
} 