package org.example.connect4;

public class Game {
    private GameBoard gameBoard;

    public Game() {
        gameBoard = new GameBoard(6, 7);
    }

    public void start() {
       
        System.out.println("Játék indul!");
      
    }
}
