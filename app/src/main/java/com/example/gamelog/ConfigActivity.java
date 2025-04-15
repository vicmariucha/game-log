package com.example.gamelog;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenhaAtual, editNovaSenha;
    private Button btnSalvar;
    private ImageView btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Inicializando os campos
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaAtual = findViewById(R.id.editSenhaAtual);
        editNovaSenha = findViewById(R.id.editNovaSenha);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar); // seta de voltar

        loadUserData();

        // Botão de voltar
        btnVoltar.setOnClickListener(v -> finish());

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senhaAtual = editSenhaAtual.getText().toString().trim();
            String novaSenha = editNovaSenha.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "E-mail inválido.", Toast.LENGTH_SHORT).show();
                return;
            }

            DataHelper.User currentUser = DataHelper.getCurrentUser();

            if (currentUser != null) {
                if (!novaSenha.isEmpty()) {
                    if (senhaAtual.isEmpty()) {
                        Toast.makeText(this, "Digite a senha atual para alterá-la.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!currentUser.password.equals(senhaAtual)) {
                        Toast.makeText(this, "Senha atual incorreta.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    currentUser.password = novaSenha;
                }

                currentUser.name = nome;
                currentUser.email = email;

                DataHelper.updateUser(currentUser);

                Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                editSenhaAtual.setText("");
                editNovaSenha.setText("");
            } else {
                Toast.makeText(this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserData() {
        DataHelper.User currentUser = DataHelper.getCurrentUser();

        if (currentUser != null) {
            editNome.setText(currentUser.name);
            editEmail.setText(currentUser.email);
        } else {
            Toast.makeText(this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
        }
    }
}
