package org.example.connect4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DatabaseManager {
    private static String FILE_PATH = "players.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        initializeFile();
    }

    /**
     * Set the file path dynamically, useful for testing.
     *
     * @param filePath the file path to use
     */
    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
        initializeFile();
    }

    private static void initializeFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<PlayerScore>());
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize the JSON file.", e);
            }
        }
    }

    public static void upsertPlayer(String name, int score) {
        List<PlayerScore> players = loadPlayers();
        boolean playerFound = false;

        for (PlayerScore player : players) {
            if (player.getName().equals(name)) {
                player.setScore(player.getScore() + score);
                playerFound = true;
                break;
            }
        }

        if (!playerFound) {
            players.add(new PlayerScore(name, score));
        }

        savePlayers(players);
    }

    public static List<String> getHighScores() {
        List<PlayerScore> players = loadPlayers();
        players.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());

        List<String> highScores = new ArrayList<>();
        for (int i = 0; i < Math.min(players.size(), 10); i++) {
            PlayerScore player = players.get(i);
            highScores.add(player.getName() + " - " + player.getScore());
        }

        return highScores;
    }

    public static void resetScores() {
        List<PlayerScore> players = loadPlayers();
        for (PlayerScore player : players) {
            player.setScore(0);
        }
        savePlayers(players);
    }

    private static List<PlayerScore> loadPlayers() {
        try {
            return mapper.readValue(new File(FILE_PATH), new TypeReference<List<PlayerScore>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load players from the JSON file.", e);
        }
    }

    private static void savePlayers(List<PlayerScore> players) {
        try {
            mapper.writeValue(new File(FILE_PATH), players);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save players to the JSON file.", e);
        }
    }

    // pontok
    private static class PlayerScore {
        private String name;
        private int score;

        public PlayerScore() {}

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
