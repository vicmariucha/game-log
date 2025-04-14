package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

// IMPORTAÇÕES DAS ACTIVITYS QUE JÁ EXISTEM
import com.example.gamelog.NovaAvaliacaoActivity;
import com.example.gamelog.BuscarActivity;
import com.example.gamelog.ConfigActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private ListView listReviews;
    private FloatingActionButton btnNovaAvaliacao;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataHelper.init(this);
        DataHelper.User user = DataHelper.getCurrentUser();

        txtWelcome = findViewById(R.id.txtWelcome);
        listReviews = findViewById(R.id.listReviews);
        btnNovaAvaliacao = findViewById(R.id.btnNovaAvaliacao);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (user != null) {
            txtWelcome.setText("Bem-vindo, " + user.name);

            List<DataHelper.Review> reviews = DataHelper.getUserReviews(user.id);
            List<String> reviewItems = new ArrayList<>();

            for (DataHelper.Review review : reviews) {
                DataHelper.Game game = DataHelper.getGameById(review.gameId);
                if (game != null) {
                    reviewItems.add(game.name + " - " + review.rating + "★: " + review.comment);
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    reviewItems
            );
            listReviews.setAdapter(adapter);
        }

        // Botão flutuante para adicionar avaliação
        btnNovaAvaliacao.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NovaAvaliacaoActivity.class);
            startActivity(intent);
        });

        // Navegação pela barra inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    return true; // já está na home
                } else if (id == R.id.nav_buscar) {
                    startActivity(new Intent(HomeActivity.this, BuscarActivity.class));
                    return true;
                } else if (id == R.id.nav_config) {
                    startActivity(new Intent(HomeActivity.this, ConfigActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
