package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenhaAtual = findViewById(R.id.editSenhaAtual);
        EditText editNovaSenha = findViewById(R.id.editNovaSenha);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnSair = findViewById(R.id.btnSair);

        // Preenche com os dados atuais (simulado)
        editNome.setText(DataHelper.getUsuarioLogado());
        editEmail.setText("usuario@exemplo.com");

        btnSalvar.setOnClickListener(v -> {
            String novaSenha = editNovaSenha.getText().toString();
            if (!novaSenha.isEmpty()) {
                Toast.makeText(this, "Configurações salvas!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Nenhuma alteração foi feita", Toast.LENGTH_SHORT).show();
            }
        });

        btnSair.setOnClickListener(v -> {
            DataHelper.setUsuarioLogado(""); // Limpa o usuário logado
            Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity(); // Fecha todas as activities
        });
    }
}