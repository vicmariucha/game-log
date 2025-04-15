package com.example.gamelog;

import android.content.Context;
import android.content.SharedPreferences;

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
    private static final String KEY_REVIEWS = "saved_reviews";
    private static final String KEY_CURRENT_USER = "current_user_id";

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

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public List<String> getReviewIds() {
            return reviewIds;
        }

        public void setReviewIds(List<String> reviewIds) {
            this.reviewIds = reviewIds;
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

    private static List<Game> games = new ArrayList<>();
    private static List<Review> reviews = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null;

    public static void init(Context context) {
        if (sharedPrefs == null) {
            sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }

        loadSampleGames();
        loadSavedUsers();
        loadSavedReviews();

        if (users.isEmpty()) {
            loadSampleUsersAndReviews();
        }

        restoreCurrentUser();
    }

    private static void loadSampleGames() {
        if (!games.isEmpty()) return;

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
    }

    private static void loadSampleUsersAndReviews() {
        users = new ArrayList<>(Arrays.asList(
                new User("u1", "João Silva", "joao@email.com", "Senha123"),
                new User("u2", "Maria Souza", "maria@email.com", "Senha123"),
                new User("u3", "Carlos Oliveira", "carlos@email.com", "Senha123"),
                new User("u4", "Ana Pereira", "ana@email.com", "Senha123")
        ));

        reviews = new ArrayList<>(Arrays.asList(
                new Review("r1", "u1", "g1", 5, "Melhor RPG já feito!"),
                new Review("r2", "u1", "g5", 4, "Gráficos incríveis"),
                new Review("r3", "u2", "g3", 5, "Jogo relaxante"),
                new Review("r4", "u2", "g7", 3, "Viciante mas repetitivo"),
                new Review("r5", "u3", "g10", 4, "Ótimo com amigos"),
                new Review("r6", "u3", "g15", 5, "Competitivo excelente"),
                new Review("r7", "u4", "g2", 2, "Poucas novidades"),
                new Review("r8", "u4", "g20", 5, "Todo fã deve jogar")
        ));

        users.get(0).reviewIds.addAll(Arrays.asList("r1", "r2"));
        users.get(1).reviewIds.addAll(Arrays.asList("r3", "r4"));
        users.get(2).reviewIds.addAll(Arrays.asList("r5", "r6"));
        users.get(3).reviewIds.addAll(Arrays.asList("r7", "r8"));

        saveUsers();
        saveReviews();
    }

    private static void loadSavedUsers() {
        String json = sharedPrefs.getString(KEY_USERS, null);
        if (json != null) {
            Type type = new TypeToken<List<User>>() {}.getType();
            List<User> savedUsers = new Gson().fromJson(json, type);
            if (savedUsers != null) {
                users.clear();
                for (User user : savedUsers) {
                    if (user.reviewIds == null) {
                        user.reviewIds = new ArrayList<>();
                    }
                    users.add(user);
                }
            }
        }
    }

    private static void loadSavedReviews() {
        String json = sharedPrefs.getString(KEY_REVIEWS, null);
        if (json != null) {
            Type type = new TypeToken<List<Review>>() {}.getType();
            List<Review> savedReviews = new Gson().fromJson(json, type);
            if (savedReviews != null) {
                reviews.clear();
                reviews.addAll(savedReviews);
            }
        }
    }

    private static void restoreCurrentUser() {
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

    private static void saveUsers() {
        String json = new Gson().toJson(users);
        sharedPrefs.edit().putString(KEY_USERS, json).apply();
    }

    private static void saveReviews() {
        String json = new Gson().toJson(reviews);
        sharedPrefs.edit().putString(KEY_REVIEWS, json).apply();
    }

    public static boolean registerUser(String name, String email, String password) {
        if (userExists(email)) return false;

        String newId = "u" + (users.size() + 1);
        User newUser = new User(newId, name, email, password);
        users.add(newUser);
        saveUsers();
        return true;
    }

    public static boolean login(String email, String password) {
        for (User user : users) {
            if (user.email.equalsIgnoreCase(email.trim()) && user.password.equals(password.trim())) {
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

    public static User getCurrentUser() {
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

    private static boolean userExists(String email) {
        for (User user : users) {
            if (user.email.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id.equals(updatedUser.id)) {
                users.set(i, updatedUser);
                saveUsers();
                break;
            }
        }
        // Atualiza o currentUser se for o mesmo
        if (currentUser != null && currentUser.id.equals(updatedUser.id)) {
            currentUser = updatedUser;
        }
    }

    // Método para encontrar um jogo pelo nome
    public static Game getGameByName(String name) {
        for (Game game : games) {
            if (game.name.equalsIgnoreCase(name)) {
                return game;
            }
        }
        return null; // Retorna null caso o jogo não seja encontrado
    }

    // Método para criar um novo jogo
    public static Game createGame(String name) {
        String newId = "g" + (games.size() + 1);
        Game newGame = new Game(newId, name, "L", "Desconhecido"); // Criando um jogo com dados padrão
        games.add(newGame);
        saveGames(); // Salva os jogos após a criação
        return newGame;
    }

    // Metodo para criar uma nova avaliação
    public static void createReview(String userId, String gameId, float rating, String comment) {
        String newId = "r" + (reviews.size() + 1);  // Gerar um novo ID
        Review newReview = new Review(newId, userId, gameId, (int) rating, comment);
        reviews.add(newReview);  // Adiciona a avaliação à lista
        saveReviews();  // Salva as avaliações após a criação

        // Associando a avaliação ao usuário
        for (User user : users) {
            if (user.id.equals(userId)) {
                user.reviewIds.add(newId);  // Associa a avaliação ao usuário
                updateUser(user);  // Atualiza o usuário
                break;
            }
        }
    }


    // Metodo para salvar a lista de jogos no SharedPreferences
    private static void saveGames() {
        String json = new Gson().toJson(games);
        sharedPrefs.edit().putString("saved_games", json).apply();
    }

    public static void updateReview(Review updatedReview) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).id.equals(updatedReview.id)) {
                reviews.set(i, updatedReview);  // Atualiza a avaliação
                saveReviews();  // Salva as avaliações após a atualização
                break;
            }
        }
    }


}
