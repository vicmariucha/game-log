package com.example.gamelog;

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

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogin;
    private ImageView btnVoltar;
    private TextView txtCadastro;  // TextView para o link de cadastro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referência aos componentes da interface
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtCadastro = findViewById(R.id.txtCadastro);

        // Aplicar o negrito ao texto "Cadastre-se"
        String texto = "Não tem uma conta? Cadastre-se";
        SpannableString spannable = new SpannableString(texto);
        spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), texto.indexOf("Cadastre-se"), texto.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Definir o texto no TextView
        txtCadastro.setText(spannable);

        // Clique no botão "Entrar"
        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

            // Verifica se os campos estão preenchidos
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulação de autenticação (substituir com lógica real)
            if (email.equals("teste@email.com") && senha.equals("123456")) {
                Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
                intent.putExtra("nomeUsuario", "Usuário Teste");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });

        // Clique na seta de voltar para finalizar a atividade
        btnVoltar.setOnClickListener(v -> finish());
    }

    // Abertura da tela de cadastro
    public void navigateToSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
