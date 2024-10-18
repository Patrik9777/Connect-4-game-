package org.example.connect4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:connect4.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQLite JDBC driver.", e);
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL UNIQUE,\n"
                + " score integer\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null) {
            if (stmt != null) {
                stmt.execute(sql);
                System.out.println("Table created.");
            } else {
                System.out.println("Failed to create statement.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static void upsertPlayer(String name, int score) {
        String selectSql = "SELECT score FROM players WHERE name = ?";
        String updateSql = "UPDATE players SET score = ? WHERE name = ?";
        String insertSql = "INSERT INTO players(name, score) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement selectStmt = conn != null ? conn.prepareStatement(selectSql) : null;
             PreparedStatement updateStmt = conn != null ? conn.prepareStatement(updateSql) : null;
             PreparedStatement insertStmt = conn != null ? conn.prepareStatement(insertSql) : null) {

            if (selectStmt != null) {
                selectStmt.setString(1, name);
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    // Frissíti a meglévő játékos pontszámát
                    int currentScore = rs.getInt("score");
                    System.out.println("Current score for " + name + ": " + currentScore);
                    updateStmt.setInt(1, currentScore + score);
                    updateStmt.setString(2, name);
                    updateStmt.executeUpdate();
                    System.out.println("Updated score for " + name + ": " + (currentScore + score));
                } else {
                    // Új játékos beszúrása
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, score);
                    insertStmt.executeUpdate();
                    System.out.println("Inserted new player " + name + " with score: " + score);
                }
            } else {
                System.out.println("Failed to create select statement.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static List<String> getHighScores() {
        String sql = "SELECT name, score FROM players ORDER BY score DESC LIMIT 10";
        List<String> highScores = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null;
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                highScores.add(name + " - " + score);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return highScores;
    }

    public static void resetScores() {
        String sql = "UPDATE players SET score = 0";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (pstmt != null) {
                pstmt.executeUpdate();
                System.out.println("All scores have been reset to zero.");
            } else {
                System.out.println("Failed to create update statement.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}
