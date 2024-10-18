package org.example.connect4;

import java.util.Random;

public class Bot extends Player {
    private final Random random;

    public Bot(String name, char token) {
        super(name, token);
        this.random = new Random();
    }

    public int chooseColumn(GameBoard board) {
        int column;
        boolean validMoveFound = false;


        for (int i = 0; i < board.getColumns(); i++) {
            if (board.isColumnValid(i)) {
                validMoveFound = true;
                break;
            }
        }


        if (!validMoveFound) {
            throw new IllegalStateException("No valid moves left for the bot.");
        }

        do {
            column = random.nextInt(board.getColumns());
        } while (!board.isColumnValid(column));
        return column;
    }
}
