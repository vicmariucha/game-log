import java.util.Date;

public class Review {
    public String id;
    public String userId;
    public String gameId;
    public int rating; // Nota do jogo, de 1 a 5
    public String comment; // Comentário da avaliação
    public String date; // Data da avaliação

    // Construtor
    public Review(String id, String userId, String gameId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.rating = rating;
        this.comment = comment;
        this.date = new Date().toString(); // Data atual no formato String
    }
}
