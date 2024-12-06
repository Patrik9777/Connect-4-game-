package org.example.connect4;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Would you like to reset the scores? (yes/no): ");
        String resetResponse = scanner.nextLine();

        if (resetResponse.equalsIgnoreCase("yes")) {
            DatabaseManager.resetScores();
        }

        while (true) {
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            String cleanPlayerName = playerName.replaceAll(" \\(Yellow\\)| \\(Red\\)", "").trim();

            GameBoard board = new GameBoard(6, 7);
            Player player = new Player(playerName + " (Yellow)", 'X');
            Bot bot = new Bot("Bot", 'O');
            Player currentPlayer = player;

            System.out.println("Welcome to Connect-4");
            System.out.println(board);

            while (true) {
                if (currentPlayer instanceof Bot) {
                    int column = ((Bot) currentPlayer).chooseColumn(board);
                    board.applyMove(column, currentPlayer.getToken());
                    System.out.printf("%s chooses column %d\n", currentPlayer.getName(), column + 1);
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
                    if (currentPlayer == player) {
                        DatabaseManager.upsertPlayer(cleanPlayerName, 1);
                    } else if (currentPlayer == bot) {
                        DatabaseManager.upsertPlayer(bot.getName(), 1);
                    }
                    break;
                }

                currentPlayer = (currentPlayer == player) ? bot : player;
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

        List<String> highScores = DatabaseManager.getHighScores();
        System.out.println("High Scores:");
        for (String score : highScores) {
            System.out.println(score);
        }
    }
}
