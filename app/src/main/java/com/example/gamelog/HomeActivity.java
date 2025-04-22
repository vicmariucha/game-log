package com.example.gamelog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

// IMPORTAÇÕES DAS ACTIVITYS QUE JÁ EXISTEM
import com.example.gamelog.NovaAvaliacaoActivity;
import com.example.gamelog.BuscarActivity;
import com.example.gamelog.ConfigActivity;
import com.example.gamelog.WishlistFragment; // Importando o novo fragmento

public class HomeActivity extends AppCompatActivity {

    private TextView txtNomeUsuario;  // Atualizado o nome da variável
    private ListView listAvaliacoes;  // Atualizado o nome da variável
    private ImageButton btnNovaAvaliacao;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataHelper.init(this);
        DataHelper.User user = DataHelper.getCurrentUser();

        // Atualizando os findViewById com os IDs corretos
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);  // Alterado
        listAvaliacoes = findViewById(R.id.listAvaliacoes);  // Alterado
        btnNovaAvaliacao = findViewById(R.id.btnNovaAvaliacao);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (user != null) {
            txtNomeUsuario.setText("Bem-vindo, " + user.name);

            List<DataHelper.Review> reviews = DataHelper.getUserReviews(user.id);
            ReviewAdapter adapter = new ReviewAdapter(this, reviews);
            listAvaliacoes.setAdapter(adapter);
        }

        // Botão para adicionar avaliação
        btnNovaAvaliacao.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NovaAvaliacaoActivity.class);
            startActivity(intent);
        });

        // Definir o item ativo da barra de navegação
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        // Navegação pela barra inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Adicionando animação ao clicar no item
                animateItemClick(item);

                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    return true; // já está na home
                } else if (id == R.id.nav_buscar) {
                    startActivity(new Intent(HomeActivity.this, BuscarActivity.class));
                    return true;
                } else if (id == R.id.nav_config) {
                    startActivity(new Intent(HomeActivity.this, ConfigActivity.class));
                    return true;
                } else if (id == R.id.nav_wishlist) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new WishlistFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void animateItemClick(MenuItem item) {
        // Animação de aumento de ícone ao selecionar o item
        if (item.getActionView() != null) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(item.getActionView(), "scaleX", 1f, 1.2f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(item.getActionView(), "scaleY", 1f, 1.2f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scaleX, scaleY);
            animatorSet.setDuration(300);
            animatorSet.start();
        }
    }
}
