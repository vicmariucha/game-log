package com.example.gamelog;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        DataHelper.init(this); // Inicializa o banco de dados

        EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        EditText editConfirmaSenha = findViewById(R.id.editConfirmarSenha);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        ImageView btnVoltar = findViewById(R.id.btnVoltar);
        TextView txtLogin = findViewById(R.id.tvLogin);

        // Aplicar o negrito ao texto "Faça login"
        String texto = "Já tem uma conta? Faça login";
        SpannableString spannable = new SpannableString(texto);
        spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), texto.indexOf("Faça login"), texto.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Definir o texto no TextView
        txtLogin.setText(spannable);

        // Configuração do botão de voltar
        btnVoltar.setOnClickListener(v -> onBackPressed()); // Função para voltar à tela de login

        btnCadastrar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString();
            String confirmaSenha = editConfirmaSenha.getText().toString();

            if (!validarDados(nome, email, senha, confirmaSenha)) return;

            boolean cadastroFeito = DataHelper.registerUser(nome, email, senha);

            if (cadastroFeito) {
                Log.d(TAG, "Cadastro bem-sucedido. Realizando login automático...");
                DataHelper.login(email, senha);
                Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Log.d(TAG, "Cadastro falhou. Email já cadastrado.");
                Toast.makeText(this, "Email já cadastrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validarDados(String nome, String email, String senha, String confirmaSenha) {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (senha.length() < 6) {
            Toast.makeText(this, "Senha deve ter 6+ caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!senha.equals(confirmaSenha)) {
            Toast.makeText(this, "Senhas não coincidem", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void navigateToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish(); // Garante que o usuário não volte com o botão "voltar"
    }
}
