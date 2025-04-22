package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtUserName; // Nome do usuário
    private ListView listUserReviews; // Lista de avaliações do usuário
    private List<String> reviews;  // Simulação das avaliações
    private String userName;
    private ImageView btnVoltar; // Botão de voltar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializa os componentes da tela
        txtUserName = findViewById(R.id.txtUserName);
        listUserReviews = findViewById(R.id.listUserReviews);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Pega os dados do usuário vindo do Intent
        userName = getIntent().getStringExtra("nomeUsuario"); // agora bate com a chave da BuscarActivity


        // Verifica se o nome do usuário não é nulo e atualiza o TextView
        if (userName != null) {
            txtUserName.setText(userName); // Exibe o nome do usuário
        } else {
            txtUserName.setText("Nome não disponível");
        }

        // Define o título da tela como "Lista de [Nome do Usuário]"
        TextView txtNomeLista = findViewById(R.id.txtNomeLista);
        if (userName != null) {
            txtNomeLista.setText("Lista de " + userName);
        } else {
            txtNomeLista.setText("Lista de usuário");
        }

        // Simulação das avaliações
        reviews = new ArrayList<>();
        reviews.add("Avaliação 1: Ótimo jogo!");
        reviews.add("Avaliação 2: Muito divertido!");
        reviews.add("Avaliação 3: Excelente gráfico!");

        // Adaptador para mostrar as avaliações do usuário
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviews);
        listUserReviews.setAdapter(adapter);

        // Configura a ação do botão de voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volta para a tela anterior (Buscar)
                onBackPressed();
            }
        });
    }
}
