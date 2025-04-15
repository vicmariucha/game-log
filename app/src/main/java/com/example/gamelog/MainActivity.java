package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Tag para logs de depuração

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa o DataHelper com o contexto da aplicação
        DataHelper.init(getApplicationContext());

        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate executado");

        // Referência aos botões da tela inicial
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        // Clique no botão "Login"
        btnLogin.setOnClickListener(v -> {
            Log.d(TAG, "Botão Login clicado!");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Clique no botão "Cadastrar"
        btnSignUp.setOnClickListener(v -> {
            Log.d(TAG, "Botão Cadastrar clicado!");
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}
