package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class BuscarActivity extends AppCompatActivity {

    private ListView listUsuarios;
    private EditText editBusca;
    private List<String> allUsers;  // Simulação de dados de usuários
    private List<String> filteredUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        // Inicializa os componentes
        listUsuarios = findViewById(R.id.listUsuarios);
        editBusca = findViewById(R.id.editBusca);

        // Dados simulados de usuários (substitua com dados reais)
        allUsers = new ArrayList<>();
        allUsers.add("João");
        allUsers.add("Maria");
        allUsers.add("Carlos");
        allUsers.add("Ana");
        allUsers.add("Lucas");

        // Lista filtrada (inicialmente é igual a todos os usuários)
        filteredUsers = new ArrayList<>(allUsers);

        // Adaptador para exibir os usuários na ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredUsers);
        listUsuarios.setAdapter(adapter);

        // Listener para o campo de busca
        editBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Filtra os usuários com base no texto de busca
                String query = charSequence.toString().toLowerCase();
                filteredUsers.clear();
                for (String user : allUsers) {
                    if (user.toLowerCase().contains(query)) {
                        filteredUsers.add(user);
                    }
                }
                adapter.notifyDataSetChanged(); // Atualiza a ListView
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Listener de clique para abrir o perfil do usuário
        listUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Obtém o nome do usuário clicado
                String nomeUsuario = filteredUsers.get(position);

                // Cria a intent para ir para o perfil do usuário
                Intent intent = new Intent(BuscarActivity.this, PerfilActivity.class);
                intent.putExtra("nomeUsuario", nomeUsuario); // Passa o nome do usuário para o perfil
                startActivity(intent);
            }
        });
    }
}
