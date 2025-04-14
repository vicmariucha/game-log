package com.example.gamelog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenhaAtual, editNovaSenha;
    private Button btnSalvar, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaAtual = findViewById(R.id.editSenhaAtual);
        editNovaSenha = findViewById(R.id.editNovaSenha);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSair = findViewById(R.id.btnSair);

        // Lógica para salvar alterações e sair pode ser implementada aqui.
    }
}
