package com.example.gamelog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NovaAvaliacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_avaliacao);

        EditText editTitulo = findViewById(R.id.editTitulo);
        EditText editDescricao = findViewById(R.id.editDescricao);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString();
            String descricao = editDescricao.getText().toString();
            float rating = ratingBar.getRating();

            if (titulo.isEmpty()) {
                Toast.makeText(this, "Digite um título para a avaliação", Toast.LENGTH_SHORT).show();
            } else {
                String avaliacao = titulo + " - " + rating + " estrelas\n" + descricao;
                DataHelper.addAvaliacao(avaliacao);
                Toast.makeText(this, "Avaliação salva!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}