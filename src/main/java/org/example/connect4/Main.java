package org.example.connect4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name for Player 1: ");
            String player1Name = scanner.nextLine();

            GameBoard board = new GameBoard(6, 7); 
            Player player1 = new Player(player1Name + " (Yellow)", 'X');
            Bot bot = new Bot("Bot (Red)", 'O');
            Player currentPlayer = player1;

            System.out.println("Welcome to Connect-4");
            System.out.println(board);

            while (true) {
                if (currentPlayer instanceof Bot) {
                    try {
                        int column = ((Bot) currentPlayer).chooseColumn(board);
                        board.applyMove(column, currentPlayer.getToken());
                        System.out.printf("%s chooses column %d\n", currentPlayer.getName(), column + 1);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                } else {
                    System.out.printf("%s, choose a column (1-7): ", currentPlayer.getName());
                    int column = scanner.nextInt() - 1; 
                    if (column < 0 || column >= board.getColumns() || !board.applyMove(column, currentPlayer.getToken())) {
                        System.out.println("Invalid move, try again.");
                        continue;
                    }
                }

                System.out.println(board);

                if (board.checkWin(currentPlayer.getToken())) {
                    System.out.printf("%s wins!\n", currentPlayer.getName());
                    break;
                }

                currentPlayer = (currentPlayer == player1) ? bot : player1;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for playing! Have a great day!");
                break;
            }
        }

        scanner.close();
    }
}
