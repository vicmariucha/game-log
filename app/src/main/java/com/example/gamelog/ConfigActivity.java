package com.example.gamelog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenhaAtual, editNovaSenha;
    private Button btnSalvar, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Inicializando os EditText e Buttons
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaAtual = findViewById(R.id.editSenhaAtual);
        editNovaSenha = findViewById(R.id.editNovaSenha);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSair = findViewById(R.id.btnSair);

        // Carregar as informações do usuário atual
        loadUserData();

        // Lógica para salvar alterações e sair
        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senhaAtual = editSenhaAtual.getText().toString().trim();
            String novaSenha = editNovaSenha.getText().toString().trim();

            // Validar se os campos necessários foram preenchidos
            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(ConfigActivity.this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lógica para verificar a senha atual antes de permitir alteração
            DataHelper.User currentUser = DataHelper.getCurrentUser();
            if (currentUser != null) {
                if (!senhaAtual.isEmpty() && !currentUser.password.equals(senhaAtual)) {
                    Toast.makeText(ConfigActivity.this, "Senha atual incorreta.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Atualizar os dados do usuário
                currentUser.name = nome;
                currentUser.email = email;

                // Atualizar a senha se fornecida
                if (!novaSenha.isEmpty()) {
                    currentUser.password = novaSenha;
                }

                // Salvar as alterações
                DataHelper.updateUser(currentUser);

                // Mostrar uma mensagem de sucesso
                Toast.makeText(ConfigActivity.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConfigActivity.this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
            }
        });

        // Lógica para sair e voltar à tela anterior
        btnSair.setOnClickListener(v -> finish()); // Fechar a tela de configurações
    }

    private void loadUserData() {
        // Pega o usuário atual a partir do DataHelper
        DataHelper.User currentUser = DataHelper.getCurrentUser();

        if (currentUser != null) {
            // Preenche os campos de EditText com os dados do usuário
            editNome.setText(currentUser.name);
            editEmail.setText(currentUser.email);
            // Deixe a senha atual vazia ou mascarada
            editSenhaAtual.setText(""); // Não exibe a senha atual
            editNovaSenha.setText(""); // Não exibe a nova senha
        } else {
            // Caso não haja um usuário logado
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
