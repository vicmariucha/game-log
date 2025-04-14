package com.example.gamelog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.appcompat.app.AppCompatActivity;

public class NovaAvaliacaoActivity extends AppCompatActivity {

    private EditText editTitulo, editDescricao;
    private RatingBar ratingBar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_avaliacao);

        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        ratingBar = findViewById(R.id.ratingBar);
        btnSalvar = findViewById(R.id.btnSalvar);

        // Lógica para salvar a avaliação pode ser implementada aqui.
    }
}
