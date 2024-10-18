package org.example.connect4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testAddDisc() {
        gameBoard.addDisc(0, 'Y');
        assertEquals('Y', gameBoard.getCell(5, 0), "The bottom cell in column 0 should have a yellow disc.");
    }

    @Test
    void testInvalidColumn() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameBoard.addDisc(7, 'Y');
        });
        assertEquals("Invalid column: 7", exception.getMessage());
    }

}
