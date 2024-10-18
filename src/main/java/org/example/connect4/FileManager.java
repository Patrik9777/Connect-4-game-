package org.example.connect4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static void saveGame(GameBoard board, String filePath) {
        try {
            Files.writeString(Path.of(filePath), board.toString());
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static GameBoard loadGame(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            // Parse the content and create GameBoard object
            // For this example, we won't implement loading from file
            return new GameBoard(6, 7); // Default board size
        } catch (IOException e) {
            System.out.println("No saved game found, starting new game.");
            return new GameBoard(6, 7);  // Default board size
        }
    }
}
