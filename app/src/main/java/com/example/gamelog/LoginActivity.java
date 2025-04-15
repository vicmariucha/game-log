package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogin;
    private ImageView btnVoltar;
    private TextView txtCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DataHelper.init(getApplicationContext());

        // Inicialização dos componentes da interface
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtCadastro = findViewById(R.id.txtCadastro);

        // Aplica negrito ao trecho "Cadastre-se"
        String texto = "Não tem uma conta? Cadastre-se";
        SpannableString spannable = new SpannableString(texto);
        spannable.setSpan(
                new StyleSpan(android.graphics.Typeface.BOLD),
                texto.indexOf("Cadastre-se"),
                texto.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        txtCadastro.setText(spannable);

        // Ação ao clicar no botão "Entrar"
        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

            // Validação dos campos
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(LoginActivity.this, "Por favor, insira um email válido", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d("LoginActivity", "Email: " + email);
            Log.d("LoginActivity", "Senha: " + senha);

            // Verificação com usuários reais do DataHelper
            if (DataHelper.login(email, senha)) {
                DataHelper.User usuarioAtual = DataHelper.getCurrentUser();

                Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
                intent.putExtra("nomeUsuario", usuarioAtual.name); // envia nome real
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }

        });

        // Ação ao clicar na seta de voltar
        btnVoltar.setOnClickListener(v -> finish());

        // Ação ao clicar no texto "Cadastre-se"
        txtCadastro.setOnClickListener(this::navigateToSignUp);
    }

    // Navega para a tela de cadastro
    public void navigateToSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    // Simula verificação de email e senha (substituir por lógica real com banco de dados futuramente)
    private boolean checkUserCredentials(String email, String senha) {
        Log.d("LoginActivity", "Verificando credenciais: Email: " + email + " Senha: " + senha);

        // Credenciais fictícias
        String storedEmail = "teste@email.com";
        String storedPassword = "123456";

        // Comparação exata
        return email.equals(storedEmail) && senha.equals(storedPassword);
    }
}
