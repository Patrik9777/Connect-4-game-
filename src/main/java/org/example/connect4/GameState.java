package org.example.connect4;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class GameState {
    private String playerName;
    private String botName;
    private int playerScore;
    private int botScore;
    private char[][] board;

    public GameState() { }

    public GameState(String playerName, String botName, int playerScore, int botScore, char[][] board) {
        this.playerName = playerName;
        this.botName = botName;
        this.playerScore = playerScore;
        this.botScore = botScore;
        this.board = board;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getBotScore() {
        return botScore;
    }

    public void setBotScore(int botScore) {
        this.botScore = botScore;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void saveToFile(String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(filename), this);
    }

    public static GameState loadFromFile(String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(new File(filename), GameState.class);
    }
}
