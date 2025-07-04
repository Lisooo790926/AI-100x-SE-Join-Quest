package com.chess.steps;

import com.chess.ChineseChessService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ChineseChessSteps {
    
    private ChineseChessService chessService;
    private boolean moveResult;
    private boolean gameResult;
    
    public ChineseChessSteps() {
        this.chessService = new ChineseChessService();
    }
    
    @Given("the board is empty except for a Red General at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_general_at(int row, int col) {
        // 設置棋盤，只有一個紅將在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "General", row, col);
    }
    
    @When("Red moves the General from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_general_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveGeneral("Red", fromRow, fromCol, toRow, toCol);
    }
    
    @Then("the move is legal")
    public void the_move_is_legal() {
        assertTrue(moveResult, "Move should be legal");
    }
    
    @Then("the move is illegal")
    public void the_move_is_illegal() {
        assertFalse(moveResult, "Move should be illegal");
    }
    
    @Given("the board has:")
    public void the_board_has(DataTable dataTable) {
        // 設置空棋盤
        chessService.initializeBoard();
        
        // 根據 DataTable 設置棋子
        List<Map<String, String>> pieces = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> piece : pieces) {
            String pieceInfo = piece.get("Piece");
            String position = piece.get("Position");
            
            // 解析棋子資訊 "Color PieceType"
            String[] parts = pieceInfo.split(" ");
            String color = parts[0];
            String pieceType = parts[1];
            
            // 解析位置 "(row, col)"
            String[] coords = position.replaceAll("[()]", "").split(", ");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            
            chessService.placePiece(color, pieceType, row, col);
        }
    }
    
    @Given("the board is empty except for a Red Guard at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_guard_at(int row, int col) {
        // 設置棋盤，只有一個紅士在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "Guard", row, col);
    }
    
    @When("Red moves the Guard from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_guard_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveGuard("Red", fromRow, fromCol, toRow, toCol);
    }
    
    @Given("the board is empty except for a Red Rook at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_rook_at(int row, int col) {
        // 設置棋盤，只有一個紅車在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "Rook", row, col);
    }
    
    @When("Red moves the Rook from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_rook_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveRook("Red", fromRow, fromCol, toRow, toCol);
    }
    
    @Given("the board is empty except for a Red Horse at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_horse_at(int row, int col) {
        // 設置棋盤，只有一個紅馬在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "Horse", row, col);
    }
    
    @When("Red moves the Horse from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_horse_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveHorse("Red", fromRow, fromCol, toRow, toCol);
    }
    
    @Given("the board is empty except for a Red Cannon at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_cannon_at(int row, int col) {
        // 設置棋盤，只有一個紅炮在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "Cannon", row, col);
    }
    
    @When("Red moves the Cannon from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_cannon_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveCannon("Red", fromRow, fromCol, toRow, toCol);
    }
    
    @Given("the board is empty except for a Red Soldier at \\({int}, {int}\\)")
    public void the_board_is_empty_except_for_a_red_soldier_at(int row, int col) {
        // 設置棋盤，只有一個紅兵在指定位置
        chessService.initializeBoard();
        chessService.placePiece("Red", "Soldier", row, col);
    }
    
    @When("Red moves the Soldier from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void red_moves_the_soldier_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        // 執行移動
        moveResult = chessService.moveSoldier("Red", fromRow, fromCol, toRow, toCol);
    }

    @Given("the board is empty except for a Red Elephant at \\({int}, {int})")
    public void the_board_is_empty_except_for_a_red_elephant_at(int row, int col) {
        chessService.initializeBoard();
        chessService.placePiece("Red", "Elephant", row, col);
    }

    @When("Red moves the Elephant from \\({int}, {int}) to \\({int}, {int})")
    public void red_moves_the_elephant_from_to(int fromRow, int fromCol, int toRow, int toCol) {
        moveResult = chessService.moveElephant("Red", fromRow, fromCol, toRow, toCol);
    }

    @Then("Red wins immediately")
    public void red_wins_immediately() {
        assertTrue(chessService.isGameOver());
        assertEquals("Red", chessService.getWinner());
    }

    @Then("the game is not over just from that capture")
    public void the_game_is_not_over_just_from_that_capture() {
        assertFalse(chessService.isGameOver());
    }
} 