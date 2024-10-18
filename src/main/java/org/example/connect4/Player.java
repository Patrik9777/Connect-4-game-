package org.example.connect4;

public class Player {
    private final String name;
    private final char token;

    public Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public char getToken() {
        return token;
    }
}
