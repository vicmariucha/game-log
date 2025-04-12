package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView txtUsuario = findViewById(R.id.txtUsuario);
        txtUsuario.setText("Bem-vindo, " + DataHelper.getUsuarioLogado());

        ListView listAvaliacoes = findViewById(R.id.listAvaliacoes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, DataHelper.getAvaliacoes());
        listAvaliacoes.setAdapter(adapter);

        findViewById(R.id.btnNovaAvaliacao).setOnClickListener(v -> {
            startActivity(new Intent(this, NovaAvaliacaoActivity.class));
        });

        findViewById(R.id.btnBuscar).setOnClickListener(v -> {
            startActivity(new Intent(this, BuscarActivity.class));
        });

        findViewById(R.id.btnHome).setOnClickListener(v -> {
            finish();
            startActivity(getIntent());
        });

        findViewById(R.id.btnConfig).setOnClickListener(v -> {
            startActivity(new Intent(this, ConfigActivity.class));
        });
    }
}