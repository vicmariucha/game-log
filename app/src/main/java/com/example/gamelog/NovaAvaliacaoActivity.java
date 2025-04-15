package com.example.gamelog;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NovaAvaliacaoActivity extends AppCompatActivity {

    private EditText editTitulo, editDescricao;
    private RatingBar ratingBar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_avaliacao);

        DataHelper.init(this); // garante que o helper está pronto

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        ratingBar = findViewById(R.id.ratingBar);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            float nota = ratingBar.getRating();

            // Validação
            if (TextUtils.isEmpty(titulo)) {
                editTitulo.setError("Digite o nome do jogo");
                return;
            }

            if (nota == 0) {
                Toast.makeText(this, "Dê uma nota para o jogo", Toast.LENGTH_SHORT).show();
                return;
            }

            DataHelper.User user = DataHelper.getCurrentUser();
            if (user == null) {
                Toast.makeText(this, "Usuário não identificado", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verifica se o jogo já existe, senão cria
            DataHelper.Game game = DataHelper.getGameByName(titulo);
            if (game == null) {
                game = DataHelper.createGame(titulo);
            }

            // Salva a avaliação
            DataHelper.createReview(user.id, game.id, nota, descricao);

            Toast.makeText(this, "Avaliação salva com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Fecha e volta para a tela anterior (Home)
        });
    }
}
