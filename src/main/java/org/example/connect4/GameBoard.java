package org.example.connect4;

public class GameBoard {
    private final int rows;
    private final int columns;
    private final char[][] board;

    public GameBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '.'; // '.' represents an empty cell
            }
        }
    }

    public boolean applyMove(int column, char playerToken) {
        if (column < 0 || column >= columns) {
            throw new IllegalArgumentException("Invalid column: " + column);
        }
        for (int i = 0; i < rows; i++) {
            if (board[i][column] == '.') {
                board[i][column] = playerToken;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(char playerToken) {
        return checkHorizontalWin(playerToken) || checkVerticalWin(playerToken) || checkDiagonalWin(playerToken);
    }

    private boolean checkHorizontalWin(char playerToken) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns - 3; c++) {
                if (board[r][c] == playerToken && board[r][c + 1] == playerToken && board[r][c + 2] == playerToken && board[r][c + 3] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin(char playerToken) {
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows - 3; r++) {
                if (board[r][c] == playerToken && board[r + 1][c] == playerToken && board[r + 2][c] == playerToken && board[r + 3][c] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(char playerToken) {

        for (int r = 0; r < rows - 3; r++) {
            for (int c = 0; c < columns - 3; c++) {
                if (board[r][c] == playerToken && board[r + 1][c + 1] == playerToken && board[r + 2][c + 2] == playerToken && board[r + 3][c + 3] == playerToken) {
                    return true;
                }
            }
        }

        for (int r = 3; r < rows; r++) {
            for (int c = 0; c < columns - 3; c++) {
                if (board[r][c] == playerToken && board[r - 1][c + 1] == playerToken && board[r - 2][c + 2] == playerToken && board[r - 3][c + 3] == playerToken) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("  ");
        for (int j = 0; j < columns; j++) {
            sb.append((char) ('A' + j)).append(' ');
        }
        sb.append('\n');


        sb.append(" +");
        for (int j = 0; j < columns; j++) {
            sb.append("--");
        }
        sb.append('\n');


        for (int i = rows - 1; i >= 0; i--) {
            sb.append(i + 1).append(" |");
            for (int j = 0; j < columns; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public boolean addDisc(int column, char disc) {
        return applyMove(column, disc);
    }

    public char getCell(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Invalid row or column.");
        }
        return board[row][column];
    }

    public boolean isColumnValid(int column) {
        return column >= 0 && column < columns && board[rows - 1][column] == '.';
    }

    public int getColumns() {
        return columns;
    }
}
