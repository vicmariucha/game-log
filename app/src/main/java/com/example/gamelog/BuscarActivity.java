package com.example.gamelog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BuscarActivity extends AppCompatActivity {

    private ListView listUsuarios;
    private EditText editBusca;
    private List<String> allUsers;
    private List<String> filteredUsers;
    private BottomNavigationView bottomNavigationView;
    private ImageButton btnNovaAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        listUsuarios = findViewById(R.id.listUsuarios);
        editBusca = findViewById(R.id.editBusca);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btnNovaAvaliacao = findViewById(R.id.btnNovaAvaliacao);

        allUsers = new ArrayList<>();
        allUsers.add("João");
        allUsers.add("Maria");
        allUsers.add("Carlos");
        allUsers.add("Ana");
        allUsers.add("Lucas");

        filteredUsers = new ArrayList<>(allUsers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_usuario, filteredUsers) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_usuario, parent, false);
                }

                TextView nomeUsuario = convertView.findViewById(R.id.txtNomeUsuario);
                nomeUsuario.setText(filteredUsers.get(position));

                Button btnVerLista = convertView.findViewById(R.id.btnVerLista);
                btnVerLista.setOnClickListener(v -> {
                    String nome = filteredUsers.get(position);
                    Intent intent = new Intent(BuscarActivity.this, PerfilActivity.class);
                    intent.putExtra("nomeUsuario", nome);  // Passando o nome do usuário
                    startActivity(intent);
                });

                return convertView;
            }
        };

        listUsuarios.setAdapter(adapter);

        editBusca.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();
                filteredUsers.clear();
                for (String user : allUsers) {
                    if (user.toLowerCase().contains(query)) {
                        filteredUsers.add(user);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        // Clique do botão flutuante
        btnNovaAvaliacao.setOnClickListener(v -> {
            Intent intent = new Intent(BuscarActivity.this, NovaAvaliacaoActivity.class);
            startActivity(intent);
        });

        // Define o item ativo
        bottomNavigationView.setSelectedItemId(R.id.nav_buscar);

        // Navegação da navbar inferior
        bottomNavigationView.setOnItemSelectedListener(item -> {
            animateItemClick(item);

            int id = item.getItemId();
            if (id == R.id.nav_buscar) {
                return true; // já está na tela de busca
            } else if (id == R.id.nav_home) {
                startActivity(new Intent(BuscarActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_config) {
                startActivity(new Intent(BuscarActivity.this, ConfigActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_wishlist) {
                startActivity(new Intent(BuscarActivity.this, WishlistActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }

    private void animateItemClick(MenuItem item) {
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
