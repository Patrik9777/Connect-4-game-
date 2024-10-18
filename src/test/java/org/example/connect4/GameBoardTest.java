package org.example.connect4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(6, 7);
    }

    @Test
    void testInitializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals('.', gameBoard.getCell(i, j), "A cell should be empty.");
            }
        }
    }



    @Test
    void testInvalidColumn() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.addDisc(8, 'Y');
        });
        assertEquals("Invalid column: 8", exception.getMessage());
    }


    @Test
    void testFullColumn() {
        for (int i = 0; i < 6; i++) {
            gameBoard.addDisc(0, 'R');
        }
        assertFalse(gameBoard.applyMove(0, 'Y'), "Column 0 should be full.");
    }

    @Test
    void testHorizontalWin() {
        for (int i = 0; i < 4; i++) {
            gameBoard.addDisc(i, 'R');
        }
        assertTrue(gameBoard.checkWin('R'), "Player R should win horizontally.");
    }

    @Test
    void testVerticalWin() {
        for (int i = 0; i < 4; i++) {
            gameBoard.addDisc(0, 'Y');
        }
        assertTrue(gameBoard.checkWin('Y'), "Player Y should win vertically.");
    }

    @Test
    void testDiagonalWin() {
        gameBoard.addDisc(0, 'R');
        gameBoard.addDisc(1, 'Y');
        gameBoard.addDisc(1, 'R');
        gameBoard.addDisc(2, 'Y');
        gameBoard.addDisc(2, 'Y');
        gameBoard.addDisc(2, 'R');
        gameBoard.addDisc(3, 'Y');
        gameBoard.addDisc(3, 'Y');
        gameBoard.addDisc(3, 'Y');
        gameBoard.addDisc(3, 'R');
        assertTrue(gameBoard.checkWin('R'), "Player R should win diagonally.");
    }


    @Test
    void testNoWinOnEmptyBoard() {
        assertFalse(gameBoard.checkWin('R'), "There should be no winner on an empty board.");
        assertFalse(gameBoard.checkWin('Y'), "There should be no winner on an empty board.");
    }


    @Test
    void testApplyMove() {
        GameBoard gameBoard = new GameBoard(6, 7);
        boolean result = gameBoard.applyMove(0, 'Y');
        assertTrue(result, "The move should be successful.");
    }


    @Test
    void testColumnFull() {
        for (int i = 0; i < 6; i++) {
            gameBoard.addDisc(0, 'R');
        }
        assertFalse(gameBoard.addDisc(0, 'Y'), "The column should be full and no more discs can be added.");
    }



    @Test
    void testInvalidMoveAfterBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard.addDisc(j, (i + j) % 2 == 0 ? 'R' : 'Y');
            }
        }
        assertFalse(gameBoard.applyMove(0, 'R'), "No moves should be possible after the board is full.");
    }
}


