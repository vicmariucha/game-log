package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        EditText editConfirmaSenha = findViewById(R.id.editConfirmaSenha);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            String confirmaSenha = editConfirmaSenha.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (!senha.equals(confirmaSenha)) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            } else {
                DataHelper.setUsuarioLogado(nome);
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}