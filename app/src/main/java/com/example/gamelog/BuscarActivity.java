package com.example.gamelog;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
        editBusca.addTextChangedListener(new android.text.TextWatcher() {
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
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }
}
