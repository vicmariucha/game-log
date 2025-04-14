package com.example.gamelog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataHelper {
    private static SharedPreferences sharedPrefs;
    private static final String PREFS_NAME = "GameLogData";
    private static final String KEY_USERS = "saved_users";
    private static final String KEY_CURRENT_USER = "current_user_id";

    // Modelos de dados
    public static class User {
        public String id;
        public String name;
        public String email;
        public String password;
        public List<String> reviewIds;

        public User(String id, String name, String email, String password) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.reviewIds = new ArrayList<>();
        }
    }

    public static class Game {
        public String id;
        public String name;
        public String rating;
        public String genre;

        public Game(String id, String name, String rating, String genre) {
            this.id = id;
            this.name = name;
            this.rating = rating;
            this.genre = genre;
        }
    }

    public static class Review {
        public String id;
        public String userId;
        public String gameId;
        public int rating;
        public String comment;
        public String date;

        public Review(String id, String userId, String gameId, int rating, String comment) {
            this.id = id;
            this.userId = userId;
            this.gameId = gameId;
            this.rating = rating;
            this.comment = comment;
            this.date = new Date().toString();
        }
    }

    // Dados em memória
    private static List<Game> games = new ArrayList<>();
    private static List<Review> reviews = new ArrayList<>();
    private static List<User> users = new ArrayList<>();  // Modificado para ser uma ArrayList
    private static User currentUser = null;

    public static void init(Context context) {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadSampleData();
        loadSavedUsers();
    }

    private static void loadSampleData() {
        if (!games.isEmpty()) return;

        // 20 jogos pré-cadastrados
        games = Arrays.asList(
                new Game("g1", "The Witcher 3", "18+", "RPG"),
                new Game("g2", "FIFA 23", "L", "Esporte"),
                new Game("g3", "Minecraft", "L", "Sandbox"),
                new Game("g4", "Call of Duty", "16+", "FPS"),
                new Game("g5", "God of War", "18+", "Ação"),
                new Game("g6", "Zelda", "12+", "Aventura"),
                new Game("g7", "Fortnite", "12+", "Battle Royale"),
                new Game("g8", "Red Dead 2", "18+", "Ação-Aventura"),
                new Game("g9", "GTA V", "18+", "Ação"),
                new Game("g10", "Overwatch", "12+", "FPS"),
                new Game("g11", "Cyberpunk", "18+", "RPG"),
                new Game("g12", "Assassin's Creed", "18+", "RPG"),
                new Game("g13", "Stardew Valley", "L", "Simulação"),
                new Game("g14", "Apex Legends", "16+", "FPS"),
                new Game("g15", "Rocket League", "L", "Esporte"),
                new Game("g16", "Among Us", "10+", "Party"),
                new Game("g17", "Valorant", "16+", "FPS"),
                new Game("g18", "League of Legends", "12+", "MOBA"),
                new Game("g19", "Counter-Strike", "18+", "FPS"),
                new Game("g20", "Hogwarts Legacy", "12+", "RPG")
        );

        // 4 usuários iniciais (senha: Senha123)
        users = new ArrayList<>(Arrays.asList(  // Modificado para usar ArrayList
                new User("u1", "João Silva", "joao@email.com", "Senha123"),
                new User("u2", "Maria Souza", "maria@email.com", "Senha123"),
                new User("u3", "Carlos Oliveira", "carlos@email.com", "Senha123"),
                new User("u4", "Ana Pereira", "ana@email.com", "Senha123")
        ));

        // 8 avaliações (2 por usuário)
        reviews = Arrays.asList(
                new Review("r1", "u1", "g1", 5, "Melhor RPG já feito!"),
                new Review("r2", "u1", "g5", 4, "Gráficos incríveis"),
                new Review("r3", "u2", "g3", 5, "Jogo relaxante"),
                new Review("r4", "u2", "g7", 3, "Viciante mas repetitivo"),
                new Review("r5", "u3", "g10", 4, "Ótimo com amigos"),
                new Review("r6", "u3", "g15", 5, "Competitivo excelente"),
                new Review("r7", "u4", "g2", 2, "Poucas novidades"),
                new Review("r8", "u4", "g20", 5, "Todo fã deve jogar")
        );

        // Vincula reviews aos usuários
        users.get(0).reviewIds.add("r1");
        users.get(0).reviewIds.add("r2");
        users.get(1).reviewIds.add("r3");
        users.get(1).reviewIds.add("r4");
        users.get(2).reviewIds.add("r5");
        users.get(2).reviewIds.add("r6");
        users.get(3).reviewIds.add("r7");
        users.get(3).reviewIds.add("r8");
    }

    private static void loadSavedUsers() {
        String json = sharedPrefs.getString(KEY_USERS, null);
        if (json != null) {
            Type type = new TypeToken<List<User>>(){}.getType();
            List<User> savedUsers = new Gson().fromJson(json, type);
            if (savedUsers != null) {
                // Mantém usuários originais e adiciona os salvos
                for (User savedUser : savedUsers) {
                    if (!userExists(savedUser.email)) {
                        users.add(savedUser);
                    }
                }
            }
        }
    }

    private static boolean userExists(String email) {
        for (User user : users) {
            if (user.email.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private static void saveUsers() {
        List<User> usersToSave = new ArrayList<>(users);
        String json = new Gson().toJson(usersToSave);
        sharedPrefs.edit().putString(KEY_USERS, json).apply();
    }

    // Métodos de autenticação
    public static boolean registerUser(String name, String email, String password) {
        if (userExists(email)) {
            return false;
        }

        String newId = "u" + (users.size() + 1);
        User newUser = new User(newId, name, email, password);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public static boolean login(String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                currentUser = user;
                sharedPrefs.edit().putString(KEY_CURRENT_USER, user.id).apply();
                return true;
            }
        }
        return false;
    }

    public static void logout() {
        currentUser = null;
        sharedPrefs.edit().remove(KEY_CURRENT_USER).apply();
    }

    // Getters
    public static User getCurrentUser() {
        if (currentUser == null) {
            String userId = sharedPrefs.getString(KEY_CURRENT_USER, null);
            if (userId != null) {
                for (User user : users) {
                    if (user.id.equals(userId)) {
                        currentUser = user;
                        break;
                    }
                }
            }
        }
        return currentUser;
    }

    public static List<Game> getAllGames() {
        return games;
    }

    public static List<Review> getUserReviews(String userId) {
        List<Review> userReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.userId.equals(userId)) {
                userReviews.add(review);
            }
        }
        return userReviews;
    }

    public static Game getGameById(String gameId) {
        for (Game game : games) {
            if (game.id.equals(gameId)) {
                return game;
            }
        }
        return null;
    }
}
