package com.example.gamelog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class PerfilActivity extends AppCompatActivity {

    private TextView textNomeUsuario;
    private ListView listAvaliacoes;
    private List<String> avaliacoes;  // Simulação das avaliações
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializa os componentes da tela
        textNomeUsuario = findViewById(R.id.textNomeUsuario);
        listAvaliacoes = findViewById(R.id.listAvaliacoes);

        // Pega o nome do usuário vindo do Intent
        nomeUsuario = getIntent().getStringExtra("nomeUsuario");
        textNomeUsuario.setText(nomeUsuario);

        // Simulação das avaliações (substitua por dados reais)
        avaliacoes = new ArrayList<>();
        avaliacoes.add("Avaliação 1: Ótimo jogo!");
        avaliacoes.add("Avaliação 2: Muito divertido!");
        avaliacoes.add("Avaliação 3: Excelente gráfico!");

        // Adaptador para mostrar as avaliações do usuário
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, avaliacoes);
        listAvaliacoes.setAdapter(adapter);
    }
}
