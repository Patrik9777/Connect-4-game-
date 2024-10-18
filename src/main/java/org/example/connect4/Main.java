package org.example.connect4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard board = new GameBoard(6, 7); // 6 rows and 7 columns
        Player player1 = new Player("Player 1 (Yellow)", 'Y');
        Player player2 = new Player("Player 2 (Red)", 'R');
        Player currentPlayer = player1;

        System.out.println("Welcome to Connect-4");
        System.out.println(board);

        while (true) {
            System.out.printf("%s, choose a column (0-6): ", currentPlayer.getName());
            int column = scanner.nextInt();

            if (!board.applyMove(column, currentPlayer.getToken())) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            System.out.println(board);

            if (board.checkWin(currentPlayer.getToken())) {
                System.out.printf("%s wins!\n", currentPlayer.getName());
                break;
            }

            // Switch player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
        scanner.close();
    }
}
