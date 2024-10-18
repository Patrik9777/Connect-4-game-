package org.example.connect4;

public class Game {
    private GameBoard gameBoard;

    public Game() {
        gameBoard = new GameBoard(6, 7); // Példa: 6 sor és 7 oszlop
    }

    public void start() {
        // Indítsd el a játékot itt
        System.out.println("Játék indul!");
        // További játékmenet logika
    }
}
